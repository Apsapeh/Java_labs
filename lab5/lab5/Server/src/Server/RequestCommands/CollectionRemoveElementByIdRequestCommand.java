package Server.RequestCommands;

import Server.Server;
import General.Task.Vehicle;

public class CollectionRemoveElementByIdRequestCommand implements RequestCommandInterface {
    Server server;

    public CollectionRemoveElementByIdRequestCommand(Server server) {
        this.server = server;
    }

    @Override
    public void execute(String[] args) {
        Integer id = Integer.parseInt(args[0]);
        Vehicle vehicle = server.getCollectionManager().removeById(id);

        if (vehicle == null) {
            server.getResponseSender().sendResponse(
                    General.Response.ResponseEnum.PRINT_TEXT,
                    new String[] {"Элемент с id " + id + " не найден"}
            );
        }
    }
}
