import fr.emse.simulator.world.Cell;
import fr.emse.simulator.world.Robot;

import java.util.ArrayList;

/**
 * Décrivez votre classe Drone ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class Drone extends Acteur implements Robot
{


    @Override
    public ArrayList<Cell> pathCible() {
        return path(cellule, worldMap.getVoleurs());
    }

    @Override
    public void deplacer() {
        ArrayList<Cell> bestChemin = pathCible();
    }
}
