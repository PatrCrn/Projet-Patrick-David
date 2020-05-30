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
    private Occupant occupant;
    private int row;
    private int col;

    /**
     * Constructeur d'objets de classe Cellule
     */
    public Cellule(int row, int col, Occupant occupant)
    {
        this.row = row;
        this.col = col;
        this.occupant = occupant;
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
    public Occupant getOccupant()
    {
        return occupant;
    }
    
    public void setCol(int newCol)
    {
        col = newCol;
    }
    public void setRow(int newRow)
    {
        row = newRow;
    }
    public void setOccupant(Occupant newOccupant)
    {
        occupant = newOccupant;
    }
    public void setOccupant()
    {
        occupant = null;
    }
    
    public boolean isEmpty()
    {
        return (occupant == null) || (occupant.getClass() != Voleur.class && occupant.getClass() != Drone.class);
    }
    
    public boolean equals(Object o) {
        Cellule c = (Cellule) o;
        return row == c.getRow() && col == c.getCol() && occupant == c.getOccupant();
    }
}
