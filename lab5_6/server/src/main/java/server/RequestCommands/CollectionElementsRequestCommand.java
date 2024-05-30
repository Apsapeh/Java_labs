package server.RequestCommands;

import general.Sender.SenderEnum;
import general.Sender.SenderInterface;
import general.Task.Vehicle;
import general.Wrappers.Wrapper;
import server.Collections.TaskCollection;

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
