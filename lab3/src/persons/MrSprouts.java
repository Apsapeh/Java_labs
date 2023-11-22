package persons;

import estates.Factory;
import estates.Latifundia;
import estates.ManufactureFactory;
import estates.SugarFactory;
import product.ProductTypeEnum;

import java.util.HashMap;
import java.util.Vector;

public class MrSprouts extends Person {
    public Vector<Factory> factories = new Vector<>();

    public MrSprouts() {
        super("Mr. Sprouts", 57);

        factories.addElement(new ManufactureFactory("Something street, 1"));
        for (int i = 1; i <= 30; ++i)
            factories.addElement(new SugarFactory("Something street 2, " + 1 + i));
        for (int i = 1; i <= 4; ++i) {
            Latifundia lf = new Latifundia("Something street 3, " + 1 + i);
            lf.workers.addElement(new SugarShorty("Никто", 10));
            factories.addElement(lf);
        }
    }

    public void manage() {
        HashMap<ProductTypeEnum, Integer> result = new HashMap<>();
        for (Factory f : factories) {
            final HashMap<ProductTypeEnum, Integer> product = f.produce();
            product.forEach((key, value) -> result.merge(key, value, Integer::sum));
        }
        System.out.println(result.toString());
    }
}
