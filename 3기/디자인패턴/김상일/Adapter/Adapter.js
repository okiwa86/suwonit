"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var Socket_1 = require("./Socket");
var Volt_1 = require("./Volt");
var Adapter = (function () {
    function Adapter() {
        this.socket = new Socket_1.Socket();
    }
    Adapter.prototype.get120Volt = function () {
        return this.socket.getVolt();
    };
    Adapter.prototype.get12Volt = function () {
        return new Volt_1.Volt(12);
    };
    Adapter.prototype.get3Volt = function () {
        return new Volt_1.Volt(3);
    };
    return Adapter;
}());
exports.Adapter = Adapter;
//# sourceMappingURL=Adapter.js.map