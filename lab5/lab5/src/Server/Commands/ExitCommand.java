package Server.Commands;


public class ExitCommand implements CommandInterface {
    public ExitCommand() {
    }

    public void execute (String[] args) {
        System.out.println("Завершение работы программы");
        System.exit(0);
    }

    public String getDescription () {
        return "Завершает работу программы";
    }
}
