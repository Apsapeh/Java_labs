package Client.Commands;

import Client.Client;
import General.Request.RequestEnum;


public class ClearCommand implements CommandInterface {
    Client client;

    public ClearCommand(Client client) {
        this.client = client;
    }

    public void execute (String[] args) {
        /*executor.getCollection().clear();
        executor.getPrintSender().send("Коллекция очищена");*/
        client.getRequestSender().sendRequest(
                RequestEnum.CLEAR_COLLECTION,
                null
        );
    }

    public String getDescription () {
        return "Очищает коллекцию";
    }
}
