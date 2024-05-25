package org.apsapeh.Client.Commands;

import org.apsapeh.General.Wrappers.Wrapper;
import org.apsapeh.General.Sender.SenderEnum;
import org.apsapeh.General.Sender.SenderInterface;


public class InfoCommand implements CommandInterface {
    private final Wrapper<SenderInterface> sender;

    public InfoCommand(Wrapper<SenderInterface> sender) {
        this.sender = sender;
    }

    public void execute (String[] args) {
        //System.out.println(executor.getCollection().toPrettyString());
        sender.get().send(
                SenderEnum.GET_COLLECTION_INFO, null
        );
    }

    public String getDescription () {
        return "Выводит информацию о коллекции";
    }
}
