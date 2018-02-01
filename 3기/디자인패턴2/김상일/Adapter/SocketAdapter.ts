import {Volt} from './Volt';

export interface SocketAdapter {
    get120Volt(): Volt;
    get12Volt(): Volt;
    get3Volt(): Volt;
}
