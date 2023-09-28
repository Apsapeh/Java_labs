package src.attacks;

import ru.ifmo.se.pokemon.*;

public class SkillSwap extends StatusMove {
    public SkillSwap() {
        super(Type.PSYCHIC, 0, 1);
    }

    @Override
    protected String describe() {
        return "is using Skill Swap";
    }
}
