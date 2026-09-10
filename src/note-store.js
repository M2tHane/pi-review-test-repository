import { readFile } from "node:fs/promises";
import { join } from "node:path";

export function readNote(notesDirectory, noteName) {
  return readFile(join(notesDirectory, noteName), "utf8");
}
