package Server.Commands;

import Server.Collections.CollectionManager;
import Server.TaskClasses.Vehicle;


public class InfoCommand implements CommandInterface {
    CollectionManager<Vehicle> collection;

    public InfoCommand(CollectionManager<Vehicle> vehicles) {
        this.collection = vehicles;
    }

    public void execute (String[] args) {
        System.out.println(collection.toPrettyString());

    }

    public String getDescription () {
        return "Выводит информацию о коллекции";
    }
}
