package Server.RequestCommands;

import Server.Server;

public class ClearCollectionRequestCommand implements RequestCommandInterface{
    Server server;

    public ClearCollectionRequestCommand(Server server) {
        this.server = server;
    }

    @Override
    public void execute(String[] args) {
        server.getCollectionManager().clear();
    }
}
