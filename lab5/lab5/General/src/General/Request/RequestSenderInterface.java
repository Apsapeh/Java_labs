package General.Request;

/**
 * This is an interface for sending requests.
 * It defines a method for sending a request of a specific type with arguments.
 */
public interface RequestSenderInterface {
    void sendRequest(RequestEnum request, String[] args);
}
