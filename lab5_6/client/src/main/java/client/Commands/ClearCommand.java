package client.Commands;

import general.Sender.SenderEnum;
import general.Sender.SenderInterface;
import general.Wrappers.Wrapper;


public class ClearCommand implements CommandInterface {
    private final Wrapper<SenderInterface> sender;

    public ClearCommand(Wrapper<SenderInterface> sender) {
        this.sender = sender;
    }

    public void execute (String[] args) {
        /*executor.getCollection().clear();
        executor.getPrintSender().send("Коллекция очищена");*/
        sender.get().send(
                SenderEnum.CLEAR_COLLECTION,
                null
        );
    }

    public String getDescription () {
        return "Очищает коллекцию";
    }
}
