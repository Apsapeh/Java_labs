package Server.Commands;

import Server.Collections.CollectionManager;
import Server.TaskClasses.Vehicle;


public class ClearCommand implements CommandInterface {
    CollectionManager<Vehicle> collection;

    public ClearCommand(CollectionManager<Vehicle> vehicles) {
        this.collection = vehicles;
    }

    public void execute (String[] args) {
        collection.clear();
        System.out.println("Коллекция очищена");
    }

    public String getDescription () {
        return "Очищает коллекцию";
    }
}
