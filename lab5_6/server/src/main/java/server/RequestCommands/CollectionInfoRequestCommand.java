package server.RequestCommands;

import general.Sender.SenderEnum;
import general.Sender.SenderInterface;
import general.Wrappers.Wrapper;
import server.Collections.TaskCollection;

public class CollectionInfoRequestCommand implements RequestCommandInterface {
    private final TaskCollection collection;
    private final Wrapper<SenderInterface> sender;

    public CollectionInfoRequestCommand(
            TaskCollection collection,
            Wrapper<SenderInterface> sender
    ) {
        this.collection = collection;
        this.sender = sender;
    }

    @Override
    public void execute(String[] args) {
        sender.get().send(
                SenderEnum.PRINT_TEXT,
                new String[] {collection.toPrettyString()}
        );
    }
}
