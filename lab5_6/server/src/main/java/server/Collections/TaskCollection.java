package server.Collections;

import general.Task.Vehicle;

import java.util.HashSet;

/**
 * This class extends HashSet and is responsible for managing a collection of Vehicle objects.
 * It provides methods for adding, clearing, getting and removing vehicles by their ID, and removing all vehicles by their engine power.
 */
public class TaskCollection extends HashSet<Vehicle>{
    // The date when the collection was created
    private final java.time.LocalDate creationDate;

    /**
     * Constructor for the CollectionManager class.
     * Initializes the creationDate to the current date.
     */
    public TaskCollection() {
        creationDate = java.time.LocalDate.now();
    }

    /**
     * Adds a vehicle to the collection.
     * @param o The vehicle to be added.
     * @return true if the vehicle was added successfully, false otherwise.
     */
    public boolean add(Vehicle o) {
        return super.add(o);
    }

    /**
     * Clears the collection.
     */
    public void clear() {
        super.clear();
    }

    /**
     * Gets a vehicle from the collection by its ID.
     * @param id The ID of the vehicle to get.
     * @return The vehicle with the given ID, or null if no such vehicle exists.
     */
    public Vehicle getById(Integer id) {
        for (Vehicle vehicle : this)
            if (vehicle.getID().equals(id))
                return vehicle;
        return null;
    }

    /**
     * Removes a vehicle from the collection by its ID.
     * @param id The ID of the vehicle to remove.
     * @return The removed vehicle, or null if no such vehicle exists.
     */
    public Vehicle removeById(Integer id) {
        Vehicle vehicle = getById(id);
        if (vehicle != null) {
            super.remove(vehicle);
            return vehicle;
        }
        return null;
    }

    /**
     * Removes all vehicles from the collection with a given engine power.
     * @param enginePower The engine power of the vehicles to remove.
     */
    public void removeAllByEnginePower(Long enginePower) {
        for (Vehicle vehicle : this)
            if (vehicle.getEnginePower().equals(enginePower))
                super.remove(vehicle);
    }

    /**
     * Returns a string representation of the collection.
     * @return A string representation of the collection.
     */
    @Override
    public String toString() {
        return "Server.Collections.CollectionManager {" + "init_date=" + creationDate +
                ", collection_type=" + super.getClass().getName() +
                ", collection_size=" + super.size() +
                '}';
    }

    /**
     * Returns a pretty string representation of the collection.
     * @return A pretty string representation of the collection.
     */
    public String toPrettyString() {
        return "Коллекция:\n" +
                "\tДата создания: " + creationDate + "\n" +
                "\tТип коллекции: " + super.getClass().getName() + "<Server.TaskClasses.Vehicle>\n" +
                "\tКоличество элементов: " + super.size();
    }
}