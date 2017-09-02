"use strict";
var __extends = (this && this.__extends) || (function () {
    var extendStatics = Object.setPrototypeOf ||
        ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
        function (d, b) { for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p]; };
    return function (d, b) {
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();
Object.defineProperty(exports, "__esModule", { value: true });
var Crawler_1 = require("./Crawler");
var NaverCrawler = (function (_super) {
    __extends(NaverCrawler, _super);
    function NaverCrawler(baseUrl) {
        return _super.call(this, baseUrl) || this;
    }
    NaverCrawler.prototype.parsing = function (response) {
        console.log(this.baseUrl + " is on parsing");
        return 'parsing';
    };
    NaverCrawler.prototype.request = function (url) {
        console.log("request to " + this.baseUrl);
        return 'response';
    };
    return NaverCrawler;
}(Crawler_1.Crawler));
exports.NaverCrawler = NaverCrawler;
//# sourceMappingURL=NaverCrawler.js.map