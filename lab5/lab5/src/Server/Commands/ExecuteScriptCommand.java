package Server.Commands;

import Server.Executors.Executor;

import java.io.File;
import java.util.Scanner;


public class ExecuteScriptCommand implements CommandInterface {
    Executor executor;

    public ExecuteScriptCommand(Executor executor) {
        this.executor = executor;
    }

    public void execute (String[] args) {
        if (args.length < 2) {
            System.out.println("Неверное количество аргументов. Использование: execute_script file_name");
            return;
        }

        // Чтение из файла
        try (Scanner scanner = new Scanner(new File(args[1]))) {
            executor.
            //Executor executor = new Executor(this.collection);
            while (scanner.hasNextLine()) {
                executor.executeStringCmd(scanner.nextLine());
            }
        } catch (Exception e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }


        //Executor executor = new Executor(this.collection);
    }

    public String getDescription () {
        return "Выводит информацию о коллекции";
    }
}
