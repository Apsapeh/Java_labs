package persons;

import product.Product;
import product.ProductTypeEnum;

public class MoonRyeShorty extends Shorty{
    public MoonRyeShorty(String name, int age) {
        super(name, age);
        this.type = ProductTypeEnum.MOON_RYE;
    }

    @Override
    public Product work() {
        return new Product(this.type, 3);
    }
}
