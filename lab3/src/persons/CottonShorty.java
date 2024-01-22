package persons;

import product.*;

public class CottonShorty extends Shorty{
    public CottonShorty(String name, int age) {
        super(name, age);
        this.type = ProductTypeEnum.COTTON;
    }

    @Override
    public Product work() {
        return new Product(this.type, 3);
    }
}
