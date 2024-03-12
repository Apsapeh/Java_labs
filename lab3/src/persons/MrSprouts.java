package persons;

import estates.Estate;
import estates.Latifundia;
import estates.ManufactureFactory;
import estates.SugarFactory;
import exceptions.AddressTakenException;
import exceptions.BarnOwerflowException;
import product.ProductTypeEnum;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;

public class MrSprouts extends Person implements Manger{
    public Vector<Estate> factories = new Vector<>();
    HashMap<ProductTypeEnum, Integer> barn = new HashMap<>();

    private interface Compare {
        boolean LM(int a, int b);
    }

    class Logger {
        public void Log() {
            class StrConcat {
                public static String cat(String... s) {
                    for (int n = 1; n < s.length; ++n)
                        s[0] += s[n];
                    return s[0];
                }
            }

            String s = "Estates:\n";
            for (Estate f : factories) {
                s = StrConcat.cat(s, "  ", f.toString(), "\n");
            }
            s = StrConcat.cat(s, "Barn: \n   ", barn.toString());

            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("log.txt", true));
                writer.append(StrConcat.cat(s, "\n\n"));
                writer.close();
            }
            catch (IOException e) {
                System.out.println("Log error: " + e.toString());
            }
        }
    }

    public MrSprouts() {
        super("Mr. Sprouts", 57);

        try {
            factories.addElement(new ManufactureFactory("Something street, 1"));

            for (int i = 1; i <= 30; ++i)
                factories.addElement(new SugarFactory("Something street 2, " + 1 + i));
            for (int i = 1; i <= 4; ++i) {
                Latifundia lf = new Latifundia("Something street 3, " + 1 + i);
                lf.workers.addElement(new WheatShorty("Никто", 10));
                factories.addElement(lf);
            }
        }
        catch (AddressTakenException e){
            System.out.println("Недвижимость с этим адресом уже существует");
        }
        Logger logger = new Logger();
        logger.Log();
    }

    public void manage() {
        for (Estate f : factories) {
            final HashMap<ProductTypeEnum, Integer> product = f.produce();
            product.forEach((key, value) -> barn.merge(key, value, Integer::sum));
        }

        System.out.println(barn);

        Compare compare = new Compare() {
            @Override
            public boolean LM(int a, int b) {
                return a > b;
            }
        };
        for (Integer value : barn.values())
            if (compare.LM(value, 6000)) throw new BarnOwerflowException();

        Logger logger = new Logger();
        logger.Log();
    }
}
