package src.pokemons;

import ru.ifmo.se.pokemon.*;
import src.attacks.*;

public class Gardevoir extends Pokemon {
    public Gardevoir(String name, int lvl) {
        super(name, lvl);
        setStats(70, 103, 80, 70, 80, 92);
        setType(Type.BUG, Type.GRASS);
        setMove(new ThunderShock(), new Blizzard(), new Octazooka(), new ThunderWave());
    }
}