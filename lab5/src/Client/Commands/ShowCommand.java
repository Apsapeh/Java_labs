package Client.Commands;

import Client.Executor;
import Server.TaskClasses.Vehicle;


public class ShowCommand implements CommandInterface {
    Executor executor;

    public ShowCommand(Executor executor) {
        this.executor = executor;
    }

    public void execute (String[] args) {
        executor.getPrintSender().send("Элементы коллекции:");
        for (Vehicle vehicle : executor.getCollection()) {
            executor.getPrintSender().send(vehicle.toString());
        }
    }

    public String getDescription () {
        return "Выдает все элементы коллекции в строковом представлении";
    }
}
