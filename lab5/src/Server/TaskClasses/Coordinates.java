package Server.TaskClasses;

public class Coordinates {
    private double x;
    private int y; //Значение поля должно быть больше -282

    public Coordinates(double x, int y) throws IllegalArgumentException {
        if (y <= -282)
            throw new IllegalArgumentException("'y' должно быть больше -282");

        this.x = x;
        this.y = y;
    }

    public double getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}