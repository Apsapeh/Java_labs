package Client.Commands;

import Client.Client;
import Client.Factories.VehicleFactory;
import General.Request.RequestEnum;

public class UpdateCommand implements CommandInterface{
    Client client;

    public UpdateCommand(Client client) {
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

        String [] req_args = new String[7];
        req_args[0] = id.toString();
        String [] fields = VehicleFactory.getInteractiveVehicleFields(
                client.getScanner(),
                client.getCurrentScriptFileName().isEmpty()
        );
        System.arraycopy(fields, 0, req_args, 1, 6);

        client.getRequestSender().sendRequest(
                RequestEnum.UPDATE_ELEMENT,
                req_args
        );
    }

    @Override
    public String getDescription() {
        return "Обновить значение элемента коллекции, id которого равен заданному";
    }
}
