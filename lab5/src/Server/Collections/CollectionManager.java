package Server.Collections;

import java.util.HashSet;

public class CollectionManager <T> extends HashSet<T>{
    private final java.time.LocalDate creationDate;

    public CollectionManager() {
        creationDate = java.time.LocalDate.now();
    }

    public boolean add(T o) {
        boolean r = super.add(o);
        sort();
        return r;
    }

    public void sort() {

    }

    public void clear() {
        super.clear();
    }

    /*public boolean removeIndex(int index) {
        if (index >= 0 && index < super.size())
            return false;

        T r = super.toArray()[new T[this.size()]];
        T r = super.remove();
        sort();
        return r != null;
    }

    public boolean removeID(Long id) {
        for (int index=0; index < this.collection.size(); ++index)
            if (this.collection.get(index).getID().equals(id)) {
                this.collection.remove();
                sort();
                return true;
            }
        return false;
    }

    public LinkedList<Vehicle> getCollection() {
        return this.collection;
    }*/

    @Override
    public String toString() {
        return "Server.Collections.CollectionManager {" + "init_date=" + creationDate +
                ", collection_type=" + super.getClass().getName() +
                ", collection_size=" + super.size() +
                '}';
    }

    public String toPrettyString() {
        return "Коллекция:\n" +
                "\tДата создания: " + creationDate + "\n" +
                "\tТип коллекции: " + super.getClass().getName() + "<Server.TaskClasses.Vehicle>\n" +
                "\tКоличество элементов: " + super.size();
    }
}
