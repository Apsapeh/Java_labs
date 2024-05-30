package client.Commands;

import general.Sender.SenderEnum;
import general.Sender.SenderInterface;
import general.Wrappers.Wrapper;


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
