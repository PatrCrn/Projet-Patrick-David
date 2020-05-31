import fr.emse.simulator.world.Cell;
import fr.emse.simulator.world.Robot;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Décrivez votre classe Drone ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class Drone extends Acteur implements Robot
{
    public Drone(Cellule cellule, WorldMap worldMap) {
        super(cellule, worldMap);
    }
    
    @Override
    public ArrayList<Cell> pathCible() {
        if (!worldMap.getCellVoleurs().isEmpty()) {
            return path(cellule, worldMap.getCellVoleurs());
        } else {
            return null;
        }
    }

    @Override
    public void remove() {
        worldMap.removeDrone(this);
    }

    @Override
    public void add(Cellule newCell) {
        worldMap.addDrone(this, newCell);
    }

    @Override
    public void saisir(Cellule cell) {
        pieces += ((Acteur)cell.getOccupant()).getPieces();
        worldMap.removeVoleur((Voleur) cell.getOccupant());
    }
}
