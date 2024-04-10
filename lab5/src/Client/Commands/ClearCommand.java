package Client.Commands;

import Client.Executor;


public class ClearCommand implements CommandInterface {
    Executor executor;

    public ClearCommand(Executor executor) {
        this.executor = executor;
    }

    public void execute (String[] args) {
        executor.getCollection().clear();
        executor.getPrintSender().send("Коллекция очищена");
    }

    public String getDescription () {
        return "Очищает коллекцию";
    }
}
