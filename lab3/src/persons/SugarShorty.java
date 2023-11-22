package persons;

import product.Product;
import product.ProductTypeEnum;

public class SugarShorty extends Shorty implements Worker {
    public SugarShorty(String name, int age) {
        super(name, age);
        this.type = ProductTypeEnum.SUGAR_BEET;
    }

    @Override
    public Product work() {
        return new Product(this.type, 3);
    }
}
