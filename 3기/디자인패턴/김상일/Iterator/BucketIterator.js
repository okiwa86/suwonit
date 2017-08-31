"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var BucketIterator = (function () {
    function BucketIterator(bucket) {
        this.bucket = bucket;
        this.index = 0;
    }
    BucketIterator.prototype.hasNext = function () {
        var hasNext = false;
        if (this.index < this.bucket.getLength()) {
            hasNext = true;
        }
        return hasNext;
    };
    BucketIterator.prototype.next = function () {
        return this.bucket.get(this.index++);
    };
    return BucketIterator;
}());
exports.BucketIterator = BucketIterator;
//# sourceMappingURL=BucketIterator.js.map