package Client.Commands;

import java.util.Scanner;

import Client.Client;
import General.Utilities.File;


public class ExecuteScriptCommand implements CommandInterface {
    Client client;

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

        if (client.getCurrentScriptFileName().equals(args[1])) {
            System.out.println("Ошибка: Рекурсивное исполнение файла");
            return;
        }

        client.setCurrentScriptFileName(args[1]);

        Scanner scanner = new Scanner(File.read(args[1]));
        client.setScanner(scanner);
        while (scanner.hasNextLine())
            client.executeStringCmd(scanner.nextLine());
        client.setCurrentScriptFileName("");
        client.setScanner(new Scanner(System.in));
    }

    public String getDescription () {
        return "Выводит информацию о коллекции";
    }
}
