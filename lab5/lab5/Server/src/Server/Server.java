package Server;

import General.Utilities.File;
import Server.Collections.CollectionManager;
import Server.RequestCommands.RequestCommandInterface;
import Server.RequestCommands.*;

import General.Request.RequestEnum;
import General.Response.ResponseSenderInterface;

import General.Task.Vehicle;
import General.Task.Coordinates;
import General.Task.VehicleType;
import General.Task.FuelType;

import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONArray;

/**
 * This class represents the server side of the application.
 * It is responsible for handling requests and managing the collection of vehicles.
 */
public class Server {
    // Interface for sending responses
    ResponseSenderInterface responseSender = null;
    // Manager for the collection of vehicles
    CollectionManager collectionManager = new CollectionManager();
    // Map for storing request commands
    HashMap<RequestEnum, RequestCommandInterface> requestCommandsMap = new HashMap<>();

    // Path to the database file
    String collectionDBPath;

    /**
     * Constructor for the Server class.
     * Initializes the request commands map and loads the collection from the database.
     */
    public Server() {
        this.collectionDBPath = System.getenv("DB_PATH");

        // Load the collection from the database
        String JSON_DB_string = File.read(collectionDBPath);
        if (!JSON_DB_string.isEmpty()) {
            JSONArray JSON_DB = new JSONArray(JSON_DB_string);
            for (int i = 0; i < JSON_DB.length(); ++i) {
                JSONObject vehicle = JSON_DB.getJSONObject(i);
                collectionManager.add(
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
        requestCommandsMap.put(RequestEnum.GET_COLLECTION_INFO, new CollectionInfoRequestCommand(this));
        requestCommandsMap.put(RequestEnum.GET_COLLECTION_ELEMENTS, new CollectionElementsRequestCommand(this));
        requestCommandsMap.put(RequestEnum.ADD_ELEMENT, new AddElementRequestCommand(this));
        requestCommandsMap.put(RequestEnum.UPDATE_ELEMENT, new UpdateElementRequestCommand(this));
        requestCommandsMap.put(RequestEnum.CLEAR_COLLECTION, new ClearCollectionRequestCommand(this));
        requestCommandsMap.put(RequestEnum.GET_COLLECTION_ELEMENTS_BY_TYPE, new CollectionElementsByTypeRequestCommand(this));
        requestCommandsMap.put(RequestEnum.REMOVE_ELEMENT_BY_ID, new CollectionRemoveElementByIdRequestCommand(this));
        requestCommandsMap.put(RequestEnum.SAVE_COLLECTION, new SaveCollectionRequestCommand(this));
        requestCommandsMap.put(RequestEnum.GET_ASCENDING_COLLECTION_ELEMENTS, new AscendingCollectionElementsRequestCommand(this));
        requestCommandsMap.put(RequestEnum.REMOVE_ALL_BY_ENGINE_POWER, new CollectionRemoveAllElementsByEnginePowerRequestCommand(this));
    }

    /**
     * Method to handle a request.
     * @param request The request to handle.
     * @param args The arguments for the request.
     */
    public void handleRequest(RequestEnum request, String[] args) {
        if (!requestCommandsMap.containsKey(request))
            return;

        requestCommandsMap.get(request).execute(args);
    }

    //================> Setters <================
    public void setResponseSender(ResponseSenderInterface responseSender) {
        this.responseSender = responseSender;
    }


    //================> Getters <================
    public ResponseSenderInterface getResponseSender() {
        return responseSender;
    }

    public CollectionManager getCollectionManager() {
        return collectionManager;
    }

    public String getCollectionDBPath() {
        return collectionDBPath;
    }
}