package org.apsapeh.Client.Commands;

import org.apsapeh.General.Wrappers.Wrapper;
import org.apsapeh.General.Sender.SenderEnum;
import org.apsapeh.General.Sender.SenderInterface;

public class SaveCommand implements CommandInterface{
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
