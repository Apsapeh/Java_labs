package server.RequestCommands;

import general.Task.Vehicle;
import general.Utilities.File;
import general.Wrappers.Wrapper;
import server.Collections.TaskCollection;

public class SaveCollectionRequestCommand implements RequestCommandInterface {
    private final TaskCollection collection;
    private final Wrapper<String> collectionDBPath;

    public SaveCollectionRequestCommand(
            TaskCollection collection,
            Wrapper<String> collectionDBPath
    ) {
        this.collection = collection;
        this.collectionDBPath = collectionDBPath;
    }

    @Override
    public void execute(String[] args) {
        StringBuilder json_string = new StringBuilder("[\n");
        int i = 0;
        for (Vehicle vehicle : collection) {
            json_string.append(String.format(
                    "\t{\"id\": %d, \"name\": \"%s\", \"x\": %d, \"y\": %d,\"creationDate\": \"%s\", \"enginePower\": %d, \"type\": \"%s\", \"fuelType\": \"%s\"}",
                    vehicle.getID(),
                    vehicle.getName().replace("\"", "\\\""),
                    vehicle.getCoordinates().getX(),
                    vehicle.getCoordinates().getY(),
                    vehicle.getCreationDate().toString(),
                    vehicle.getEnginePower(),
                    vehicle.getType().toString(),
                    vehicle.getFuelType().toString()
            ));

            if (i++ < collection.size() - 1)
                json_string.append(",\n");
        }
        json_string.append("\n]");
        File.write(collectionDBPath.get(), json_string.toString());
        //server.getCollectionManager().saveCollection();

    }
}
