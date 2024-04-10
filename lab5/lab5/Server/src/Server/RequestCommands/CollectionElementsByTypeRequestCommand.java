package Server.RequestCommands;

import General.Task.VehicleType;
import Server.Server;
import General.Response.ResponseEnum;
import General.Task.Vehicle;

public class CollectionElementsByTypeRequestCommand implements RequestCommandInterface{
    Server server;

    public CollectionElementsByTypeRequestCommand(Server server) {
        this.server = server;
    }

    @Override
    public void execute(String[] args) {
        VehicleType type = VehicleType.valueOf(args[0]);
        String response = "Элементы коллекции:\n";
        for (Vehicle vehicle : server.getCollectionManager()) {
            if (vehicle.getType() == type)
                response += "\t" + vehicle.toString() + "\n";
        }

        if (response.equals("Элементы коллекции:\n"))
            response = "Коллекция пуста";

        server.getResponseSender().sendResponse(
                ResponseEnum.PRINT_TEXT,
                new String[] {response}
        );
    }
}
