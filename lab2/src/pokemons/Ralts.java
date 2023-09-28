package src.pokemons;

import ru.ifmo.se.pokemon.*;
import src.attacks.*;

public class Ralts extends Pokemon {
    public Ralts(String name, int lvl) {
        super(name, lvl);
        setStats(28, 25, 25, 45, 35, 40);
        setType(Type.PSYCHIC, Type.FAIRY);
        setMove(new ThunderShock(), new Blizzard());
    }
}