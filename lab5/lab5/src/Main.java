import Server.Executors.Executor;

public class Main {
    public static void main(String[] args) {
        Executor e = new Executor();
        e.executeStringCmd("info");
        e.executeStringCmd("show");
        e.executeStringCmd("help");

        e.Run();

        /*while (true) {
            String cmd = Input("Введите команду: ");
            e.executeStringCmd(cmd);
        }*/
    }
}