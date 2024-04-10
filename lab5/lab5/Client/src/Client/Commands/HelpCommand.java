package Client.Commands;

import Client.Client;

public class HelpCommand implements CommandInterface {
    Client executor;

    public HelpCommand(Client executor) {
        this.executor = executor;
    }

    public void execute (String[] args) {
        System.out.println("Команды:");
        for (String key : executor.getCommandsMap().keySet())
            System.out.println(
                    "\t" + key + " - " +
                    executor.getCommandsMap().get(key).getDescription()
            );
    }

    public String getDescription () {
        return "Выводит список всех команд и их описание";
    }
}
