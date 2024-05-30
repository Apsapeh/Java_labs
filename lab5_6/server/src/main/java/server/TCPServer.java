package server;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;


public class TCPServer {
    private final String host;
    private final int port;
    private final Selector selector;
    private final ServerSocketChannel serverSocketChannel;



    public TCPServer(String host, int port) throws IOException{
        this.host = host;
        this.port = port;

        // Selector: A multiplexor of SelectableChannel objects.
        // A selector may be created by invoking the open method of this class, which will use the system's default selector provider to create a new selector.
        // A selector may also be created by invoking the openSelector method of a custom selector provider. A selector remains open until it is closed via its close method.
        this.selector = Selector.open(); // selector is open here
        // ServerSocketChannel: A selectable channel for stream-oriented listening sockets.
        // A server-socket channel is created by invoking the open method of this class.
        // It is not possible to create a channel for an arbitrary, pre-existing ServerSocket.
        this.serverSocketChannel = ServerSocketChannel.open();

        // InetSocketAddress: This class implements an IP Socket Address (IP address + port number) It can also be a pair (hostname + port number),
        // in which case an attempt will be made to resolve the hostname.
        // If resolution fails then the address is said to be unresolved but can still be used on some circumstances like connecting through a proxy.
        InetSocketAddress inetAddr = new InetSocketAddress(this.host, this.port);
        // Binds the channel's socket to a local address and configures the socket to listen for connections
        this.serverSocketChannel.bind(inetAddr);
        // Adjusts this channel's blocking mode.
        this.serverSocketChannel.configureBlocking(false);
        int ops = this.serverSocketChannel.validOps();

        // SelectionKey: A token representing the registration of a SelectableChannel with a Selector.
        // A selection key is created each time a channel is registered with a selector.
        // A key remains valid until it is cancelled by invoking its cancel method, by closing its channel, or by closing its selector.
        SelectionKey selectKy = this.serverSocketChannel.register(this.selector, ops, null);
    }

    public <T extends Serializable> void send(SocketChannel channel, T obj) throws IOException {
        // Send in channel serialization of obj
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(obj);
        objectOutputStream.flush();

        ByteBuffer buffer = ByteBuffer.wrap(byteArrayOutputStream.toByteArray());
        channel.write(buffer);
        buffer.clear();

    }

    public <T extends Serializable> ArrayList<Pair<SocketChannel, T>> listenStep() throws IOException, ClassNotFoundException {
        // Selects a set of keys whose corresponding channels are ready for I/O operations
        ArrayList<Pair<SocketChannel, T>> result = new ArrayList<>();
        selector.select();
        // token representing the registration of a SelectableChannel with a Selector
        //Set<SelectionKey> selectedKeys = selector.selectedKeys();
        Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
        while (iterator.hasNext()) {
            SelectionKey myKey = iterator.next();
            // Tests whether this key's channel is ready to accept a new socket connection
            if (myKey.isAcceptable()) {
                SocketChannel client = this.serverSocketChannel.accept();

                // Adjusts this channel's blocking mode to false
                client.configureBlocking(false);
                // Operation-set bit for read operations
                client.register(selector, SelectionKey.OP_READ);
                //log("Connection Accepted: " + crunchifyClient.getLocalAddress() + "\n");
                // Tests whether this key's channel is ready for reading
            } else if (myKey.isReadable()) {

                SocketChannel client = (SocketChannel) myKey.channel();



                // ByteBuffer: A byte buffer.
                // This class defines six categories of operations upon byte buffers:
                // Absolute and relative get and put methods that read and write single bytes;
                // Absolute and relative bulk get methods that transfer contiguous sequences of bytes from this buffer into an array;
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                if (client.read(buffer) == -1) {
                    client.close();
                    //log("\nClient got disconnected..");
                    continue;
                }

                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(buffer.array());
                ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);

                result.add(new Pair<>(client, (T) objectInputStream.readObject()));
            }
            iterator.remove();
        }

        return result;
    }

    public void close() {
        /*try {
            socketChannel.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
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
