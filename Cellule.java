import fr.emse.simulator.world.Cell;

/**
 * Cette classe représente une cellule du monde simulé.
 *
 * @author (David Abab, Patrick Corneo)
 * @version (V1)
 */
public class Cellule implements Cell
{
    //L'occupant de la cellule
    private Occup occup;
    // les coordonnées de la cellules
    private int row;
    private int col;

    /**
     * Constructeur d'objets de classe Cellule pour les acteurs
     */
    public Cellule(int row, int col, char c, WorldMap world)
    {
        this.row = row;
        this.col = col;
        initOccupant(c, world);
    }

    /**
     * Constructeur d'objets de classe Cellule alternative
     */
    public Cellule(int row, int col)
    {
        this.row = row;
        this.col = col;
    }

    /**
     * Permets d'initialiser les occupants selon les informations du fichier
     * @param charac représente le caratère qui définit quel occcupant mettre dans la cellule
     * @param world le monde dont la cellule et le potentiel occupant appartient
     */
    public void initOccupant(char charac, WorldMap world) {
        if (charac == '#') {
            occup = new Mur();
        } else if (charac == 'D') {
            Drone drone = new Drone(this, world);
            occup = drone;
            world.setDrones(drone);
            world.setCellDrones(this);
        } else if (charac == 'I') {
            Voleur vol = new Voleur(this, world);
            occup = vol;
            world.setVoleurs(vol);
            world.setCellVoleurs(this);
        } else if (charac == '$') {
            occup = new Argent();
            world.setArgents(this);
        } else if (charac == '_') {
            setOccupant();
            if(row == world.getNbRows() || col == world.getNbCols() || row == 0 || col == 0) {
                world.setSorties(this);
            }
        }
        world.setCell(row, this);
    }

    /**
     *  un simple getter de la collonne
     * @return l'indice de la collonne
     */
    public int getCol()
    {
        return col;
    }

    /**
     *  un simple getter de la ligne
     * @return l'indice de la ligne
     */
    public int getRow()
    {
        return row;
    }

    /**
     *  un simple getter de l'occupant
     * @return l'occupant de la cellule
     */
    public Occup getOccupant()
    {
        return occup;
    }

    /**
     *  un simple setter de l'occupant (le bloc conditionnelle permet d'éviter qu'un voleur écrase un drone)
     * @return l'occupant qu'on souhaite mettre dans la cellule
     */
    public void setOccupant(Occup newOccup)
    {
        if(occup == null) {
            occup= newOccup;
        } else if(!(newOccup.getClass() == Voleur.class && occup.getClass() == Drone.class)) {
            occup= newOccup;
        }
    }

    /**
     * Mets l'occupant de la cellule à null
     */
    public void setOccupant()
    {
        occup = null;
    }

    /**
     * Regarde si il y a un occupant sur la cellule
     * @return vrai si il y a un occupant sinon false
     */
    public boolean isEmpty()
    {
        return (occup == null);
    }

    /**
     *  Un simple equals
     * @param o l'objet qu'on souhaite comparer
     * @return vrai si c'est c'est égal sinon false
     */
    public boolean equals(Object o) {
        Cellule c = (Cellule) o;
        return row == c.getRow() && col == c.getCol() && occup == c.getOccupant();
    }
}
