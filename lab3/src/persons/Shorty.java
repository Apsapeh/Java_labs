package persons;

import product.ProductTypeEnum;

public abstract class Shorty extends Person {
    protected ProductTypeEnum type;

    public Shorty(String name, int age) {
        super(name, age);
    }

    @Override
    public String toString() {
        return  "persons.Person {" +
                "inherit_class='" + super.toString() + "', " +
                "type=" + this.type +
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

        return type.equals(((Shorty) obj).type);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + super.hashCode();
        hash = 53 * hash + (this.type != null ? this.type.hashCode() : 0);
        return hash;
    }
}
