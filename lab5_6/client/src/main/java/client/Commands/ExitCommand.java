package client.Commands;


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
