import fr.emse.simulator.world.Cell;
import fr.emse.simulator.world.Robber;

import java.util.ArrayList;

/**
 * Décrivez votre classe Voleur ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class Voleur extends Acteur implements Robber
{
    // variables d'instance - remplacez l'exemple qui suit par le vôtre
    private int pieces;

    /**
     * Constructeur d'objets de classe Voleur
     */
    public Voleur() {
        this.pieces = 0;
    }

    public void setPieces() {
        pieces++;
    }

    @Override
    public ArrayList<Cell> pathCible() {
        if (pieces == 2){
            return path(cellule, worldMap.getSorties());
        } else{
            return path(cellule, worldMap.getArgents());
        }
    }

    @Override
    public void remove() {
        worldMap.removeVoleur(this);
    }

    @Override
    public void add(Cellule newCell) {
        worldMap.addVoleur(this, newCell);
    }

    @Override
    public void saisir(Cellule cellule) {
        if(pieces == 2 && worldMap.containsSortie(cellule)){
            remove();
        } else {
            worldMap.removePiece(cellule);
            setPieces();
        }
    }

    //    @Override
//    public void deplacer() {
//        ArrayList<Cell> bestChemin = pathCible();
//
//        for(Cellule c: worldMap.getVoleurs()) {
//            if (c.equals(cellule)) {
//                worldMap.getVoleurs().remove(c);
//                break;
//            }
//        }
//        worldMap.set(cellule.getRow(), cellule.getCol());
//
//        Cell cell = bestChemin.get(1);
//        worldMap.set(cell.getRow(), cell.getCol(), this);
//
//        cellule = worldMap.get(cell.getRow(), cell.getCol());
//
//        verifVoisin(cellule.getRow(), cellule.getCol());
//    }
//
//    private void verifVoisin(int row, int col) {
//        if ((Class)worldMap.get(row-1, col-1).getClass() == Argent.class) {
//            worldMap.set(row-1, col-1);
//            setPieces();
//        } else if ((Class)worldMap.get(row-1, col).getClass() == Argent.class) {
//            worldMap.set(row-1, col);
//            setPieces();
//        } else if ((Class)worldMap.get(row-1, col+1).getClass() == Argent.class) {
//            worldMap.set(row-1, col+1);
//            setPieces();
//        } else if ((Class)worldMap.get(row, col-1).getClass() == Argent.class) {
//            worldMap.set(row, col-1);
//            setPieces();
//        } else if ((Class)worldMap.get(row, col+1).getClass() == Argent.class) {
//            worldMap.set(row, col+1);
//            setPieces();
//        } else if ((Class)worldMap.get(row+1, col-1).getClass() == Argent.class) {
//            worldMap.set(row+1, col-1);
//            setPieces();
//        } else if ((Class)worldMap.get(row+1, col).getClass() == Argent.class) {
//            worldMap.set(row+1, col);
//            setPieces();
//        } else if ((Class)worldMap.get(row+1, col+1).getClass() == Argent.class) {
//            worldMap.set(row+1, col+1);
//            setPieces();
//        }
//    }
}
