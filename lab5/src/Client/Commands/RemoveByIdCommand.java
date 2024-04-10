package Client.Commands;

import Server.Collections.CollectionManager;
import Server.Senders.StringSenderInterface;
import Server.TaskClasses.Vehicle;


public class RemoveByIdCommand implements CommandInterface {
    CollectionManager<Vehicle> collection;
    StringSenderInterface printSender;

    public RemoveByIdCommand(
            CollectionManager<Vehicle> vehicles,
            StringSenderInterface printSender
    ) {
        this.collection = vehicles;
        this.printSender = printSender;
    }

    public void execute (String[] args) {
        /*if (args.length < 2) {
            System.out.println("Неверное количество аргументов. Использование: remove_by_id id");
            return;
        }
        try {
            int id = Integer.parseInt(args[1]);
            if (collection.removeById(id))
                System.out.println("Элемент с id " + id + " удален");
            else
                System.out.println("Элемент с id " + id + " не найден");
        } catch (NumberFormatException e) {
            System.out.println("Неверный формат id");
        }*/
    }

    public String getDescription () {
        return "Удаляет элемент из коллекции по его id";
    }
}
