package src.attacks;

import ru.ifmo.se.pokemon.*;

public class ThunderShock extends SpecialMove {
    public ThunderShock() {
        super(Type.ELECTRIC, 40, 1);
    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        if (Math.random() <= 0.1)
            Effect.paralyze(p);
    }

    @Override
    protected String describe() {
        return "is using Thunder Shock";
    }
}
