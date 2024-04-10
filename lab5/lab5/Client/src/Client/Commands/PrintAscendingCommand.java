package Client.Commands;

import Client.Client;
import General.Request.RequestEnum;

public class PrintAscendingCommand implements CommandInterface{
    Client client;

    public PrintAscendingCommand(Client client) {
        this.client = client;
    }

    public void execute(String[] args) {
        client.getRequestSender().sendRequest(
                RequestEnum.GET_ASCENDING_COLLECTION_ELEMENTS,
                null
        );
    }

    public String getDescription() {
        return "вывести элементы коллекции в порядке возрастания";
    }
}
