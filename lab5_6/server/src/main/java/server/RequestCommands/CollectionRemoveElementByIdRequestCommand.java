package server.RequestCommands;

import general.Sender.SenderEnum;
import general.Sender.SenderInterface;
import general.Task.Vehicle;
import general.Wrappers.Wrapper;
import server.Collections.TaskCollection;

public class CollectionRemoveElementByIdRequestCommand implements RequestCommandInterface {
    private final TaskCollection collection;
    private final Wrapper<SenderInterface> sender;

    public CollectionRemoveElementByIdRequestCommand(
            TaskCollection collection,
            Wrapper<SenderInterface> sender
    ) {
        this.collection = collection;
        this.sender = sender;
    }


    @Override
    public void execute(String[] args) {
        Integer id = Integer.parseInt(args[0]);
        Vehicle vehicle = collection.removeById(id);

        if (vehicle == null) {
            sender.get().send(
                    SenderEnum.PRINT_TEXT,
                    new String[] {"Элемент с id " + id + " не найден"}
            );
        }
    }
}
