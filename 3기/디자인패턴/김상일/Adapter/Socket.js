"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var Volt_1 = require("./Volt");
var Socket = (function () {
    function Socket() {
    }
    Socket.prototype.getVolt = function () {
        return new Volt_1.Volt(120);
    };
    return Socket;
}());
exports.Socket = Socket;
//# sourceMappingURL=Socket.js.map