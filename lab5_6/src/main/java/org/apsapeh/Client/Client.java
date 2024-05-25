package org.apsapeh.Client;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.*;

import org.apsapeh.Client.Commands.*;
import org.apsapeh.Client.ResponseCommands.PrintTextResponseCommand;
import org.apsapeh.Client.ResponseCommands.ResponseCommandInterface;
import org.apsapeh.General.Wrappers.Wrapper;
import org.apsapeh.General.Sender.SenderInterface;
import org.apsapeh.General.Sender.SenderEnum;

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
        List<Long> objects = Arrays.asList(
                125L,
                126L
        );
        try (SocketChannel socketChannel = SocketChannel.open()) {
            socketChannel.configureBlocking(false);

            if (!socketChannel.connect(new InetSocketAddress(ip, port))) {
                while (!socketChannel.finishConnect()) {
                    System.out.println("Attempting to connect...");
                }
            }

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(objects);
            objectOutputStream.flush();

            ByteBuffer buffer = ByteBuffer.wrap(byteArrayOutputStream.toByteArray());
            socketChannel.write(buffer);

            buffer.clear();
            while (socketChannel.read(buffer) <= 0) {
                // ожидание данных от сервера
            }
            System.out.println("Answer");

            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(buffer.array());
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);



        } catch (IOException e/*| ClassNotFoundException e*/) {
            e.printStackTrace();
        }
        /*while (true) {
            System.out.print("Введите команду: ");
            executeStringCmd(scanner.get().nextLine());
        }*/
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