"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
/**
 * Iterator Pattern
 */
var Bucket_1 = require("./Iterator/Bucket");
var Item_1 = require("./Iterator/Item");
var bucket = new Bucket_1.Bucket();
for (var i = 0; i < 10; i++) {
    var item = new Item_1.Item("\uC0C1\uD488 " + i, 12000 + i * 10);
    bucket.addItem(item);
}
var iterator = bucket.getIterator();
while (iterator.hasNext()) {
    var item = iterator.next();
    console.log(item.info());
}
//# sourceMappingURL=Main.js.map