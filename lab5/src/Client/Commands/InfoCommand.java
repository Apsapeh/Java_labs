package Client.Commands;

import Client.Executor;


public class InfoCommand implements CommandInterface {
    Executor executor;

    public InfoCommand(Executor executor) {
        this.executor = executor;
    }

    public void execute (String[] args) {
        executor.getPrintSender().send(executor.getCollection().toPrettyString());
    }

    public String getDescription () {
        return "Выводит информацию о коллекции";
    }
}
