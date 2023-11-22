package src.attacks;

import ru.ifmo.se.pokemon.*;

public class Whirlwind extends StatusMove {
    public Whirlwind(){
        super(Type.FLYING, 0, 1);
    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        Effect.paralyze(p);
    }

    @Override
    protected String describe() {
        return "is using Whirlwind";
    }
}
