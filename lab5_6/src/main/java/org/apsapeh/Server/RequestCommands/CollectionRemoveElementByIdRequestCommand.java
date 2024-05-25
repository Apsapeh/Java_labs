package org.apsapeh.Server.RequestCommands;

import org.apsapeh.General.Sender.SenderEnum;
import org.apsapeh.General.Sender.SenderInterface;
import org.apsapeh.General.Wrappers.Wrapper;
import org.apsapeh.Server.Collections.TaskCollection;
import org.apsapeh.Server.Server;
import org.apsapeh.General.Task.Vehicle;

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
