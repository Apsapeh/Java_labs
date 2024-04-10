package Client.Commands;


import Client.Client;

public class ExitCommand implements CommandInterface {
    Client executor;

    public ExitCommand (Client executor) {
        this.executor = executor;
    }

    public void execute (String[] args) {
        /*executor.getPrintSender().send("Завершение работы программы");*/
        System.exit(0);
    }

    public String getDescription () {
        return "Завершает работу программы";
    }
}
