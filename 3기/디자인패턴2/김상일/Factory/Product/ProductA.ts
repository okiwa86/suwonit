import {IProduct} from "./IProduct";
export class ProductA implements IProduct{
    toString(): string{
        return 'ProductA';
    }
}