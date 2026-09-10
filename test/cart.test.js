import assert from "node:assert/strict";
import test from "node:test";
import { total } from "../src/cart.js";

test("calculates a cart total", () => {
  assert.equal(total([{ price: 12.5, quantity: 2 }, { price: 5, quantity: 1 }]), 30);
});

test("rejects invalid input", () => {
  assert.throws(() => total(null), TypeError);
});
