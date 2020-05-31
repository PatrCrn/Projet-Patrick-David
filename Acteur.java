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
    protected int pieces;
    
    public Acteur(Cellule cellule, WorldMap worldMap) {
        this.cellule = cellule;
        this.worldMap = worldMap;
    }

    public ArrayList<Cell> path(Cellule position, HashSet<Cellule> cibles) {
        Iterator<Cellule> iteratorCibles = cibles.iterator();
        ArrayList<Cell> chemin = new ArrayList<Cell>();
        if(iteratorCibles.hasNext()) {
            chemin = worldMap.getSolver().getShortestPath(position, iteratorCibles.next());
        }
        while (iteratorCibles.hasNext()){
            ArrayList<Cell> newCible = worldMap.getSolver().getShortestPath(position, iteratorCibles.next());
            if (newCible != null){
                if(chemin == null){
                    chemin = newCible;
                }
                if (newCible.size() < chemin.size()) {
                    chemin = newCible;
                }
            }
        }
        return chemin;
    }

    abstract public ArrayList<Cell> pathCible();
    abstract public void add(Cellule newCell);
    abstract public void remove();
    abstract public void saisir(Cellule cellule);

    public void deplacer(){
        ArrayList<Cell> bestChemin = pathCible();
        if (bestChemin != null) {
            Cell cell = bestChemin.get(0);;
            if(bestChemin.size() >= 2) {
                cell = bestChemin.get(1);
            }
            if (bestChemin.size() > 2) {
                remove();
                add((Cellule) cell);
            } else {
                saisir((Cellule) cell);
                worldMap.set((Cellule) cell);
            }
        }
    }

    public void setCellule(Cellule cellule) {
        this.cellule = cellule;
    }

    public void setWorldMap(WorldMap worldMap) {
        this.worldMap = worldMap;
    }

    public Cellule getCellule() {
        return cellule;
    }
    
    public void setPieces() {
        pieces++;
    }
    
    public void setPieces(int p) {
        pieces += p;
    }
    
    public int getPieces() {
        return pieces;
    }
}
