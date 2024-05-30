package server.RequestCommands;

import general.Task.Coordinates;
import general.Task.FuelType;
import general.Task.Vehicle;
import general.Task.VehicleType;
import server.Collections.TaskCollection;

public class AddElementRequestCommand implements RequestCommandInterface{
    private final TaskCollection collection;

    public AddElementRequestCommand(TaskCollection collection) {
        this.collection = collection;
    }

    @Override
    public void execute(String[] args) {
        Vehicle vehicle = new Vehicle(
                args[0],
                new Coordinates(Integer.parseInt(args[1]), Integer.parseInt(args[2])),
                Long.parseLong(args[3]),
                VehicleType.valueOf(args[4]),
                FuelType.valueOf(args[5])
        );

        collection.add(vehicle);
    }
}
