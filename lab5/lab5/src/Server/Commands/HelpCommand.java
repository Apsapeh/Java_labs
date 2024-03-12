package Server.Commands;

import java.util.HashMap;

public class HelpCommand implements CommandInterface {
    HashMap<String, CommandInterface> commandsMap;

    public HelpCommand (HashMap<String, CommandInterface> commandsMap) {
        this.commandsMap = commandsMap;
    }

    public void execute (String[] args) {
        System.out.println("Команды:");
        for (String key : commandsMap.keySet())
            System.out.println("\t" + key + " - " + commandsMap.get(key).getDescription());
    }

    public String getDescription () {
        return "Выводит список всех команд и их описание";
    }
}
