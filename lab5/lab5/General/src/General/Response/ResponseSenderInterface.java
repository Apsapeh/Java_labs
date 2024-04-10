package General.Response;

/**
 * This is an interface for sending responses.
 * It defines a method for sending a response of a specific type with arguments.
 */
public interface ResponseSenderInterface {
    void sendResponse(ResponseEnum request, String[] args);
}
