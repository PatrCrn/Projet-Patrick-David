import fr.emse.simulator.world.Cell;
import fr.emse.simulator.world.Robot;

import java.util.ArrayList;

/**
 * Cette classe représente un drone
 * Il va pouchasser les voleurs
 *
 * @author (David Abab, Patrick Corneo)
 * @version (V1)
 */
public class Drone extends Acteur implements Robot
{
    /**
     * Il s'agit du constructeur du drone
     */
    public Drone(Cellule cellule, WorldMap worldMap) {
        super(cellule, worldMap);
    }

    /**
     * Sert à déterminer le meilleure chemin par rapport aux voleurs
     * si y a plus de voleurs et qu'il doit se déplacer alors il restera sur place
     * @return le meilleures chemin
     */
    @Override
    public ArrayList<Cell> pathCible() {
        if (!worldMap.getCellVoleurs().isEmpty()) {
            return path(worldMap.getCellVoleurs());
        } else {
            return null;
        }
    }

    /**
     * Permets de supprimer le drone au monde
     */
    @Override
    public void remove() {
        worldMap.removeDrone(this);
    }

    /**
     * Permet d'ajouter le drone au monde
     * @param newCell la cellule ou on veut que l'acteur s'ajoute
     */
    @Override
    public void add(Cellule newCell) {
        worldMap.addDrone(this, newCell);
    }

    /**
     * Permets de saisir ces satanés voleurs
     * @param cell la cellule ou se trouve le voleur à saisir
     */
    @Override
    public void saisir(Cellule cell) {
        pieces += ((Acteur)cell.getOccupant()).getPieces();
        worldMap.removeVoleur((Voleur) cell.getOccupant());
    }
}
