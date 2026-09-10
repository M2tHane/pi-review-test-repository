import { readFile, realpath, stat } from "node:fs/promises";
import { isAbsolute, relative, resolve } from "node:path";

export async function readNote(notesDirectory, noteName) {
  if (typeof notesDirectory !== "string" || typeof noteName !== "string" || !noteName) throw new TypeError("notesDirectory and noteName must be non-empty strings");
  const root = await realpath(notesDirectory);
  const candidate = resolve(root, noteName);
  if (!inside(root, candidate)) throw new Error("note path escapes notes directory");
  const target = await realpath(candidate);
  if (!inside(root, target)) throw new Error("note symlink escapes notes directory");
  const info = await stat(target);
  if (!info.isFile() || info.size > 1_000_000) throw new Error("note must be a file no larger than 1 MB");
  return readFile(target, "utf8");
}

function inside(root, target) {
  const path = relative(root, target);
  return path !== "" && !path.startsWith("..") && !isAbsolute(path);
}
