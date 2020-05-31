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
    public Cellule(int row, int col, char c, WorldMap world)
    {
        this.row = row;
        this.col = col;
        initOccupant(c, world);
    }
    
    public Cellule(int row, int col)
    {
        this.row = row;
        this.col = col;
    }
    
    public void initOccupant(char c, WorldMap world) {
        if (c == '#') {
            Mur mur = new Mur();
            occup = mur;
        } else if (c == 'D') {
            Drone drone = new Drone(this, world);
            occup = drone;
            world.setDrones(drone);
            world.setCellDrones(this);
        } else if (c == 'I') {
            Voleur vol = new Voleur(this, world);
            occup = vol;
            world.setVoleurs(vol);
            world.setCellVoleurs(this);
        } else if (c == '$') {
            Argent money = new Argent();
            occup = money;
            world.setArgents(this);
        } else if (c == '_') {
            setOccupant();
            if(row == world.getNbRows() || col == world.getNbCols() || row == 0 || col == 0) {
                world.setSorties(this);
            }
        }
        world.setCell(row, this);
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
