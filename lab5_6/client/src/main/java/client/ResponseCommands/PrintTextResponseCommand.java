package client.ResponseCommands;

public class PrintTextResponseCommand implements ResponseCommandInterface{
    public void execute(String[] args) {
        System.out.println(args[0]);
    }
}
