package estates;

import product.ProductTypeEnum;
import exceptions.AddressTakenException;

import java.util.HashMap;
import java.util.Vector;

public abstract class Estate {
    static Vector<Integer> taken_addresses = new Vector<Integer>();
    protected String address;

    public Estate() {}
    public Estate(String address) throws AddressTakenException {
        if (taken_addresses.contains(address.hashCode())) {
            throw new AddressTakenException();
        }
        taken_addresses.add(address.hashCode());
        this.address = address;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String new_address) {
        this.address = new_address;
    }

    public abstract HashMap<ProductTypeEnum, Integer> produce();

    @Override
    public String toString() {
        return  "estates.Estate {" +
                "address='" + (this.address != null ? this.address : "null") + "'}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        final Estate o = (Estate)obj;
        return this.address.equals(o.address);
    }

    @Override
    public int hashCode() {
        return 53 * this.address.hashCode();
    }
}
