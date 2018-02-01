import {Iteratble} from './Iteratble';
import {Iterator} from './Iterator';
import {BucketIterator} from './BucketIterator';
import {Item} from './Item';

export class Bucket implements Iteratble{

    private repository;

    constructor(){
        this.repository = [];
    }

    addItem(item: Item){
        this.repository.push(item);
    }

    get(index: number): Object {
        return this.repository[index];
    }

    getIterator(): Iterator {
        return new BucketIterator(this);
    }

    getLength(): number {
        return this.repository.length;
    }

}