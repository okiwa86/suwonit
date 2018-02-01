import {Iterator} from './Iterator';
import {Bucket} from './Bucket';

export class BucketIterator implements Iterator{

    private bucket: Bucket;
    private index: number;

    constructor(bucket:Bucket){
        this.bucket = bucket;
        this.index = 0;
    }

    hasNext(): boolean {
        let hasNext = false;
        if(this.index < this.bucket.getLength()){
            hasNext = true;
        }
        return hasNext;
    }

    next(): object{
        return this.bucket.get(this.index++);
    }
}