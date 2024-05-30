package client.Commands;

import general.Sender.SenderEnum;
import general.Sender.SenderInterface;
import general.Wrappers.Wrapper;

public class SaveCommand implements CommandInterface {
    private final Wrapper<SenderInterface> sender;

    public SaveCommand(Wrapper<SenderInterface> sender) {
        this.sender = sender;
    }

    @Override
    public void execute(String[] args) {
        sender.get().send(
                SenderEnum.SAVE_COLLECTION,
                null
        );
    }

    public String getDescription() {
        return "Сохраняет коллекцию на сервере";
    }
}
