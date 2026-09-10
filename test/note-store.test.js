import assert from "node:assert/strict";
import { mkdtemp, rm, symlink, writeFile } from "node:fs/promises";
import { tmpdir } from "node:os";
import { join } from "node:path";
import test from "node:test";
import { readNote } from "../src/note-store.js";

test("reads a note", async () => {
  const directory = await mkdtemp(join(tmpdir(), "notes-"));
  try {
    await writeFile(join(directory, "hello.txt"), "hello");
    assert.equal(await readNote(directory, "hello.txt"), "hello");
  } finally {
    await rm(directory, { recursive: true });
  }
});

test("rejects paths and symlinks outside the notes directory", async () => {
  const directory = await mkdtemp(join(tmpdir(), "notes-"));
  const outside = await mkdtemp(join(tmpdir(), "outside-"));
  try {
    await writeFile(join(outside, "secret.txt"), "secret");
    await assert.rejects(readNote(directory, "../secret.txt"), /escapes/);
    await symlink(join(outside, "secret.txt"), join(directory, "link.txt"));
    await assert.rejects(readNote(directory, "link.txt"), /symlink/);
  } finally {
    await Promise.all([rm(directory, { recursive: true }), rm(outside, { recursive: true })]);
  }
});
