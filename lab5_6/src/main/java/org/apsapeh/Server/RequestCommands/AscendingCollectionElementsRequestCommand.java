package org.apsapeh.Server.RequestCommands;

import org.apsapeh.General.Sender.SenderEnum;
import org.apsapeh.General.Sender.SenderInterface;
import org.apsapeh.General.Task.Vehicle;
import org.apsapeh.General.Wrappers.Wrapper;
import org.apsapeh.Server.Collections.TaskCollection;
import org.apsapeh.Server.Server;

import java.util.Arrays;

public class AscendingCollectionElementsRequestCommand implements RequestCommandInterface {
    private final TaskCollection collection;
    private final Wrapper<SenderInterface> responseSender;

    public AscendingCollectionElementsRequestCommand(
            TaskCollection collection,
            Wrapper<SenderInterface> responseSender
    ) {
        this.collection = collection;
        this.responseSender = responseSender;
    }

    @Override
    public void execute(String[] args) {
        Vehicle[] vehicles = collection.toArray(new Vehicle[0]);
        Arrays.sort(vehicles);

        StringBuilder response = new StringBuilder("Элементы коллекции:\n");
        for (Vehicle vehicle : vehicles)
            response.append("\t").append(vehicle.toString()).append("\n");

        if (response.toString().equals("Элементы коллекции:\n"))
            response = new StringBuilder("Коллекция пуста");

        responseSender.get().send(
                SenderEnum.PRINT_TEXT,
                new String[] {response.toString()}
        );
    }
}
