package Client.Commands;

import Client.Client;
import General.Request.RequestEnum;

public class RemoveAllByEnginePowerCommand implements CommandInterface{
    Client client;

    public RemoveAllByEnginePowerCommand(Client client) {
        this.client = client;
    }

    public void execute(String[] args) {
        if (args.length != 2) {
            System.out.println("Неверное количество аргументов");
            return;
        }

        Long id;
        try {
            id = Long.parseLong(args[1]);
        } catch (NumberFormatException e) {
            System.out.println("Неверный формат EnginePower");
            return;
        }

        client.getRequestSender().sendRequest(
                RequestEnum.REMOVE_ALL_BY_ENGINE_POWER,
                new String[] {id.toString()}
        );
    }

    public String getDescription() {
        return "удалить из коллекции все элементы, значение поля enginePower которого эквивалентно заданному";
    }
}
