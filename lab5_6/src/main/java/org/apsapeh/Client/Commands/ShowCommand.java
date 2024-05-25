package org.apsapeh.Client.Commands;

import org.apsapeh.General.Wrappers.Wrapper;
import org.apsapeh.General.Sender.SenderEnum;
import org.apsapeh.General.Sender.SenderInterface;


public class ShowCommand implements CommandInterface {
    private final Wrapper<SenderInterface> sender;

    public ShowCommand(Wrapper<SenderInterface> sender) {
        this.sender = sender;
    }

    public void execute (String[] args) {
        String [] arguments = new String[1];
        arguments[0] = "show";
        sender.get().send(
                SenderEnum.GET_COLLECTION_ELEMENTS, arguments
        );
        /*executor.getPrintSender().send("Элементы коллекции:");
        for (Vehicle vehicle : executor.getCollection()) {
            executor.getPrintSender().send(vehicle.toString());
        }*/
    }

    public String getDescription () {
        return "Выдает все элементы коллекции в строковом представлении";
    }
}
