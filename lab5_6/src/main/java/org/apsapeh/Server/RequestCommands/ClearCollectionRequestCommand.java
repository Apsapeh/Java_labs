package org.apsapeh.Server.RequestCommands;

import org.apsapeh.Server.Collections.TaskCollection;
import org.apsapeh.Server.Server;

public class ClearCollectionRequestCommand implements RequestCommandInterface{
    private final TaskCollection collection;

    public ClearCollectionRequestCommand(TaskCollection collection) {
        this.collection = collection;
    }

    @Override
    public void execute(String[] args) {
        collection.clear();
    }
}
