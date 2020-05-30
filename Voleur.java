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
    public void deplacer() {
        ArrayList<Cell> bestChemin = pathCible();
    }
}
