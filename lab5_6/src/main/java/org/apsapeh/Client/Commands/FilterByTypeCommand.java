package org.apsapeh.Client.Commands;

import org.apsapeh.General.Wrappers.Wrapper;
import org.apsapeh.General.Sender.SenderEnum;
import org.apsapeh.General.Sender.SenderInterface;
import org.apsapeh.General.Task.VehicleType;

public class FilterByTypeCommand implements CommandInterface{
    private final Wrapper<SenderInterface> sender;

    public FilterByTypeCommand(Wrapper<SenderInterface> sender) {
        this.sender = sender;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 2) {
            System.out.println("Неверное количество аргументов");
            return;
        }


        try {
            VehicleType.valueOf(args[1]);
        } catch (IllegalArgumentException e) {
            System.out.println("Такого типа транспорта нет");
            return;
        }

        sender.get().send(
                SenderEnum.GET_COLLECTION_ELEMENTS_BY_TYPE,
                new String[] {args[1]}
        );
    }

    @Override
    public String getDescription() {
        return "Вывести элементы, значение поля type которых равно заданному";
    }
}
