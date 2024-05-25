package org.apsapeh.Client.Commands;

import org.apsapeh.General.Wrappers.Wrapper;
import org.apsapeh.General.Sender.SenderEnum;
import org.apsapeh.General.Sender.SenderInterface;


public class RemoveByIdCommand implements CommandInterface {
    private final Wrapper<SenderInterface> sender;

    public RemoveByIdCommand(Wrapper<SenderInterface> sender) {
        this.sender = sender;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 2) {
            System.out.println("Неверное количество аргументов");
            return;
        }

        Integer id;
        try {
            id = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.out.println("Неверный формат ID");
            return;
        }

        sender.get().send(
                SenderEnum.REMOVE_ELEMENT_BY_ID,
                new String[] {id.toString()}
        );
    }

    public String getDescription () {
        return "Удаляет элемент из коллекции по его id";
    }
}
