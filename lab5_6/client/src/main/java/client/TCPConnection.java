package client;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class TCPConnection {
    private final String host;
    private final int port;
    private final SocketChannel socketChannel;


    public TCPConnection(String host, int port) throws IOException{
        this.host = host;
        this.port = port;
        InetSocketAddress inetAddr = new InetSocketAddress(this.host, this.port);

        try {
            this.socketChannel = SocketChannel.open(inetAddr);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public <T extends Serializable> void send(T obj) throws IOException {
        // Send in channel serialization of obj
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(obj);
        objectOutputStream.flush();

        ByteBuffer buffer = ByteBuffer.wrap(byteArrayOutputStream.toByteArray());
        socketChannel.write(buffer);
        buffer.clear();

    }

    public <T extends Serializable> T receive() throws IOException, ClassNotFoundException {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        this.socketChannel.read(buffer);

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(buffer.array());
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);

        return (T) objectInputStream.readObject();
    }

    public void close() {
        try {
            socketChannel.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void connectWithRetry(SocketChannel socketChannel, String ip, int port, int maxRetries, int delayMillis) throws IOException {
        int attempts = 0;
        while (attempts < maxRetries) {
            try {
                if (socketChannel.connect(new InetSocketAddress(ip, port))) {
                    break;
                }
                while (!socketChannel.finishConnect()) {
                    // ожидание завершения подключения
                }
                return;
            } catch (IOException e) {
                ++attempts;
                System.out.println("Connection attempt " + attempts + " failed. Retrying...");
                try {
                    Thread.sleep(delayMillis);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    throw new IOException("Interrupted during retry delay", ie);
                }
            }
        }
        throw new IOException("Unable to connect to the server after " + maxRetries + " attempts");
    }
}
