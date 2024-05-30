package client.Commands;

import general.Sender.SenderEnum;
import general.Sender.SenderInterface;
import general.Task.VehicleType;
import general.Wrappers.Wrapper;

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
