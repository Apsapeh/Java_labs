package org.apsapeh.Client.Commands;

import org.apsapeh.General.Wrappers.Wrapper;
import org.apsapeh.General.Sender.SenderEnum;
import org.apsapeh.General.Sender.SenderInterface;

public class RemoveAllByEnginePowerCommand implements CommandInterface{
    private final Wrapper<SenderInterface> sender;

    public RemoveAllByEnginePowerCommand(Wrapper<SenderInterface> sender) {
        this.sender = sender;
    }

    public void execute(String[] args) {
        if (args.length != 2) {
            System.out.println("Неверное количество аргументов");
            return;
        }

        Long id;
        try {
            id = Long.parseLong(args[1]);
        } catch (NumberFormatException e) {
            System.out.println("Неверный формат EnginePower");
            return;
        }

        sender.get().send(
                SenderEnum.REMOVE_ALL_BY_ENGINE_POWER,
                new String[] {id.toString()}
        );
    }

    public String getDescription() {
        return "удалить из коллекции все элементы, значение поля enginePower которого эквивалентно заданному";
    }
}
