package Server.RequestCommands;

import General.Response.ResponseEnum;
import General.Task.Vehicle;
import Server.Server;

import java.util.Arrays;

public class AscendingCollectionElementsRequestCommand implements RequestCommandInterface {
    Server server;

    public AscendingCollectionElementsRequestCommand(Server server) {
        this.server = server;
    }

    @Override
    public void execute(String[] args) {
        Vehicle[] vehicles = server.getCollectionManager().toArray(new Vehicle[0]);
        Arrays.sort(vehicles);

        String response = "Элементы коллекции:\n";
        for (Vehicle vehicle : vehicles)
            response += "\t" + vehicle.toString() + "\n";

        if (response.equals("Элементы коллекции:\n"))
            response = "Коллекция пуста";

        server.getResponseSender().sendResponse(
                ResponseEnum.PRINT_TEXT,
                new String[] {response}
        );
    }
}
