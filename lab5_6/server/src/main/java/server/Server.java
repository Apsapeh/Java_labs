package server;

import general.Sender.SenderEnum;
import general.Sender.SenderInterface;
import general.Task.Coordinates;
import general.Task.FuelType;
import general.Task.Vehicle;
import general.Task.VehicleType;
import general.Utilities.File;
import general.Wrappers.Wrapper;
import server.Collections.TaskCollection;
import server.RequestCommands.RequestCommandInterface;
import server.RequestCommands.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * This class represents the server side of the application.
 * It is responsible for handling requests and managing the collection of vehicles.
 */
public class Server {
    // Interface for sending responses
    private final Wrapper<SenderInterface> responseSender = new Wrapper<>(null);
    // Manager for the collection of vehicles
    private final TaskCollection taskCollection = new TaskCollection();
    // Map for storing request commands
    private final HashMap<SenderEnum, RequestCommandInterface> requestCommandsMap = new HashMap<>();

    // Path to the database file
    private final Wrapper<String> collectionDBPath = new Wrapper<>(null);

    /**
     * Constructor for the Server class.
     * Initializes the request commands map and loads the collection from the database.
     */
    public Server() {
        this.collectionDBPath.set(System.getenv("DB_PATH"));

        if (collectionDBPath == null) {
            System.out.println("DB_PATH is not set. Exiting...");
            System.exit(1);
        }

        // Load the collection from the database
        String JSON_DB_string = File.read(collectionDBPath.get());
        if (!JSON_DB_string.isEmpty()) {
            JSONArray JSON_DB = new JSONArray(JSON_DB_string);
            for (int i = 0; i < JSON_DB.length(); ++i) {
                JSONObject vehicle = JSON_DB.getJSONObject(i);
                taskCollection.add(
                        new Vehicle(
                                vehicle.getInt("id"),
                                vehicle.getString("name"),
                                new Coordinates(vehicle.getInt("x"), vehicle.getInt("y")),
                                java.time.LocalDateTime.parse(vehicle.getString("creationDate")),
                                vehicle.getLong("enginePower"),
                                VehicleType.valueOf(vehicle.getString("type")),
                                FuelType.valueOf(vehicle.getString("fuelType"))
                        )
                );
                if (Vehicle.staticID < vehicle.getInt("id"))
                    Vehicle.staticID = vehicle.getInt("id");
            }
        }

        // Initialize request commands
        requestCommandsMap.put(SenderEnum.GET_COLLECTION_INFO, new CollectionInfoRequestCommand(this.taskCollection, this.responseSender));
        requestCommandsMap.put(SenderEnum.GET_COLLECTION_ELEMENTS, new CollectionElementsRequestCommand(this.taskCollection, this.responseSender));
        requestCommandsMap.put(SenderEnum.ADD_ELEMENT, new AddElementRequestCommand(this.taskCollection));
        requestCommandsMap.put(SenderEnum.UPDATE_ELEMENT, new UpdateElementRequestCommand(this.taskCollection, this.responseSender));
        requestCommandsMap.put(SenderEnum.CLEAR_COLLECTION, new ClearCollectionRequestCommand(this.taskCollection));
        requestCommandsMap.put(SenderEnum.GET_COLLECTION_ELEMENTS_BY_TYPE, new CollectionElementsByTypeRequestCommand(this.taskCollection, this.responseSender));
        requestCommandsMap.put(SenderEnum.REMOVE_ELEMENT_BY_ID, new CollectionRemoveElementByIdRequestCommand(this.taskCollection, this.responseSender));
        requestCommandsMap.put(SenderEnum.SAVE_COLLECTION, new SaveCollectionRequestCommand(this.taskCollection, this.collectionDBPath)); // FIXME
        requestCommandsMap.put(SenderEnum.GET_ASCENDING_COLLECTION_ELEMENTS, new AscendingCollectionElementsRequestCommand(this.taskCollection, this.responseSender));
        requestCommandsMap.put(SenderEnum.REMOVE_ALL_BY_ENGINE_POWER, new CollectionRemoveAllElementsByEnginePowerRequestCommand(this.taskCollection));
    }

    public void run(String host, int port) throws IOException {
        /*// Selector: A multiplexor of SelectableChannel objects.
        // A selector may be created by invoking the open method of this class, which will use the system's default selector provider to create a new selector.
        // A selector may also be created by invoking the openSelector method of a custom selector provider. A selector remains open until it is closed via its close method.
        Selector selector = Selector.open(); // selector is open here
        // ServerSocketChannel: A selectable channel for stream-oriented listening sockets.
        // A server-socket channel is created by invoking the open method of this class.
        // It is not possible to create a channel for an arbitrary, pre-existing ServerSocket.
        ServerSocketChannel crunchifySocket = ServerSocketChannel.open();

        // InetSocketAddress: This class implements an IP Socket Address (IP address + port number) It can also be a pair (hostname + port number),
        // in which case an attempt will be made to resolve the hostname.
        // If resolution fails then the address is said to be unresolved but can still be used on some circumstances like connecting through a proxy.
        InetSocketAddress crunchifyAddr = new InetSocketAddress("localhost", 1234);
        // Binds the channel's socket to a local address and configures the socket to listen for connections
        crunchifySocket.bind(crunchifyAddr);
        // Adjusts this channel's blocking mode.
        crunchifySocket.configureBlocking(false);
        int ops = crunchifySocket.validOps();

        // SelectionKey: A token representing the registration of a SelectableChannel with a Selector.
        // A selection key is created each time a channel is registered with a selector.
        // A key remains valid until it is cancelled by invoking its cancel method, by closing its channel, or by closing its selector.
        SelectionKey selectKy = crunchifySocket.register(selector, ops, null);
        // Infinite loop..
        // Keep server running
        while (true) {
            log("I'm a server and I'm waiting for new connection and buffer select...");
            // Selects a set of keys whose corresponding channels are ready for I/O operations
            selector.select();
            // token representing the registration of a SelectableChannel with a Selector
            Set<SelectionKey> crunchifyKeys = selector.selectedKeys();
            if (crunchifyKeys.isEmpty()) {
                continue;
            }
            Iterator<SelectionKey> crunchifyIterator = crunchifyKeys.iterator();
            while (crunchifyIterator.hasNext()) {
                SelectionKey myKey = crunchifyIterator.next();
                // Tests whether this key's channel is ready to accept a new socket connection
                if (myKey.isAcceptable()) {
                    SocketChannel crunchifyClient = crunchifySocket.accept();

                    // Adjusts this channel's blocking mode to false
                    crunchifyClient.configureBlocking(false);
                    // Operation-set bit for read operations
                    crunchifyClient.register(selector, SelectionKey.OP_READ);
                    log("Connection Accepted: " + crunchifyClient.getLocalAddress() + "\n");
                    // Tests whether this key's channel is ready for reading
                } else if (myKey.isReadable()) {

                    SocketChannel crunchifyClient = (SocketChannel) myKey.channel();



                    // ByteBuffer: A byte buffer.
                    // This class defines six categories of operations upon byte buffers:
                    // Absolute and relative get and put methods that read and write single bytes;
                    // Absolute and relative bulk get methods that transfer contiguous sequences of bytes from this buffer into an array;
                    ByteBuffer crunchifyBuffer = ByteBuffer.allocate(1024);
                    if (crunchifyClient.read(crunchifyBuffer) == -1) {
                        crunchifyClient.close();
                        log("\nClient got disconnected..");
                        continue;
                    }

                    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(crunchifyBuffer.array());
                    ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);

                    String sortedObjects = null;
                    try {
                        sortedObjects = (String) objectInputStream.readObject();
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println(sortedObjects);

                    //crunchifyClient.read(crunchifyBuffer);
                    //String result = new String(crunchifyBuffer.array()).trim();
                    //log("Message received: " + result);


                    // Answer to client
                    byte[] message = new String("Server received the message").getBytes();
                    ByteBuffer buffer = ByteBuffer.wrap(message);
                    crunchifyClient.write(buffer);
                    buffer.clear();

                }
                crunchifyIterator.remove();
            }
        }*/
        /*TCPServer server = new TCPServer(host, port);

        while (true) {
            ArrayList<Pair<SocketChannel, String>> r;
            try {
                r = server.listenStep();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            for (Pair<SocketChannel, String> pair : r) {
                //SenderEnum request = SenderEnum.valueOf(pair.getValue().split(" ")[0]);
                //String[] args = pair.getValue().substring(pair.getValue().indexOf(" ") + 1).split(" ");
                //handleRequest(request, args);
                System.out.println(pair.getValue());
                server.send(pair.getKey(), "Server received the message");
            }
        }*/
    }
    private static void log(String str) {

        System.out.println(str);
    }

    /**
     * Method to handle a request.
     * @param request The request to handle.
     * @param args The arguments for the request.
     */
    public void handleRequest(SenderEnum request, String[] args) {
        if (!requestCommandsMap.containsKey(request))
            return;

        requestCommandsMap.get(request).execute(args);
    }

    //================> Setters <================
    public void setResponseSender(SenderInterface responseSender) {
        this.responseSender.set(responseSender);
    }


    //================> Getters <================
}