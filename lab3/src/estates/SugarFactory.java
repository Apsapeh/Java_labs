package estates;

import product.ProductTypeEnum;

import java.util.HashMap;

public class SugarFactory extends Estate implements Factory {
    public SugarFactory() {
        super();
    }
    public SugarFactory(String address) {
        super(address);
    }
    @Override
    public HashMap<ProductTypeEnum, Integer> produce() {
        HashMap<ProductTypeEnum, Integer> result = new HashMap<>();
        result.put(ProductTypeEnum.SUGAR, 163);
        return result;
    }

    @Override
    public String toString() {
        return  "estates.SugarFactory {" +
                "inherit_class='" + super.toString() + "'" +
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

        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash * super.hashCode();
        return hash;
    }
}
