package server;

import java.io.IOException;

public class Main {
    public static void main(String [] argv) {
            Server server = new Server();
        try {
            server.run("localhost", 1234);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
