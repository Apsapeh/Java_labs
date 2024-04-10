package Server.RequestCommands;

import General.Task.Coordinates;
import General.Task.Vehicle;
import General.Task.VehicleType;
import General.Task.FuelType;
import Server.Server;

public class AddElementRequestCommand implements RequestCommandInterface{
    Server server;

    public AddElementRequestCommand(Server server) {
        this.server = server;
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

        server.getCollectionManager().add(vehicle);
    }
}
