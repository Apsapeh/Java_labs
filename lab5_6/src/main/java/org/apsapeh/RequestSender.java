package org.apsapeh;

import org.apsapeh.General.Sender.SenderEnum;
import org.apsapeh.General.Sender.SenderInterface;
import org.apsapeh.Server.Server;

/**
 * This class implements the RequestSenderInterface and is responsible for sending requests to the server.
 */
public class RequestSender implements SenderInterface {
    private Server server = null;

    public void send(SenderEnum request, String[] args) {
        //System.out.println("Request: " + request + " Args: " + Arrays.toString(args));

        if (server != null)
            server.handleRequest(request, args);
    }

    public void setServer(Server server) {
        this.server = server;
    }
}