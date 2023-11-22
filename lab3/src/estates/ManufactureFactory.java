class  ManufactureFactory extends Estate implements Factory {
    @Override
    public void produce() {
        // Производит ткань
    }

    @Override
    public String toString() {
        final Estate in = (Estate)this;
        return "ManufactureFactory {\n\t" + "in_class = " + super.toString() + "\n}";
    }

    @Override
    public boolean equals(Object obj) {
        return this.getClass() == obj.getClass();
    }

    @Override
    public int hashCode() {
        return 0;
    }
}