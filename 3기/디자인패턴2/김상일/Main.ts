import {NaverCrawler} from './Template/NaverCrawler';
import {Bucket} from './Iterator/Bucket';
import {Item} from './Iterator/Item';
import {Iterator} from './Iterator/Iterator';
import {ProductFactory} from "./Factory/ProductFactory";
import {IProduct} from "./Factory/Product/IProduct";
import {DatabaseA} from "./Singleton/DatabaseA";
import {DatabaseB} from "./Singleton/DatabaseB";

var adapterPattern = function(){

}

var factoryPattern = function(){
    let productFactory = new ProductFactory();
    let productList: IProduct[] = [];
    productList.push(productFactory.create('A'));
    productList.push(productFactory.create('A'));
    productList.push(productFactory.create('B'));

    for(let product of productList){
        console.log(product.toString());
    }
}

var templatePattern = function(){
    var crawler = new NaverCrawler('www.naver.com');
    for(var rs of crawler.collect()){
        console.log(rs);
    }
}

var iteratorPattern = function(){
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
}

var singletonPattern = function(){
    DatabaseA.getInstance().write();
    DatabaseB.getInstance().write();
}

// factoryPattern();
singletonPattern();