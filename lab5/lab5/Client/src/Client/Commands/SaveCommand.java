package Client.Commands;

import Client.Client;
import General.Request.RequestEnum;

public class SaveCommand implements CommandInterface{
    Client client;

    public SaveCommand(Client client) {
        this.client = client;
    }

    @Override
    public void execute(String[] args) {
        client.getRequestSender().sendRequest(
                RequestEnum.SAVE_COLLECTION,
                null
        );
    }

    public String getDescription() {
        return "Сохраняет коллекцию на сервере";
    }
}
