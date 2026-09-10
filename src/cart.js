export function total(items) {
  if (!Array.isArray(items)) throw new TypeError("items must be an array");
  return items.reduce((sum, item) => sum + item.price * item.quantity, 0);
}
