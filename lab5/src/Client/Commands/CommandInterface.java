package Client.Commands;

public interface CommandInterface {
    void execute(String[] args);
    String getDescription();
}
