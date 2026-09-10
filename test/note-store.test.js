import assert from "node:assert/strict";
import { mkdtemp, rm, writeFile } from "node:fs/promises";
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
