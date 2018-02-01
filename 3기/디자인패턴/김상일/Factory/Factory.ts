
import {IProduct} from "./Product/IProduct";

export abstract class Factory {
    abstract create(type: string): IProduct;
}