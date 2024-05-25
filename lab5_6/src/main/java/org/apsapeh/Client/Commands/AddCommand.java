package org.apsapeh.Client.Commands;

import org.apsapeh.Client.Factories.VehicleFactory;
import org.apsapeh.General.Wrappers.Wrapper;
import org.apsapeh.General.Sender.SenderEnum;
import org.apsapeh.General.Sender.SenderInterface;

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
