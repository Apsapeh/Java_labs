package org.apsapeh.General.Task;

public class Vehicle implements Comparable<Vehicle> {
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Long enginePower; //Поле может быть null, Значение поля должно быть больше 0
    private VehicleType type; //Поле может быть null
    private FuelType fuelType; //Поле может быть null

    public static int staticID = 0;

    public Vehicle(
            String name,
            Coordinates coordinates,
            Long enginePower,
            VehicleType type,
            FuelType fuelType
    ) {
        this.id = ++staticID;
        this.creationDate = java.time.LocalDateTime.now();
        setName(name);
        setCoordinates(coordinates);
        setEnginePower(enginePower);
        setType(type);
        setFuelType(fuelType);
    }

    public Vehicle(
            Integer id,
            String name,
            Coordinates coordinates,
            java.time.LocalDateTime creationDate,
            Long enginePower,
            VehicleType type,
            FuelType fuelType
    ) {
        this.id = id;
        this.creationDate = creationDate;
        setName(name);
        setCoordinates(coordinates);
        setEnginePower(enginePower);
        setType(type);
        setFuelType(fuelType);
    }

    @Override
    public int compareTo(Vehicle vehicle) {
        return id.compareTo(vehicle.getID());
    }

    //==============> Getters <==============//
    public Integer getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public java.time.LocalDateTime getCreationDate() {
        return creationDate;
    }

    public Long getEnginePower() {
        return enginePower;
    }

    public VehicleType getType() {
        return type;
    }

    public FuelType getFuelType() {
        return fuelType;
    }


    //==============> Setters <==============//
    public void setName(String name) throws IllegalArgumentException {
        if (name == null || name.isEmpty())
            throw new IllegalArgumentException("'name' не может быть null или пустым");
        this.name = name;
    }

    public void setCoordinates(Coordinates coordinates) throws IllegalArgumentException {
        if (coordinates == null)
            throw new IllegalArgumentException("'coordinates' не может быть null");
        this.coordinates = coordinates;
    }

    public void setEnginePower(Long enginePower) throws IllegalArgumentException {
        if (enginePower == null ||  enginePower <= 0)
            throw new IllegalArgumentException("'enginePower' не может быть null или <= 0");
        this.enginePower = enginePower;
    }

    public void setType(VehicleType type) throws IllegalArgumentException {
        if (type == null)
            throw new IllegalArgumentException("'type' не может быть null");
        this.type = type;
    }

    public void setFuelType(FuelType fuelType) throws IllegalArgumentException {
        if (fuelType == null)
            throw new IllegalArgumentException("'fuelType' не может быть null");
        this.fuelType = fuelType;
    }

    @Override
    public String toString() {
        return "Vehicle {" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates.toString() +
                ", creationDate=" + creationDate +
                ", enginePower=" + enginePower +
                ", type=" + type +
                ", fuelType=" + fuelType +
                '}';
    }
}
