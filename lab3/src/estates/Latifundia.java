package estates;

import exceptions.AddressTakenException;
import persons.Shorty;
import product.ProductTypeEnum;
import product.Product;

import java.util.HashMap;
import java.util.Vector;

public class Latifundia extends Estate{
    public Vector<Shorty> workers = new Vector<>();

    public Latifundia() {
        super();
    }
    public Latifundia(String address) throws AddressTakenException {
        super(address);
    }

    public HashMap<ProductTypeEnum, Integer> produce() {
        HashMap<ProductTypeEnum, Integer> result = new HashMap<>();
        for (Shorty w : workers) {
            final Product pr = w.work();
            result.merge(pr.type, pr.count, Integer::sum);
        }
        return result;
    }

    @Override
    public String toString() {
        return  "estates.Latifundia {" +
                "inherit_class='" + super.toString() + "', " +
                "workers=" + this.workers.toString() +
                "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        if (!super.equals(obj))
            return false;

        return workers.equals(((Latifundia) obj).workers);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash * super.hashCode();
        hash = 53 * hash * workers.hashCode();
        return hash;
    }
}
