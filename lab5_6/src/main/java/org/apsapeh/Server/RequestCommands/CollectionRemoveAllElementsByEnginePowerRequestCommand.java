package org.apsapeh.Server.RequestCommands;

import org.apsapeh.General.Sender.SenderInterface;
import org.apsapeh.General.Wrappers.Wrapper;
import org.apsapeh.Server.Collections.TaskCollection;
import org.apsapeh.Server.Server;

public class CollectionRemoveAllElementsByEnginePowerRequestCommand implements RequestCommandInterface {
    private final TaskCollection collection;

    public CollectionRemoveAllElementsByEnginePowerRequestCommand(TaskCollection collection) {
        this.collection = collection;
    }

    @Override
    public void execute(String[] args) {
        Long e_p = Long.parseLong(args[0]);
        collection.removeAllByEnginePower(e_p);
    }
}
