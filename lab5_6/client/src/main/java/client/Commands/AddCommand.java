package client.Commands;

import client.Factories.VehicleFactory;
import general.Sender.SenderEnum;
import general.Sender.SenderInterface;
import general.Wrappers.Wrapper;

import java.util.Scanner;
import java.util.Stack;

public class AddCommand implements CommandInterface {
    private final Wrapper<SenderInterface> sender;
    private final Wrapper<Scanner> scanner;
    private final Stack<String> scriptStack;

    public AddCommand(
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
        sender.get().send(
                SenderEnum.ADD_ELEMENT,
                VehicleFactory.getInteractiveVehicleFields(
                        scanner.get(),
                        scriptStack.isEmpty()
                )
        );
    }

    @Override
    public String getDescription() {
        return "добавить новый элемент в коллекцию";
    }
}
