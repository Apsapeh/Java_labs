package src.attacks;

import ru.ifmo.se.pokemon.*;

public class Blizzard extends SpecialMove {
    public Blizzard() {
        super(Type.ICE, 110, .5);
    }

    @Override
    protected String describe() {
        return "is using Blizzard";
    }
}
