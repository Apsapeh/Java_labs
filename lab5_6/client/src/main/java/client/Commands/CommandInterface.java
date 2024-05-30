package client.Commands;

public interface CommandInterface {
    void execute(String[] args);
    String getDescription();
}
