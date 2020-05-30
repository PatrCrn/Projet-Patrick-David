import fr.emse.simulator.astar.AStarPathFinder;
import fr.emse.simulator.world.Cell;
import fr.emse.simulator.world.Occupant;
import java.util.*;
/**
 * Décrivez votre classe Acteur ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public abstract class Acteur extends Occup
{
    protected Cellule cellule;
    protected WorldMap worldMap;

    public ArrayList<Cell> path(Cellule position, HashSet<Cellule> cibles) {
        Iterator<Cellule> iteratorCibles = cibles.iterator();
        ArrayList<Cell> chemin = worldMap.getSolver().getShortestPath(position, iteratorCibles.next());

        while (iteratorCibles.hasNext()){
            ArrayList<Cell> newCible = worldMap.getSolver().getShortestPath(position, iteratorCibles.next());
            if(newCible.size() < chemin.size()){
                chemin = newCible;
            }
        }
        return chemin;
    }

    abstract public ArrayList<Cell> pathCible();

    abstract public void deplacer();

    public void setCellule(Cellule cellule) {
        this.cellule = cellule;
    }

    public void setWorldMap(WorldMap worldMap) {
        this.worldMap = worldMap;
    }
}
