package Server.Commands;

import Server.Collections.CollectionManager;
import Server.TaskClasses.Vehicle;


public class ShowCommand implements CommandInterface {
    CollectionManager<Vehicle> collection;

    public ShowCommand(CollectionManager<Vehicle> vehicles) {
        this.collection = vehicles;
    }

    public void execute (String[] args) {
        System.out.println("Элементы коллекции:");
        for (Vehicle vehicle : collection) {
            System.out.println(vehicle);
        }
    }

    public String getDescription () {
        return "Выдает все элементы коллекции в строковом представлении";
    }
}
