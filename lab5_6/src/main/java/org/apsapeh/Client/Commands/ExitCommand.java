package org.apsapeh.Client.Commands;


import org.apsapeh.Client.Client;

public class ExitCommand implements CommandInterface {
    public ExitCommand () {

    }

    public void execute (String[] args) {
        /*executor.getPrintSender().send("Завершение работы программы");*/
        System.exit(0);
    }

    public String getDescription () {
        return "Завершает работу программы";
    }
}
