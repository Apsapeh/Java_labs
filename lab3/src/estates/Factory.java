package estates;

import java.util.HashMap;
import product.ProductTypeEnum;

public interface Factory {
    HashMap<ProductTypeEnum, Integer> produce();
}