package src.pokemons;

import ru.ifmo.se.pokemon.*;
import src.attacks.*;

public class Corphish extends Pokemon {
    public Corphish(String name, int lvl) {
        super(name, lvl);
        setStats(43, 80, 65, 50, 35, 35);
        setType(Type.WATER);
        setMove(new ThunderWave(), new ShadowPunch(), new Whirlwind(), new SkillSwap());
    }
}