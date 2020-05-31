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
    private int attente;
    private int tour;

    /**
     * Constructeur d'objets de classe Voleur
     */
    public Voleur() {
        this.pieces = 0;
        attente = 0;
        tour = 1;
    }

    public void setPieces() {
        pieces++;
    }

    @Override
    public ArrayList<Cell> pathCible() {
        if (pieces == 2 || worldMap.getArgents().isEmpty()){
            return path(cellule, worldMap.getSorties());
        } else{
            return path(cellule, worldMap.getArgents());
        }
    }
    
    public void deplacer(){
        if(pieces == 0) {
            super.deplacer();
            return;
        }
        
        if(tour == 1) {
            super.deplacer();
        } else if(pieces == 1 && tour == 2) {
            super.deplacer();
        } else if (tour == 3) {
            tour = 1;
            return;
        }
        attente++;
        tour++;
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
}
