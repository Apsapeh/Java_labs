package Client.Commands;


import Client.Executor;

public class ExitCommand implements CommandInterface {
    Executor executor;

    public ExitCommand (Executor executor) {
        this.executor = executor;
    }

    public void execute (String[] args) {
        executor.getPrintSender().send("Завершение работы программы");
        System.exit(0); //FIXME: Send exit to client
    }

    public String getDescription () {
        return "Завершает работу программы";
    }
}
