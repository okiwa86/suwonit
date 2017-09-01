"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var RequestionOptionIterator_1 = require("./RequestionOptionIterator");
var RequestOption = (function () {
    function RequestOption() {
    }
    RequestOption.prototype.getIterator = function () {
        return new RequestionOptionIterator_1.RequestionOptionIterator(this);
    };
    RequestOption.prototype.getLength = function () {
        return this.pages.length;
    };
    RequestOption.prototype.get = function (index) {
        return this.pages[index];
    };
    return RequestOption;
}());
exports.RequestOption = RequestOption;
//# sourceMappingURL=RequestOption.js.map