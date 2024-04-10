package Client.Commands;

import Client.Executor;

public class HelpCommand implements CommandInterface {
    Executor executor;

    public HelpCommand(Executor executor) {
        this.executor = executor;
    }

    public void execute (String[] args) {
        executor.getPrintSender().send("Команды:");
        for (String key : executor.getCommandsMap().keySet())
            executor.getPrintSender().send(
                    "\t" + key + " - " + executor.getCommandsMap().get(key).getDescription()
            );
    }

    public String getDescription () {
        return "Выводит список всех команд и их описание";
    }
}
