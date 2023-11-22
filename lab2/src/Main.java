package src;

import ru.ifmo.se.pokemon.*;
import src.pokemons.*;

public class Main {
    public static void main(String []args) {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.out.println("\nБой не был завершён, т.к использован Ctrl+C");
            }
        });

        Battle battle = new Battle();
        Pokemon [] pokemons = {
            new Leavanny("Картофель", 1),
            new Corphish("Помидор", 2),
            new Kirlia("Огурец", 3),
            new Garbodor("Лук", 1),
            new Ralts("Чеснок", 2),
            new Gardevoir("Морковь", 3),
        };

        for(int i = 0; i < pokemons.length; ++i) {
            int mid_pokemons = pokemons.length / 2;
            if (i < mid_pokemons)
                battle.addAlly(pokemons[i % mid_pokemons]);
            else
                battle.addFoe(pokemons[i % mid_pokemons + mid_pokemons]);
        }

        battle.go();

        Main x = null;
        x.main(null);
    }
}