package server.RequestCommands;

import general.Sender.SenderEnum;
import general.Sender.SenderInterface;
import general.Task.Vehicle;
import general.Task.VehicleType;
import general.Wrappers.Wrapper;
import server.Collections.TaskCollection;

public class CollectionElementsByTypeRequestCommand implements RequestCommandInterface {
    private final TaskCollection collection;
    private final Wrapper<SenderInterface> sender;

    public CollectionElementsByTypeRequestCommand(
            TaskCollection collection,
            Wrapper<SenderInterface> sender
    ) {
        this.collection = collection;
        this.sender = sender;
    }

    @Override
    public void execute(String[] args) {
        VehicleType type = VehicleType.valueOf(args[0]);
        StringBuilder response = new StringBuilder("Элементы коллекции:\n");
        for (Vehicle vehicle : collection) {
            if (vehicle.getType() == type)
                response.append("\t").append(vehicle).append("\n");
        }

        if (response.toString().equals("Элементы коллекции:\n"))
            response = new StringBuilder("Коллекция пуста");

        sender.get().send(
                SenderEnum.PRINT_TEXT,
                new String[] {response.toString()}
        );
    }
}
