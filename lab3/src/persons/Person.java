package persons;

public class Person {
    protected String name;
    protected int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return this.name;
    }
    public int getAge() {
        return this.age;
    }

    public void setName(String new_name) {
        this.name = new_name;
    }
    public void setAge(int new_age) {
        this.age = new_age;
    }

    @Override
    public String toString() {
        return  "persons.Person {" +
                "name='" + (this.name != null ? this.name : "null") + "', " +
                "age=" + age + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        final Person o = (Person)obj;
        return (this.name.equals(o.name) && this.age == o.age);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 53 * hash + this.age;
        return hash;
    }
}
