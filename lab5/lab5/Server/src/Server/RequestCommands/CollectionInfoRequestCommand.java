package Server.RequestCommands;

import Server.Server;
import General.Response.ResponseEnum;

public class CollectionInfoRequestCommand implements RequestCommandInterface {
    Server server;

    public CollectionInfoRequestCommand(Server server) {
        this.server = server;
    }

    @Override
    public void execute(String[] args) {
        server.getResponseSender().sendResponse(
                ResponseEnum.PRINT_TEXT,
                new String[] {server.getCollectionManager().toPrettyString()}
        );
    }
}
