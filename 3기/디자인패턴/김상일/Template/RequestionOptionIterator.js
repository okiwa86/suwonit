"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var RequestionOptionIterator = (function () {
    function RequestionOptionIterator(requestionOption) {
        this.currentPageNumber = 0;
        this.requestionOption = requestionOption;
    }
    RequestionOptionIterator.prototype.hasNext = function () {
        console.log('send next page existence check!');
        if (this.currentPageNumber <= this.requestionOption.pages.length) {
            console.log('there is next item');
            return true;
        }
        console.log('this iteration is last');
        return false;
    };
    RequestionOptionIterator.prototype.next = function () {
        return this.requestionOption.pages[this.currentPageNumber++];
    };
    return RequestionOptionIterator;
}());
exports.RequestionOptionIterator = RequestionOptionIterator;
//# sourceMappingURL=RequestionOptionIterator.js.map