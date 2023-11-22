package mypaca;

public class Product {
    public ProductTypeEnum type;
    public int count;

    public Product(ProductTypeEnum type, int count) {
        this.type = type;
        this.count = count;
    }

    @Override
    public String toString() {
        return  "Product {" +
                "type='" + type.toString() + ", " +
                "count=" + count + "'}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        final Product o = (Product) obj;
        return (this.type.equals(o.type) && this.count == o.count);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + (this.type != null ? this.type.hashCode() : 0);
        hash = 53 * hash + this.count;
        return hash;
    }
}