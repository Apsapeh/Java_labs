package src.pokemons;

import ru.ifmo.se.pokemon.*;
import src.attacks.*;

public class Garbodor extends Pokemon {
    public Garbodor(String name, int lvl) {
        super(name, lvl);
        setStats(80, 95, 82, 60, 82, 75);
        setType(Type.POISON);
        setMove(new ThunderWave(), new ShadowPunch(), new Whirlwind());
    }
}