import fr.emse.simulator.astar.AStarPathFinder;
import fr.emse.simulator.astar.EuclideanDistanceHeuristic;
import fr.emse.simulator.astar.Heuristic;
import fr.emse.simulator.astar.LocalCost;
import fr.emse.simulator.astar.PreferEmptyCellsLocalCost;
import fr.emse.simulator.world.*;

import java.util.*;

public class WorldMap implements SimulationMap {
    private ArrayList<ArrayList<Cellule>> cells = new ArrayList<>();
    private HashSet<Cellule> cellVoleurs = new HashSet<>();
    private HashSet<Cellule> argents = new HashSet<>();
    private HashSet<Cellule> cellDrones = new HashSet<>();
    private HashSet<Cellule> sorties = new HashSet<>();
    
    private HashSet<Voleur> voleurs = new HashSet<>();
    private HashSet<Drone> drones = new HashSet<>();
    private int nbRows;
    private int nbCols;
    private AStarPathFinder solver;

    public void addCell(int row, int col, char c){
        Cellule cellule = new Cellule(row ,col);
        if (row == cells.size()) {
            cells.add(new ArrayList<Cellule>());
        }
        if (c == '#') {
            Mur mur = new Mur();
            cellule = new Cellule (row, col, mur);
        } else if (c == 'D') {
            Drone drone = new Drone();
            cellule = new Cellule (row, col, drone);
            drone.setCellule(cellule);
            drone.setWorldMap(this);
            drones.add(drone);
            cellDrones.add(cellule);
        } else if (c == 'I') {
            Voleur vol = new Voleur();
            cellule = new Cellule (row, col, vol);
            vol.setCellule(cellule);
            vol.setWorldMap(this);
            voleurs.add(vol);
            cellVoleurs.add(cellule);
        } else if (c == '$') {
            Argent money = new Argent();
            cellule = new Cellule (row, col, money);
            argents.add(cellule);
        } else if (c == '_') {
            cellule = new Cellule (row, col);
            if(row == nbRows || col == nbCols || row == 0 || col == 0) {
                sorties.add(cellule);
            }
        }
        cells.get(row).add(cellule);
    }

    public void addDrone(Drone drone, Cellule cellule){
        set(cellule, drone);
        drone.setCellule(cellule);
    }
    public void removeDrone(Drone drone){
        set(drone.getCellule());
    }

    public void addVoleur(Voleur voleur, Cellule cellule){
        cellVoleurs.add(cellule);
        set(cellule, voleur);
        voleur.setCellule(cellule);
    }

    public void removeVoleur(Voleur voleur){
        cellVoleurs.remove(voleur.getCellule());
        set(voleur.getCellule());
    }

    public void removePiece( Cellule argentCell){
        argents.remove(argentCell);
    }

    public Cellule get(int row, int col) {
        return cells.get(row).get(col);
    }
    
    public void set(Cellule cellule, Occup occ) {
        cellule.setOccupant(occ);
    }
    public void set(Cellule cellule) {
        cellule.setOccupant();
    }
    
    public HashSet<Voleur> getVoleurs() {
        return voleurs;
    }

    public HashSet<Cellule> getArgents() {
        return argents;
    }

    public HashSet<Drone> getDrones() {
        return drones;
    }

    public HashSet<Cellule> getSorties() {
        return sorties;
    }

    public HashSet<Cellule> getCellVoleurs() {
        return cellVoleurs;
    }

    public int getNbCols() {
        return nbCols;
    }
    
    public int getNbRows() {
        return nbRows;
    }
    
    public int getNbCells() {
        return nbRows * nbCols;
    }
    
    public void setNbRows(int rows) {
        nbRows = rows;
    }
    
    public void setNbCols(int cols) {
        nbCols = cols;
    }

    public AStarPathFinder getSolver() {
        return solver;
    }

    public boolean containsSortie(Cellule cellule){
        return sorties.contains(cellule);
    }

    public void setSolver() {
        ArrayList<Class<? extends Occupant>> toIgnore = new ArrayList<Class<? extends Occupant>>();
        toIgnore.add(Drone.class);
        this.solver = new AStarPathFinder(this, new EuclideanDistanceHeuristic(), new PreferEmptyCellsLocalCost(1, 5), toIgnore);;
    }
}
