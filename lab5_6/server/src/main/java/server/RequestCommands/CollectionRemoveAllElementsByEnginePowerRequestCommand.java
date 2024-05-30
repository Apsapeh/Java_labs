package server.RequestCommands;

import server.Collections.TaskCollection;

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
