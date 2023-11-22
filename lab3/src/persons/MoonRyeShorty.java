package persons;

import product.*;

public class CottonShorty extends Person implements Worker {
    public CottonShorty(String name, int age) {
        super(name, age);
    }

    @Override
    public Product work() {
        return new Product(ProductTypeEnum.COTTON, 3);
    }
}
