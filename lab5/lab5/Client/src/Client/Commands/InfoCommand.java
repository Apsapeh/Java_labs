package Client.Commands;

import Client.Client;
import General.Request.RequestEnum;


public class InfoCommand implements CommandInterface {
    Client executor;

    public InfoCommand(Client executor) {
        this.executor = executor;
    }

    public void execute (String[] args) {
        //System.out.println(executor.getCollection().toPrettyString());
        executor.getRequestSender().sendRequest(
                RequestEnum.GET_COLLECTION_INFO, null
        );
    }

    public String getDescription () {
        return "Выводит информацию о коллекции";
    }
}
