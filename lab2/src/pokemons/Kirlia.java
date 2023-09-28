package src.pokemons;

import ru.ifmo.se.pokemon.*;
import src.attacks.*;

public class Kirlia extends Pokemon {
    public Kirlia(String name, int lvl) {
        super(name, lvl);
        setStats(38, 35, 35, 65, 55, 50);
        setType(Type.PSYCHIC, Type.FAIRY);
        setMove(new ThunderShock(), new Blizzard(), new Octazooka());
    }
}