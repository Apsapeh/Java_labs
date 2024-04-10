package Client.Commands;

import Client.Executor;

import java.util.Scanner;


public class ExecuteScriptCommand implements CommandInterface {
    Executor executor;

    public ExecuteScriptCommand(Executor executor) {
        this.executor = executor;
    }

    public void execute (String[] args) {
        if (args.length < 2) {
            executor.getPrintSender().send(
                    "Неверное количество аргументов. Использование: execute_script file_name"
            );
            return;
        }

        if (executor.getCurrentScriptFileName().equals(args[1])) {
            executor.getPrintSender().send("Ошибка: Рекурсивное исполнение файла");
            return;
        }

        executor.setCurrentScriptFileName(args[1]);
        Scanner scanner = new Scanner(executor.getFileRequester().request(args[1]));
        while (scanner.hasNextLine()) {
            executor.executeStringCmd(scanner.nextLine());
        }
        executor.setCurrentScriptFileName("");
    }

    public String getDescription () {
        return "Выводит информацию о коллекции";
    }
}
