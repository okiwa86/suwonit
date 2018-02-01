import {Crawler} from './Crawler';
export class NaverCrawler extends Crawler{

    constructor(baseUrl: string){
        super(baseUrl);
    }

    protected parsing(response: string): any {
        console.log(`${this.baseUrl} is on parsing`)
        return 'parsing';
    }

    protected request(url: string): any {
        console.log(`request to ${this.baseUrl}`);
        return 'response';
    }
}