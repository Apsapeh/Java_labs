package src.attacks;

import ru.ifmo.se.pokemon.*;

public class ShadowPunch extends PhysicalMove {
    public ShadowPunch(){
        super(Type.GHOST, 60, 1);
    }

    @Override
    protected String describe() {
        return "is using Shadow Punch";
    }
}

