package client;

import client.ResponseCommands.PrintTextResponseCommand;
import client.ResponseCommands.ResponseCommandInterface;
import client.Commands.*;
import general.Sender.SenderEnum;
import general.Sender.SenderInterface;
import general.Wrappers.Wrapper;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.*;

/**
 * This class is responsible for interacting with the user.
 */
public class Client {
    // Interface for sending requests
    private final Wrapper<SenderInterface> requestSender = new Wrapper<>(null);
    // Scanner for reading user input
    private final Wrapper<Scanner> scanner = new Wrapper<>(new Scanner(System.in));
    // Map for storing commands
    private final HashMap<String, CommandInterface> commandsMap = new HashMap<>();
    // Map for storing response commands
    private final HashMap<SenderEnum, ResponseCommandInterface> responseCommandsMap = new HashMap<>();

    private final Wrapper<TCPConnection> connection = new Wrapper<>(null);

    // Current script file name
    private final Stack<String> scriptsStack = new Stack<>();

    /**
     * Constructor for the Client class.
     * Initializes the commands and response commands maps.
     */
    public Client() {
        // Initialize commands
        commandsMap.put("help", new HelpCommand(this.commandsMap));
        commandsMap.put("exit", new ExitCommand());
        commandsMap.put("info", new InfoCommand(this.requestSender));
        commandsMap.put("show", new ShowCommand(this.requestSender));
        commandsMap.put("add", new AddCommand(this.requestSender, this.scanner, this.scriptsStack));
        commandsMap.put("update", new UpdateCommand(this.requestSender, this.scanner, this.scriptsStack));
        commandsMap.put("remove_by_id", new RemoveByIdCommand(this.requestSender));
        commandsMap.put("clear", new ClearCommand(this.requestSender));
        commandsMap.put("save", new SaveCommand(this.requestSender));
        commandsMap.put("execute_script", new ExecuteScriptCommand(this)); //HACK
        commandsMap.put("remove_all_by_engine_power", new RemoveAllByEnginePowerCommand(this.requestSender));
        commandsMap.put("filter_by_type", new FilterByTypeCommand(this.requestSender));
        commandsMap.put("print_ascending", new PrintAscendingCommand(this.requestSender));

        // Initialize response commands
        responseCommandsMap.put(SenderEnum.PRINT_TEXT, new PrintTextResponseCommand());
    }

    /**
     * Method to run the client.
     * Continuously prompts the user for a command and executes it.
     */
    public void run(String ip, int port) {
        try {
            connection.set(new TCPConnection(ip, port));
        } catch (IOException e) {
            System.out.println("Не удалось установить соединение с сервером");
            return;
        }

        while (true) {
            System.out.print("Введите команду: ");
            String command_str = scanner.get().nextLine();
            try {
                this.connection.get().send(command_str);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            try {
                //SenderEnum response = this.connection.get().receive();
                //String[] args = this.connection.get().receive();
                //handleResponse(response, args);
                String response = this.connection.get().receive();
                System.out.println(response);
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void log(String str) {

        System.out.println(str);
    }

    private static void connectWithRetry(SocketChannel socketChannel, String ip, int port, int maxRetries, int delayMillis) throws IOException {
        int attempts = 0;
        while (attempts < maxRetries) {
            try {
                if (socketChannel.connect(new InetSocketAddress(ip, port))) {
                    break;
                }
                while (!socketChannel.finishConnect()) {
                    // ожидание завершения подключения
                }
                return;
            } catch (IOException e) {
                ++attempts;
                System.out.println("Connection attempt " + attempts + " failed. Retrying...");
                try {
                    Thread.sleep(delayMillis);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    throw new IOException("Interrupted during retry delay", ie);
                }
            }
        }
        throw new IOException("Unable to connect to the server after " + maxRetries + " attempts");
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
    public void handleResponse(SenderEnum response, String[] args) {
        //System.out.println("Handled Response: " + response);
        if (responseCommandsMap.containsKey(response))
            responseCommandsMap.get(response).execute(args);
    }


    //================> Setters <================
    public void setScanner(Scanner scanner) {
        this.scanner.set(scanner);
    }

    public void setRequestSender(SenderInterface requestSender) {
        this.requestSender.set(requestSender);
    }

    public void addCurrentScriptFileName(String currentScriptFileName) {
        this.scriptsStack.add(currentScriptFileName);
    }

    public String popCurrentScriptFileName() {
        return this.scriptsStack.pop();
    }


    //================> Getters <================
    public Stack<String> getScriptsStack() {
        return this.scriptsStack;
    }

}