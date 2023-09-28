package src.attacks;

import ru.ifmo.se.pokemon.*;

public class Octazooka extends SpecialMove {
    public Octazooka() {
        super(Type.WATER, 65, .85);
    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        Effect eff = new Effect();
        eff.chance(0.5);
        eff.stat(Stat.ACCURACY, -2); // Я не знаю, как это "снизить на ступень", будет в 2 раза
        p.addEffect(eff);
    }

    @Override
    protected String describe() {
        return "is using Octazooka";
    }
}
