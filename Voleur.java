import fr.emse.simulator.world.Robber;
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
    public Voleur()
    {
        pieces = 0;
    }
    
    public void setPieces() {
        pieces++;
    }
}
