package client.Commands;

import client.Factories.VehicleFactory;
import general.Sender.SenderEnum;
import general.Sender.SenderInterface;
import general.Wrappers.Wrapper;

import java.util.Scanner;
import java.util.Stack;

public class UpdateCommand implements CommandInterface {
    private final Wrapper<SenderInterface> sender;
    private final Wrapper<Scanner> scanner;
    private final Stack<String> scriptStack;

    public UpdateCommand(
            Wrapper<SenderInterface> sender,
            Wrapper<Scanner> scanner,
            Stack<String> scriptStack
    ) {
        this.sender = sender;
        this.scanner = scanner;
        this.scriptStack = scriptStack;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 2) {
            System.out.println("Неверное количество аргументов");
            return;
        }

        int id;
        try {
            id = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.out.println("Неверный формат ID");
            return;
        }

        String [] req_args = new String[7];
        req_args[0] = String.valueOf(id);
        String [] fields = VehicleFactory.getInteractiveVehicleFields(
                scanner.get(),
                scriptStack.isEmpty()
        );
        System.arraycopy(fields, 0, req_args, 1, 6);

        sender.get().send(
                SenderEnum.UPDATE_ELEMENT,
                req_args
        );
    }

    @Override
    public String getDescription() {
        return "Обновить значение элемента коллекции, id которого равен заданному";
    }
}
