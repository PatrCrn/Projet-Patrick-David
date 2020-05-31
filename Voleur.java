import fr.emse.simulator.world.Cell;
import fr.emse.simulator.world.Robber;

import java.util.ArrayList;

/**
 * Cette classe représente un voleur
 * Il va chercher des pieces puis va tenter de s'enfuir avec vers une sortie
 *
 * @author (David Abab, Patrick Corneo)
 * @version (V1)
 */
public class Voleur extends Acteur implements Robber
{
    //permettra de le ralentir si il porte trop de pièces
    private int tour;

    /**
     * Constructeur d'objets de classe Voleur
     */
    public Voleur(Cellule cellule, WorldMap worldMap) {
        super(cellule, worldMap);
        this.pieces = 0;
        tour = 1;
    }

    /**
     *  Sert à déterminer le meilleure chemin aux cibles du voleur
     * @return le meilleur chemin possible
     */
    @Override
    public ArrayList<Cell> pathCible() {
        if (pieces == 2 || worldMap.getArgents().isEmpty()){
            return path(worldMap.getSorties());
        } else{
            return path(worldMap.getArgents());
        }
    }

    /**
     * Permets d'ajuster le déplacement selon le nombre de pièce qu'il porte
     */
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
        tour++;
    }

    /**
     * Permets de supprimer le voleur du monde
     */
    @Override
    public void remove() {
        worldMap.removeVoleur(this);
    }

    /**
     * Permet d'ajouter le voleur au modne
     * @param newCell la cellule ou on veut que l'acteur s'ajoute
     */
    @Override
    public void add(Cellule newCell) {
        worldMap.addVoleur(this, newCell);
    }

    /**
     * Permet aux voleurs de saisir les pièece ou de s'échapper
     * @param cellule la cellule qu'on souhaite prendr
     */
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
