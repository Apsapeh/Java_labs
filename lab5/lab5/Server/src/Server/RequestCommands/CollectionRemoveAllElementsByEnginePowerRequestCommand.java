package Server.RequestCommands;

import Server.Server;

public class CollectionRemoveAllElementsByEnginePowerRequestCommand implements RequestCommandInterface {
    Server server;

    public CollectionRemoveAllElementsByEnginePowerRequestCommand(Server server) {
        this.server = server;
    }

    @Override
    public void execute(String[] args) {
        Long e_p = Long.parseLong(args[0]);
        server.getCollectionManager().removeAllByEnginePower(e_p);
    }
}
