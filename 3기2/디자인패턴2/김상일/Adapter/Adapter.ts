import {Socket} from './Socket';
import {SocketAdapter} from './SocketAdapter';
import {Volt} from './Volt';

export class Adapter implements SocketAdapter {

    private socket: Socket = new Socket();

    get120Volt(): Volt {
        return this.socket.getVolt();
    }

    get12Volt(): Volt {
        return new Volt(12);
    }

    get3Volt(): Volt {
        return new Volt(3);
    }
}