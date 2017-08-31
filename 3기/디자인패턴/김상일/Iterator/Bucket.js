"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var BucketIterator_1 = require("./BucketIterator");
var Bucket = (function () {
    function Bucket() {
        this.repository = [];
    }
    Bucket.prototype.addItem = function (item) {
        this.repository.push(item);
    };
    Bucket.prototype.get = function (index) {
        return this.repository[index];
    };
    Bucket.prototype.getIterator = function () {
        return new BucketIterator_1.BucketIterator(this);
    };
    Bucket.prototype.getLength = function () {
        return this.repository.length;
    };
    return Bucket;
}());
exports.Bucket = Bucket;
//# sourceMappingURL=Bucket.js.map