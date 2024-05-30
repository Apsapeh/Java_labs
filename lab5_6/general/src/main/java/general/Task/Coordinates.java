package general.Task;

import java.io.Serializable;

public class Coordinates implements Serializable {
    private Integer x; //Поле не может быть null
    private int y; //Значение поля должно быть больше -282

    public Coordinates(Integer x, int y) throws IllegalArgumentException {
        if (y <= -282)
            throw new IllegalArgumentException("'y' должно быть больше -282");

        this.x = x;
        this.y = y;
    }

    public Integer getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }
}
