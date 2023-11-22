package estates;

public abstract class Estate {
    protected String address;

    public Estate() {}
    public Estate(String address) {
        this.address = address;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String new_address) {
        this.address = new_address;
    }

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
