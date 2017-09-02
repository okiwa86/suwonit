import {NaverCrawler} from './Template/NaverCrawler';
/**
 * Template Pattern
 */
var crawler = new NaverCrawler('www.naver.com');
for(var rs of crawler.collect()){
    console.log(rs);
}

/**
 * Iterator Pattern
 */
import {Bucket} from './Iterator/Bucket';
import {Item} from './Iterator/Item';
import {Iterator} from './Iterator/Iterator';

var bucket = new Bucket();
for(var i = 0; i < 10; i++){
    let item = new Item(`상품 ${i}`, 12000 + i * 10);
    bucket.addItem(item);
}

var iterator:Iterator = bucket.getIterator();
while(iterator.hasNext()){
    let item = iterator.next();
    console.log(item.info());
}