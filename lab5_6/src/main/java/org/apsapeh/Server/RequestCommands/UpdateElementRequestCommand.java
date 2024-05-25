package org.apsapeh.Server.RequestCommands;

import org.apsapeh.General.Sender.SenderEnum;
import org.apsapeh.General.Sender.SenderInterface;
import org.apsapeh.General.Task.Coordinates;
import org.apsapeh.General.Task.FuelType;
import org.apsapeh.General.Task.Vehicle;
import org.apsapeh.General.Task.VehicleType;
import org.apsapeh.General.Wrappers.Wrapper;
import org.apsapeh.Server.Collections.TaskCollection;
import org.apsapeh.Server.Server;

public class UpdateElementRequestCommand implements RequestCommandInterface{
    private final TaskCollection collection;
    private final Wrapper<SenderInterface> sender;

    public UpdateElementRequestCommand(
            TaskCollection collection,
            Wrapper<SenderInterface> sender
    ) {
        this.collection = collection;
        this.sender = sender;
    }

    @Override
    public void execute(String[] args) {
        Integer id = Integer.parseInt(args[0]);
        Vehicle vehicle = collection.getById(id);

        if (vehicle == null) {
            sender.get().send(
                    SenderEnum.PRINT_TEXT,
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
