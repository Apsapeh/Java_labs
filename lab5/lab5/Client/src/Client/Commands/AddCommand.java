package Client.Commands;

import Client.Client;
import Client.Factories.VehicleFactory;
import General.Request.RequestEnum;
import General.Task.Coordinates;
import General.Task.FuelType;
import General.Task.VehicleType;

public class AddCommand implements CommandInterface {
    Client client;

    public AddCommand(Client client) {
        this.client = client;
    }

    @Override
    public void execute(String[] args) {
        client.getRequestSender().sendRequest(
                RequestEnum.ADD_ELEMENT,
                VehicleFactory.getInteractiveVehicleFields(
                        client.getScanner(),
                        client.getCurrentScriptFileName().isEmpty()
                )
        );
    }

    @Override
    public String getDescription() {
        return "добавить новый элемент в коллекцию";
    }
}
