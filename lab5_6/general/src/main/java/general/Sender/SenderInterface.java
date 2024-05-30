package general.Sender;

/**
 * This is an interface for sending requests.
 * It defines a method for sending a request of a specific type with arguments.
 */
public interface SenderInterface {
    void send(SenderEnum request, String[] args);
}
