import {Iterator} from '../Iterator/Iterator';
import {RequestionOption} from './RequestOption';

export class RequestionOptionIterator implements Iterator{

    private requestionOption: RequestionOption;
    private currentPageNumber = 0;

    constructor(requestionOption: RequestionOption){
        this.requestionOption = requestionOption;
    }


    hasNext(): boolean {
        console.log('send next page existence check!');
        if(this.currentPageNumber <= this.requestionOption.pages.length){
            console.log('there is next item');
            return true;
        }
        console.log('this iteration is last');
        return false;
    }

    next(): any {
        return this.requestionOption.pages[this.currentPageNumber++];
    }
}