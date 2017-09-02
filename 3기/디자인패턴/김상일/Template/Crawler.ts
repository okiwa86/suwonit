import {RequestOption} from './RequestOption';
import {Iteratble} from '../Iterator/Iteratble';
import {Iterator} from '../Iterator/Iterator';

export abstract class Crawler{

    public baseUrl: string;
    private requestOption: Iteratble;
    private iterator: Iterator;
    
    constructor(baseUrl: string) {
        this.baseUrl = baseUrl;
        this.requestOption =  new RequestOption();
        this.iterator = this.requestOption.getIterator();
    }

    /**
     * Method Logic For Crawling
     * @returns {Promise}
     */
    public *collect(): any{
        var options;
        var response;
        while(this.iterator.hasNext()){
            options = this.iterator.next();
            console.log('get http request base on requestOption');
            response = this.request('options.url');
            yield this.parsing(response);
        }
    }
    protected abstract parsing(response: string): any;
    protected abstract request(url: string): any;
}