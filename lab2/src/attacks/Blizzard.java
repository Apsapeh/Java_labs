package src.attacks;

import ru.ifmo.se.pokemon.*;

import java.util.Vector;

public class Blizzard extends SpecialMove {
    public Blizzard() {
        super(Type.ICE, 110, .5);

    }

    //@Override
    protected String describe() {
        return "is using Blizzard";
    }
}

class P {
    void over(){

    }

    public Object print() {
        return null;
    }

    public final void fin() {

    }

    protected float data() {
        return 0.0f;
    }
}

class Child extends P {
    @Override
    protected void over() {

    }

    @Override
    public Vector<String> print() {
        return new Vector<String>(1);
    }

    @Override
    public float data() {
        super.fin();
        return 0;
    }

}

class PP extends Child {
    @Override
    public void over() {

    }
}