package server.RequestCommands;

import server.Collections.TaskCollection;

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
