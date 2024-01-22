package persons;

import product.Product;
import product.ProductTypeEnum;

public class WheatShorty extends Shorty{
    public WheatShorty(String name, int age) {
        super(name, age);
        this.type = ProductTypeEnum.WHEAT;
    }

    @Override
    public Product work() {
        return new Product(this.type, 3);
    }
}
