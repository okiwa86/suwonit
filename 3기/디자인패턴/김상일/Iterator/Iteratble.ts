import {Iterator} from './Iterator';

export interface Iteratble {
    getIterator():Iterator;
    getLength():number;
    get(index: number): object;
}