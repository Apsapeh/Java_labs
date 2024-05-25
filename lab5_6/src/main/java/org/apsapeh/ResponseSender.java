package org.apsapeh;

import org.apsapeh.Client.Client;
import org.apsapeh.General.Sender.*;

/**
 * This class implements the ResponseSenderInterface and is responsible for sending responses to the client.
 */
public class ResponseSender implements SenderInterface {
    private Client client = null;
    public void send(SenderEnum response, String[] args) {
        //System.out.println("Resp: " + response + " Args: " + Arrays.toString(args));

        if (client != null)
            client.handleResponse(response, args);
    }

    public void setClient(Client client) {
        this.client = client;
    }
}