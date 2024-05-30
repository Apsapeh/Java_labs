package client.Commands;

import client.Client;
import general.Utilities.File;

import java.util.Scanner;


public class ExecuteScriptCommand implements CommandInterface {
    private final Client client;

    public ExecuteScriptCommand(Client client) {
        this.client = client;
    }

    public void execute (String[] args) {
        if (args.length != 2) {
            System.out.println(
                    "Неверное количество аргументов. Использование: execute_script file_name"
            );
            return;
        }

        if (client.getScriptsStack().contains(args[1])) {
            System.out.println("Ошибка: Рекурсивное исполнение файла");
            return;
        }

        client.addCurrentScriptFileName(args[1]);

        Scanner scanner = new Scanner(File.read(args[1]));
        client.setScanner(scanner);

        while (scanner.hasNextLine())
            client.executeStringCmd(scanner.nextLine());

        //client.setCurrentScriptFileName("");
        client.popCurrentScriptFileName();
        client.setScanner(new Scanner(System.in));
    }

    public String getDescription () {
        return "Выводит информацию о коллекции";
    }
}
