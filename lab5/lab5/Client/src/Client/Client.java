package Client;

import java.util.HashMap;
import java.util.Scanner;

import Client.Commands.*;
import Client.ResponseCommands.PrintTextResponseCommand;
import Client.ResponseCommands.ResponseCommandInterface;
import General.Request.RequestEnum;
import General.Request.RequestSenderInterface;
import General.Response.ResponseEnum;

/**
 * This class is responsible for interacting with the user.
 */
public class Client {
    // Interface for sending requests
    RequestSenderInterface requestSender = null;
    // Scanner for reading user input
    Scanner scanner = new Scanner(System.in);
    // Map for storing commands
    HashMap<String, CommandInterface> commandsMap = new HashMap<>();
    // Map for storing response commands
    HashMap<ResponseEnum, ResponseCommandInterface> responseCommandsMap = new HashMap<>();

    // Current script file name
    String currentScriptFileName = "";

    /**
     * Constructor for the Client class.
     * Initializes the commands and response commands maps.
     */
    public Client() {
        // Initialize commands
        commandsMap.put("help", new HelpCommand(this));
        commandsMap.put("info", new InfoCommand(this));
        commandsMap.put("show", new ShowCommand(this));
        commandsMap.put("add", new AddCommand(this));
        commandsMap.put("update", new UpdateCommand(this));
        commandsMap.put("remove_by_id", new RemoveByIdCommand(this));
        commandsMap.put("clear", new ClearCommand(this));
        commandsMap.put("save", new SaveCommand(this));
        commandsMap.put("execute_script", new ExecuteScriptCommand(this));
        commandsMap.put("exit", new ExitCommand(this));
        commandsMap.put("remove_all_by_engine_power", new RemoveAllByEnginePowerCommand(this));
        commandsMap.put("filter_by_type", new FilterByTypeCommand(this));
        commandsMap.put("print_ascending", new PrintAscendingCommand(this));

        // Initialize response commands
        responseCommandsMap.put(ResponseEnum.PRINT_TEXT, new PrintTextResponseCommand());
    }

    /**
     * Method to run the client.
     * Continuously prompts the user for a command and executes it.
     */
    public void run() {
        while (true) {
            System.out.print("Введите команду: ");
            executeStringCmd(scanner.nextLine());
        }
    }

    /**
     * Method to execute a command.
     * @param command_str The command string to execute.
     */
    public void executeStringCmd(String command_str) {
        System.out.println("Выполнение команды: " + command_str);
        String[] cmd_with_args = command_str.strip().split(" ");

        if (cmd_with_args.length == 0)
            return;

        if (commandsMap.containsKey(cmd_with_args[0]))
            commandsMap.get(cmd_with_args[0]).execute(cmd_with_args);
        else
            System.out.println("Команда не найдена");
    }

    /**
     * Method to handle a response.
     * @param response The response to handle.
     * @param args The arguments for the response.
     */
    public void handleResponse(ResponseEnum response, String[] args) {
        //System.out.println("Handled Response: " + response);
        if (responseCommandsMap.containsKey(response))
            responseCommandsMap.get(response).execute(args);
    }

    //================> Setters <================
    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public void setRequestSender(RequestSenderInterface requestSender) {
        this.requestSender = requestSender;
    }

    public void setCurrentScriptFileName(String currentScriptFileName) {
        this.currentScriptFileName = currentScriptFileName;
    }


    //================> Getters <================
    public HashMap<String, CommandInterface> getCommandsMap() {
        return commandsMap;
    }

    public RequestSenderInterface getRequestSender() {
        return requestSender;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public String getCurrentScriptFileName() {
        return currentScriptFileName;
    }
}