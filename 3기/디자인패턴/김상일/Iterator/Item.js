"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var Item = (function () {
    function Item(name, price) {
        this.name = name;
        this.price = price;
    }
    Item.prototype.info = function () {
        return "\uC0C1\uD488\uBA85: " + this.name + ", \uAC00\uACA9: " + this.price;
    };
    return Item;
}());
exports.Item = Item;
//# sourceMappingURL=Item.js.map