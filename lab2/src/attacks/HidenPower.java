package src.attacks;

import ru.ifmo.se.pokemon.*;


public class HidenPower extends SpecialMove {
    public HidenPower() {
        super(Type.NORMAL, 50, 1);
    }

    @Override
    protected String describe() {
        return "is using Hiden Power";
    }
}
