package Client.Commands;

import Client.Client;
import General.Request.RequestEnum;
import General.Task.VehicleType;

public class FilterByTypeCommand implements CommandInterface{
    Client client;

    public FilterByTypeCommand(Client client) {
        this.client = client;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 2) {
            System.out.println("Неверное количество аргументов");
            return;
        }


        try {
            VehicleType.valueOf(args[1]);
        } catch (IllegalArgumentException e) {
            System.out.println("Такого типа транспорта нет");
            return;
        }

        client.getRequestSender().sendRequest(
                RequestEnum.GET_COLLECTION_ELEMENTS_BY_TYPE,
                new String[] {args[1]}
        );
    }

    @Override
    public String getDescription() {
        return "Вывести элементы, значение поля type которых равно заданному";
    }
}
