package Client.Commands;

import Client.Client;
import General.Request.RequestEnum;


public class RemoveByIdCommand implements CommandInterface {
    Client client;

    public RemoveByIdCommand(Client client) {
        this.client = client;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 2) {
            System.out.println("Неверное количество аргументов");
            return;
        }

        Integer id;
        try {
            id = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.out.println("Неверный формат ID");
            return;
        }

        client.getRequestSender().sendRequest(
                RequestEnum.REMOVE_ELEMENT_BY_ID,
                new String[] {id.toString()}
        );
    }

    public String getDescription () {
        return "Удаляет элемент из коллекции по его id";
    }
}
