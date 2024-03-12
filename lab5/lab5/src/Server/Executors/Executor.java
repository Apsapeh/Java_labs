package Server.Executors;

import Server.Collections.CollectionManager;
import Server.Senders.StringSenderInterface;
import Server.TaskClasses.Vehicle;

import static Utilities.InputUtil.Input;
import java.util.HashMap;
import java.util.Scanner;

import Server.Commands.*;
import Server.Requesters.StringRequesterInterface;

public class Executor {
    CollectionManager<Vehicle> collection = new CollectionManager <Vehicle>();
    HashMap<String, CommandInterface> commandsMap = new HashMap<>();
    //Scanner scanner = new Scanner(System.in);

    StringRequesterInterface inputRequester;
    StringRequesterInterface fileRequester;
    StringSenderInterface    printSender;

    String[] cmd_with_args;

    public Executor (
            StringRequesterInterface inputRequester,
            StringRequesterInterface fileRequester,
            StringSenderInterface    printSender
    ) {
        this.inputRequester = inputRequester;
        this.fileRequester = fileRequester;
        this.printSender = printSender;

        commandsMap.put("help", new HelpCommand(commandsMap));
        commandsMap.put("info", new InfoCommand(collection));
        commandsMap.put("show", new ShowCommand(collection));

        //commandsMap.put("add", new AddCommand());
        //commandsMap.put("update", new UpdateCommand());
        //commandsMap.put("remove_by_id", new RemoveByIdCommand(collection));
        commandsMap.put("clear", new ClearCommand(collection));
        //commandsMap.put("save", new SaveCommand());
        commandsMap.put("execute_script", new ExecuteScriptCommand(this));
        commandsMap.put("exit", new ExitCommand());
        //commandsMap.put("add_if_max", new AddIfMaxCommand());
        //commandsMap.put("remove_greater", new RemoveGreaterCommand());
        //commandsMap.put("remove_lower", new RemoveLowerCommand());
        //commandsMap.put("remove_all_by_engine_power", new RemoveAllByEnginePowerCommand());
        //commandsMap.put("filter_by_type", new FilterByTypeCommand());
        //commandsMap.put("print_asceding", new PrintAscendingCommand());

    }

    /*public Executor (CollectionManager<Vehicle> collection) {
        this.collection = collection;
    }*/

    public void executeStringCmd(String command_str) {
        printSender.send("Выполнение команды: " + command_str);
        cmd_with_args = command_str.strip().split(" ");

        if (cmd_with_args.length == 0)
            return;

        if (commandsMap.containsKey(cmd_with_args[0]))
            commandsMap.get(cmd_with_args[0]).execute(cmd_with_args);
        else
            printSender.println("Ошибка: Неизвестная команда. Введите help для справки.");



        /*switch (cmd_with_args[0]) {
            case "info":
                info();
                break;
            case "show":
                break;
            case "add":
                add();
                break;
            case "update":
                break;
            case "remove_by_id":
                break;
            case "clear":
                break;
            case "save":
                break;
            case "execute_script":
                break;
            case "exit":
                exit();
                break;
            case "remove_at":
                break;
            case "remove_last":
                break;
            case "sort":
                break;
            case "remove_any_by_type":
                break;
            case "average_of_engine_power":
                break;
            case "count_by_engine_power":
                break;
            case "help":
                help();
                break;

            default:
                System.out.println("Sosi");
                break;
        }*/
    }

    public void Run() {
        while (scanner.hasNextLine()) {
            executeStringCmd(scanner.nextLine());
        }
    }

    boolean checkAndFixCommandArgs(int argc) {
        if (this.cmd_with_args.length < argc + 1) {
            System.out.println("Error: Not enough arguments");
            return false;
        }

        if (this.cmd_with_args.length > argc + 1)
            System.out.println("Warning: Too many arguments");

        // Change "null" to empty string
        for (int i = 1; i < this.cmd_with_args.length; ++i)
            if (this.cmd_with_args[i].equals("null"))
                this.cmd_with_args[i] = "";

        return true;
    }

    /* ==========> Server.Commands <========== */


    void info() {
        System.out.println(collection.toPrettyString());
    }

    void show() {
        /*for (Vehicle v : collection.getCollection()) {
            System.out.println(v);
        }*/
    }

    void add() {
        if (checkAndFixCommandArgs(0)) {
            String name = Input("Введите название: ").replaceAll("^null$", "");
            System.out.println(name);
        }
    }

    void update() {

    }

    void remove_by_id() {

    }

    void clear() {

    }

    void save() {

    }

    void execute_script() {

    }

    void exit() {
        System.exit(0);
    }

    void remove_at() {

    }

    void remove_last() {

    }

    void sort() {

    }

    void remove_any_by_type() {

    }

    void average_of_engine_power() {

    }

    void count_by_engine_power() {

    }

    void help() {
        System.out.println(
                """
                Команды:
                    help: вывести справку по доступным командам
                    info: вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)
                    show: вывести в стандартный поток вывода все элементы коллекции в строковом представлении
                    add {element}: добавить новый элемент в коллекцию
                    update id {element}: обновить значение элемента коллекции, id которого равен заданному
                    remove_by_id id: удалить элемент из коллекции по его id
                    clear: очистить коллекцию
                    save: сохранить коллекцию в файл
                    execute_script file_name: считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.
                    exit: завершить программу (без сохранения в файл)
                    remove_at index: удалить элемент, находящийся в заданной позиции коллекции (index)
                    remove_last: удалить последний элемент из коллекции
                    sort: отсортировать коллекцию в естественном порядке
                    remove_any_by_type type: удалить из коллекции один элемент, значение поля type которого эквивалентно заданному
                    average_of_engine_power: вывести среднее значение поля enginePower для всех элементов коллекции
                    count_by_engine_power enginePower: вывести количество элементов, значение поля enginePower которых равно заданному
                """
        );
    }
    /* ==========> End Server.Commands <========== */
}
