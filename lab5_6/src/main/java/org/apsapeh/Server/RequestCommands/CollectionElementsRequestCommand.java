package org.apsapeh.Server.RequestCommands;

import org.apsapeh.General.Sender.SenderEnum;
import org.apsapeh.General.Sender.SenderInterface;
import org.apsapeh.General.Task.Vehicle;
import org.apsapeh.General.Wrappers.Wrapper;
import org.apsapeh.Server.Collections.TaskCollection;
import org.apsapeh.Server.Server;

public class CollectionElementsRequestCommand implements RequestCommandInterface {
    private final TaskCollection collection;
    private final Wrapper<SenderInterface> sender;

    public CollectionElementsRequestCommand(
            TaskCollection collection,
            Wrapper<SenderInterface> sender
    ) {
        this.collection = collection;
        this.sender = sender;
    }

    @Override
    public void execute(String[] args) {
        StringBuilder response = new StringBuilder("Элементы коллекции:\n");
        for (Vehicle vehicle : collection)
            response.append("\t").append(vehicle.toString()).append("\n");

        if (response.toString().equals("Элементы коллекции:\n"))
            response = new StringBuilder("Коллекция пуста");

        sender.get().send(
                SenderEnum.PRINT_TEXT,
                new String[] {response.toString()}
        );
    }
}
