package Server.RequestCommands;

import General.Response.ResponseEnum;
import General.Task.Vehicle;
import Server.Server;

public class CollectionElementsRequestCommand implements RequestCommandInterface {
    Server server;

    public CollectionElementsRequestCommand(Server server) {
        this.server = server;
    }

    @Override
    public void execute(String[] args) {
        String response = "Элементы коллекции:\n";
        for (Vehicle vehicle : server.getCollectionManager())
            response += "\t" + vehicle.toString() + "\n";

        if (response.equals("Элементы коллекции:\n"))
            response = "Коллекция пуста";

        server.getResponseSender().sendResponse(
                ResponseEnum.PRINT_TEXT,
                new String[] {response}
        );
    }
}
