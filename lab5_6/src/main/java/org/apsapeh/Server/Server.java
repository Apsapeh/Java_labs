package org.apsapeh.Server;

import org.apsapeh.General.Utilities.File;
import org.apsapeh.General.Wrappers.Wrapper;
import org.apsapeh.Server.Collections.TaskCollection;
import org.apsapeh.Server.RequestCommands.RequestCommandInterface;
import org.apsapeh.Server.RequestCommands.*;

import org.apsapeh.General.Sender.SenderEnum;
import org.apsapeh.General.Sender.SenderInterface;

import org.apsapeh.General.Task.Vehicle;
import org.apsapeh.General.Task.Coordinates;
import org.apsapeh.General.Task.VehicleType;
import org.apsapeh.General.Task.FuelType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONArray;

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

    public void run(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started...");

            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                     ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream())) {

                    /*List<MyObject> objects = (List<MyObject>) in.readObject();
                    List<MyObject> sortedObjects = objects.stream()
                            .sorted(Comparator.comparing(MyObject::getName))
                            .collect(Collectors.toList());

                    out.writeObject(sortedObjects);*/
                    System.out.println("New Request");
                } catch (/*ClassNotFoundException e*/Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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