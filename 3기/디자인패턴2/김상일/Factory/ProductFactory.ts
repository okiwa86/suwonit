import {Factory} from "./Factory";
import {IProduct} from "./Product/IProduct";
import {ProductA} from "./Product/ProductA";
import {ProductB} from "./Product/ProductB";

export class ProductFactory extends Factory{

    create(type: string): IProduct {
        var product:IProduct = null;
        switch(type){
            case 'A':
                product = new ProductA();
                break;
            case 'B':
                product = new ProductB();
                break;
        }
        return product;
    }
}