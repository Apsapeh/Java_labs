package Client.Commands;

import Client.Client;
import General.Request.RequestEnum;


public class ShowCommand implements CommandInterface {
    Client executor;

    public ShowCommand(Client executor) {
        this.executor = executor;
    }

    public void execute (String[] args) {
        String [] arguments = new String[1];
        arguments[0] = "show";
        executor.getRequestSender().sendRequest(
                RequestEnum.GET_COLLECTION_ELEMENTS, arguments
        );
        /*executor.getPrintSender().send("Элементы коллекции:");
        for (Vehicle vehicle : executor.getCollection()) {
            executor.getPrintSender().send(vehicle.toString());
        }*/
    }

    public String getDescription () {
        return "Выдает все элементы коллекции в строковом представлении";
    }
}
