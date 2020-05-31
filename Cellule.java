import fr.emse.simulator.world.Cell;
import fr.emse.simulator.world.Occupant;
/**
 * Décrivez votre classe Cellule ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class Cellule implements Cell
{
    private Occup occup;
    private int row;
    private int col;

    /**
     * Constructeur d'objets de classe Cellule
     */
    public Cellule(int row, int col, Occup occup)
    {
        this.row = row;
        this.col = col;
        this.occup = occup;
    }
    
    public Cellule(int row, int col)
    {
        this.row = row;
        this.col = col;
    }

    /**
     * Un exemple de méthode - remplacez ce commentaire par le vôtre
     */
    public int getCol()
    {
        return col;
    }
    
    public int getRow()
    {
        return row;
    }
    
    public Occup getOccupant()
    {
        return occup;
    }
    
    public void setOccupant(Occup newOccup)
    {
        if(occup == null) {
            occup= newOccup;
        } else if(!(newOccup.getClass() == Voleur.class && occup.getClass() == Drone.class)) {
            occup= newOccup;
        }
    }
    public void setOccupant()
    {
        occup = null;
    }
    
    public boolean isEmpty()
    {
        return (occup == null);
    }

    public boolean equals(Object o) {
        Cellule c = (Cellule) o;
        return row == c.getRow() && col == c.getCol() && occup == c.getOccupant();
    }
}
