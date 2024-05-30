package client.Commands;

import java.util.HashMap;

public class HelpCommand implements CommandInterface {
    private final HashMap<String, CommandInterface> commands_map;

    public HelpCommand(HashMap<String, CommandInterface> commands_map) {
        this.commands_map = commands_map;
    }

    public void execute (String[] args) {
        System.out.println("Команды:");
        for (String key : commands_map.keySet())
            System.out.println(
                    "\t" + key + " - " +
                    commands_map.get(key).getDescription()
            );
    }

    public String getDescription () {
        return "Выводит список всех команд и их описание";
    }
}
