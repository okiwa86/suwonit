import {Iteratble} from '../Iterator/Iteratble';
import {Iterator} from '../Iterator/Iterator';
import {RequestionOptionIterator} from './RequestionOptionIterator';

export class RequestOption implements Iteratble{

    pages: object[];

    getIterator(): Iterator {
        return new RequestionOptionIterator(this);
    }

    getLength(): number {
        return this.pages.length;
    }

    get(index: number): Object {
        return this.pages[index];
    }
}