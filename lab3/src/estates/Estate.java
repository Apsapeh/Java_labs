public abstract class Estate {
    protected String address;

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String new_address) {
        this.address = new_address;
    }

    @Override
    public String toString() {
        return  "Estate {" +
                "address='" + (this.address != null ? this.address : "null") + "'}";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj == this)
            return true;
        return obj instanceof Estate;
    }

    @Override
    public int hashCode() {
        return 53 * this.address.hashCode();
    }
}
