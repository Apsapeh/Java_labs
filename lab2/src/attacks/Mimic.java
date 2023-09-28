package src.attacks;

import ru.ifmo.se.pokemon.*;

public class Mimic extends StatusMove {
    public Mimic() {
        super(Type.NORMAL, 0, 1);
    }

    @Override
    protected String describe() {
        return "is using Mimic";
    }
}
