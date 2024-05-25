package org.apsapeh.Client.Commands;

import org.apsapeh.General.Wrappers.Wrapper;
import org.apsapeh.General.Sender.SenderEnum;
import org.apsapeh.General.Sender.SenderInterface;

public class PrintAscendingCommand implements CommandInterface{
    private final Wrapper<SenderInterface> sender;

    public PrintAscendingCommand(Wrapper<SenderInterface> sender) {
        this.sender = sender;
    }

    public void execute(String[] args) {
        sender.get().send(
                SenderEnum.GET_ASCENDING_COLLECTION_ELEMENTS,
                null
        );
    }

    public String getDescription() {
        return "вывести элементы коллекции в порядке возрастания";
    }
}
