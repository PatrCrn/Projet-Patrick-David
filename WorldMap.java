import fr.emse.simulator.astar.AStarPathFinder;
import fr.emse.simulator.astar.EuclideanDistanceHeuristic;
import fr.emse.simulator.astar.PreferEmptyCellsLocalCost;
import fr.emse.simulator.world.Occupant;
import fr.emse.simulator.world.SimulationMap;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Cette classe représente la carte qui sera simulé par le simulateur.
 * Elle traite l'ensemble de la gestion de son contenu.
 *
 * @author (David Abab, Patrick Corneo)
 * @version (V1)
 */
public class WorldMap implements SimulationMap {
    //La liste des cellules de la map
    private ArrayList<ArrayList<Cellule>> cells = new ArrayList<>();

    //des liste de cellules occupé par certains occupants
    private HashSet<Cellule> cellVoleurs = new HashSet<>();
    private HashSet<Cellule> argents = new HashSet<>();
    private HashSet<Cellule> cellDrones = new HashSet<>();
    private HashSet<Cellule> sorties = new HashSet<>();

    //des listes des acteurs de la map
    private HashSet<Voleur> voleurs = new HashSet<>();
    private HashSet<Drone> drones = new HashSet<>();

    //les dimension de la map
    private int nbRows;
    private int nbCols;
    private AStarPathFinder solver;

    /**
     * permets d'ajouter une cellule dans le monde
     * @param row la coordonné de la ligne
     * @param col la coordonné de la ligne
     * @param charac le charactère qui définira l'occupant dans la cellule
     */
    public void addCell(int row, int col, char charac){
        if (row == cells.size()) {
            cells.add(new ArrayList<Cellule>());
        }
        new Cellule(row ,col, charac, this);
    }

    /**
     * un simple setter pour ajouter une cellule au monde
     * @param row la ligne où l'ajoute
     * @param cel la collonne où l'ajouter
     */
    public void setCell(int row, Cellule cel) {
        cells.get(row).add(cel);
    }

    /**
     * permet d'ajouter un drone au monde
     * @param drone le drone qu'on souhaite ajouter
     * @param cellule la cellule ou on souhaite l'ajouter
     */
    public void addDrone(Drone drone, Cellule cellule){
        set(cellule, drone);
        drone.setCellule(cellule);
    }

    /**
     * Permets de supprimer un drone du monde
     * @param drone le drone qu'on souhaite supprimer
     */
    public void removeDrone(Drone drone){
        set(drone.getCellule());
    }

    /**
     * permet d'ajouter un voleur au monde si possible
     * @param voleur le voleur qu'on souhaite ajouter
     * @param cellule la cellule ou on souhaite l'ajouter
     */
    public void addVoleur(Voleur voleur, Cellule cellule){
        if(cellule.getOccupant() == null) {
            cellVoleurs.add(cellule);
            set(cellule, voleur);
            voleur.setCellule(cellule);
        } else {
            ((Drone)cellule.getOccupant()).setPieces(voleur.getPieces());
        }
    }

    /**
     * Permets de supprimer un voleur du monde
     * @param voleur le voleur qu'on souhaite supprimer
     */
    public void removeVoleur(Voleur voleur){
        cellVoleurs.remove(voleur.getCellule());
        set(voleur.getCellule());
    }

    /**
     * Permets de supprimer une piece du monde
     * @param argentCell la piece qu'on souhaite supprimer
     */
    public void removePiece( Cellule argentCell){
        argents.remove(argentCell);
    }

    /**
     * Permet d'obtenir une cellule selon des coordonnees
     * @param row la ligne
     * @param col la collonne
     * @return la cellule selon les coordonnées
     */
    public Cellule get(int row, int col) {
        return cells.get(row).get(col);
    }

    /**
     * permets de modifer une cellule en lui attribuant un occupant
     * @param cellule la cellule qu'on souhaite modifier
     * @param occ l'occupant qu'on souhaite attribuer
     */
    public void set(Cellule cellule, Occup occ) {
        cellule.setOccupant(occ);
    }

    /**
     * permets de modifer une cellule en lui attribuant rien
     * @param cellule la cellule qu'on souhaite mettre à null
     */
    public void set(Cellule cellule) {
        cellule.setOccupant();
    }

    /**
     * permets d'obtenir la liste des voleurs
     * @return les voleurs du monde
     */
    public HashSet<Voleur> getVoleurs() {
        return voleurs;
    }

    /**
     * permets d'obtenir la liste des cellules de l'argent
     * @return cellules de l'argent
     */
    public HashSet<Cellule> getArgents() {
        return argents;
    }

    /**
     * permets d'obtenir la liste des drones
     * @return les drones du monde
     */
    public HashSet<Drone> getDrones() {
        return drones;
    }

    /**
     * permets d'obtenir la liste des cellules des sorties
     * @return les cellules des sorties du monde
     */
    public HashSet<Cellule> getSorties() {
        return sorties;
    }

    /**
     * permets d'obtenir la liste des cellules des voleurs
     * @return les voleurs des sorties du monde
     */
    public HashSet<Cellule> getCellVoleurs() {
        return cellVoleurs;
    }

    /**
     * permets d'obtenir le nombre de collonnes
     * @return le nombre de collonnes
     */
    public int getNbCols() {
        return nbCols;
    }

    /**
     * permets d'obtenir le nombre de lignes
     * @return le nombre de lignes
     */
    public int getNbRows() {
        return nbRows;
    }

    /**
     * permets d'obtenir la surface du monde
     * @return la surface du monde
     */
    public int getNbCells() {
        return nbRows * nbCols;
    }

    /**
     * Permets d'initialiser le nombre de lignes
     * @param rows le nombre de lignes
     */
    public void setNbRows(int rows) {
        nbRows = rows;
    }

    /**
     * Permets d'initialiser le nombre de cols
     * @param cols le nombre de cols
     */
    public void setNbCols(int cols) {
        nbCols = cols;
    }

    /**
     * Permets de rajouter un drone à la liste de drones
     * @param drone un drone à rajouter
     */
    public void setDrones(Drone drone) {
        drones.add(drone);
    }

    /**
     * Permets de rajouter une cellule de drone à la liste de cellule de drones
     * @param cellule une cellule de drone à rajouter
     */
    public void setCellDrones(Cellule cellule) {
        cellDrones.add(cellule);
    }

    /**
     * Permets de rajouter un voleur à la liste de voleurs
     * @param vol un voleur à rajouter
     */
    public void setVoleurs(Voleur vol) {
        voleurs.add(vol);
    }

    /**
     * Permets de rajouter une cellule de voleur à la liste de cellule de voleurs
     * @param cellule une cellule de voleur à rajouter
     */
    public void setCellVoleurs(Cellule cellule) {
        cellVoleurs.add(cellule);
    }

    /**
     * Permets de rajouter une cellule d'argent à la liste de cellule d'argent
     * @param cellule une cellule d'argent à rajouter
     */
    public void setArgents(Cellule cellule) {
        argents.add(cellule);
    }

    /**
     * Permets de rajouter une cellule de sortie à la liste de cellule de sortie
     * @param cellule une cellule de sortie à rajouter
     */
    public void setSorties(Cellule cellule) {
        sorties.add(cellule);
    }

    /**
     * permet d'objeter le solver (pou établir le chemon le plus rapide) de ce monde
     * @return le solver de ce monde
     */
    public AStarPathFinder getSolver() {
        return solver;
    }

    /**
     * Permetds de savoir si la cellule vide fait partie des sorties
     * @param cellule la cellule vide qu'on souhaite vérifier
     * @return vrai si il fait partie des sorties sinon false
     */
    public boolean containsSortie(Cellule cellule){
        return sorties.contains(cellule);
    }

    /**
     * Permet d'établir le solver de ce monde cette méthode est essentielle
     * Elle doit être lancer une fois que les dimensions(lignes, collonnes) sont initialisées
     */
    public void setSolver() {
        ArrayList<Class<? extends Occupant>> toIgnore = new ArrayList<Class<? extends Occupant>>();
        toIgnore.add(Drone.class);
        this.solver = new AStarPathFinder(this, new EuclideanDistanceHeuristic(), new PreferEmptyCellsLocalCost(1, 5), toIgnore);
    }
}
