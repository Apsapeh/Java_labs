package Server.RequestCommands;

import General.Task.Vehicle;
import General.Utilities.File;
import Server.Server;

public class SaveCollectionRequestCommand implements RequestCommandInterface{
    Server server;

    public SaveCollectionRequestCommand(Server server) {
        this.server = server;
    }

    @Override
    public void execute(String[] args) {
        String json_string = "[\n";
        int i = 0;
        for (Vehicle vehicle : server.getCollectionManager()) {
            json_string += String.format(
                    "\t{\"id\": %d, \"name\": \"%s\", \"x\": %d, \"y\": %d,\"creationDate\": \"%s\", \"enginePower\": %d, \"type\": \"%s\", \"fuelType\": \"%s\"}",
                    vehicle.getID(),
                    vehicle.getName().replace("\"", "\\\""),
                    vehicle.getCoordinates().getX(),
                    vehicle.getCoordinates().getY(),
                    vehicle.getCreationDate().toString(),
                    vehicle.getEnginePower(),
                    vehicle.getType().toString(),
                    vehicle.getFuelType().toString()
            );

            if (i++ < server.getCollectionManager().size() - 1)
                json_string += ",\n";
        }
        json_string += "\n]";
        File.write(server.getCollectionDBPath(), json_string);
        //server.getCollectionManager().saveCollection();

    }
}
