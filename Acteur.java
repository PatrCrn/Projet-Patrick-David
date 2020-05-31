import fr.emse.simulator.world.Cell;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Cette classe représente un acteur du monde
 * Il peut se déplacer et intéragir avec d'autres occupants
 *
 * @author (David Abab, Patrick Corneo)
 * @version (V1)
 */
public abstract class Acteur extends Occup
{
    // la cellule et le monde ou le monde appatient
    protected Cellule cellule;
    protected WorldMap worldMap;
    // le nombre de pièces qu'à l'acteur
    protected int pieces;

    /**
     * Le constructeur
     * @param cellule la cellule où l'acteur est l'occupant
     * @param worldMap le monde ou l'acteur appartient
     */
    public Acteur(Cellule cellule, WorldMap worldMap) {
        this.cellule = cellule;
        this.worldMap = worldMap;
    }

    /**
     * Permets d'établir le meilleure chemin selon l'objectif le plus proche
     * @param cibles l'ensemble des cibles que l'acteur poursuit pour le moment
     * @return le meilleur chemin possible
     */
    public ArrayList<Cell> path(HashSet<Cellule> cibles) {
        Iterator<Cellule> iteratorCibles = cibles.iterator();
        ArrayList<Cell> chemin = new ArrayList<Cell>();
        if(iteratorCibles.hasNext()) {
            chemin = worldMap.getSolver().getShortestPath(cellule, iteratorCibles.next());
        }
        while (iteratorCibles.hasNext()){
            ArrayList<Cell> newCible = worldMap.getSolver().getShortestPath(cellule, iteratorCibles.next());
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

    /**
     *  Sert à déterminer le meilleure chemin par rapport aux cibles de l'acteur
     * @return le meilleur chemin possible
     */
    abstract public ArrayList<Cell> pathCible();

    /**
     *  Permets d'ajouter l'acteur à une nouvelle cellule
     * @param newCell la cellule ou on veut que l'acteur s'ajoute
     */
    abstract public void add(Cellule newCell);

    /**
     * Permets de retirer l'acteur de sa cellule actuelle
     */
    abstract public void remove();

    /**
     * Permet de prendre un autre occupant
     * @param cellule la cellule qu'on souhaite prendr
     */
    abstract public void saisir(Cellule cellule);

    /**
     * Permet d'effectuer un déplacement sur le monde en changeant de cellule
     */
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

    /**
     *  Permets de changer la cellule de l'acteur
     * @param cellule la cellule qu'on souhaite changer
     */
    public void setCellule(Cellule cellule) {
        this.cellule = cellule;
    }

    /**
     * Permets d'obtenir la cellule actuel de l'acteur
     * @return
     */
    public Cellule getCellule() {
        return cellule;
    }

    /**
     * Permet de rajouter une piece
     */
    public void setPieces() {
        pieces++;
    }

    /**
     * Permets de rajouter une certaine somme de piece
     * @param p le nb de piece qu'on souhaite rajouter
     */
    public void setPieces(int p) {
        pieces += p;
    }

    /**
     * Permets d'obtenir le nombre de piece qu'à l'acteur
     * @return le nombre de piece qu'à l'acteur
     */
    public int getPieces() {
        return pieces;
    }
}
