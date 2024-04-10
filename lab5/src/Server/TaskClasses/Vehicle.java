package Server.TaskClasses;

public class Vehicle {
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Float enginePower; //Поле может быть null, Значение поля должно быть больше 0
    private VehicleType type; //Поле может быть null
    private FuelType fuelType; //Поле может быть null

    private static int staticID = 0;

    Vehicle (
            String name, Coordinates coordinates, float enginePower,
            VehicleType type, FuelType fuelType
    ) throws IllegalArgumentException {
        if (name == null || coordinates == null)
            throw new IllegalArgumentException("'name' или 'coordinates' не могут быть null");

        this.id = staticID++;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = java.time.LocalDate.now();
    }

    public void setEnginePower(Float enginePower) {
        this.enginePower = enginePower;
    }

    public  void setType(VehicleType type) {
        this.type = type;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public int getID() {
        return this.id;
    }

    @Override
    public String toString() {
        return String.format("Vehicle{id=%s, name=%s, coordinates=%s, creationDate=%s, enginePower=%s, type=%s, fuelType=%s}", this.id, this.name, this.coordinates, this.creationDate, this.enginePower, this.type, this.fuelType);
    }
}