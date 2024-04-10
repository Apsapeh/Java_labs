package Server.RequestCommands;

import General.Response.ResponseEnum;
import General.Task.Coordinates;
import General.Task.FuelType;
import General.Task.Vehicle;
import General.Task.VehicleType;
import Server.Server;

public class UpdateElementRequestCommand implements RequestCommandInterface{
    Server server;

    public UpdateElementRequestCommand(Server server) {
        this.server = server;
    }

    @Override
    public void execute(String[] args) {
        Integer id = Integer.parseInt(args[0]);
        Vehicle vehicle = server.getCollectionManager().getById(id);

        if (vehicle == null) {
            server.getResponseSender().sendResponse(
                    ResponseEnum.PRINT_TEXT,
                    new String [] {"Элемент с id " + id + " не найден"}
            );
            return;
        }

        vehicle.setName(args[1]);
        vehicle.setCoordinates(new Coordinates(Integer.parseInt(args[2]), Integer.parseInt(args[3])));
        vehicle.setEnginePower(Long.parseLong(args[4]));
        vehicle.setType(VehicleType.valueOf(args[5]));
        vehicle.setFuelType(FuelType.valueOf(args[6]));

        /*Vehicle vehicle = new Vehicle(
                args[0],
                new Coordinates(Integer.parseInt(args[1]), Integer.parseInt(args[2])),
                Long.parseLong(args[3]),
                VehicleType.valueOf(args[4]),
                FuelType.valueOf(args[5])
        );*/

    }
}
