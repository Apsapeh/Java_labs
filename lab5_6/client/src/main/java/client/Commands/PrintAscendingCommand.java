package client.Commands;

import general.Sender.SenderEnum;
import general.Sender.SenderInterface;
import general.Wrappers.Wrapper;

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
