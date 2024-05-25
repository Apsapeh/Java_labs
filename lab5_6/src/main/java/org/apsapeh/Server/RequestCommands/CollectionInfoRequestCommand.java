package org.apsapeh.Server.RequestCommands;

import org.apsapeh.General.Sender.SenderInterface;
import org.apsapeh.General.Wrappers.Wrapper;
import org.apsapeh.Server.Collections.TaskCollection;
import org.apsapeh.Server.Server;
import org.apsapeh.General.Sender.SenderEnum;

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
