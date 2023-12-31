package src.attacks;


import ru.ifmo.se.pokemon.*;

public class Thunderbolt extends SpecialMove {
    public Thunderbolt() {
        super(Type.ELECTRIC, 90, 1);
    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        if (Math.random() <= 0.1)
            Effect.paralyze(p);
    }

    @Override
    protected String describe() {
        return "is using Thunderbolt";
    }
}
