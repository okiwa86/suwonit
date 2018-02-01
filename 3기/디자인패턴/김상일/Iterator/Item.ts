export class Item {

    private name: string;
    private price: number;

    constructor(name: string, price: number){
        this.name = name;
        this.price = price;
    }

    info(): string{
        return `상품명: ${this.name}, 가격: ${this.price}`;
    }
}