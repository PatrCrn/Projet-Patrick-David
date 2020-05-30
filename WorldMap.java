import fr.emse.simulator.astar.AStarPathFinder;
import fr.emse.simulator.astar.EuclideanDistanceHeuristic;
import fr.emse.simulator.astar.Heuristic;
import fr.emse.simulator.astar.LocalCost;
import fr.emse.simulator.astar.PreferEmptyCellsLocalCost;

import fr.emse.simulator.world.Cell;
import fr.emse.simulator.world.Coin;
import fr.emse.simulator.world.Occupant;
import fr.emse.simulator.world.SimulationMap;

import java.util.*;

public class WorldMap implements SimulationMap {
    private ArrayList<ArrayList<Cellule>> cells = new ArrayList<>();
    private HashSet<Cellule> voleurs = new HashSet<>();
    private HashSet<Cellule> argents = new HashSet<>();
    private HashSet<Cellule> drones = new HashSet<>();
    private HashSet<Cellule> sorties = new HashSet<>();
    private int nbRows;
    private int nbCols;

    private static ArrayList<Class<? extends Occupant>> toIgnore = new ArrayList<Class<? extends Occupant>>();
    private  AStarPathFinder solver = new AStarPathFinder(this, new EuclideanDistanceHeuristic(), new PreferEmptyCellsLocalCost(1, 3), toIgnore);

    public WorldMap() {
        toIgnore.add(Drone.class);
    }

    public void addCell(int row, int col, char c){
        Cellule cellule;
        if (row == cells.size()) {
            cells.add(new ArrayList<Cellule>());
        }
        if (c == '#') {
            Mur mur = new Mur();
            cellule = new Cellule (row, col, mur);
            cells.get(row).add(cellule);
        } else if (c == 'D') {
            Drone drone = new Drone();
            cellule = new Cellule (row, col, drone);
            drone.setCellule(cellule);
            drone.setWorldMap(this);
            cells.get(row).add(cellule);
            drones.add(cellule);
        } else if (c == 'I') {
            Voleur vol = new Voleur();
            cellule = new Cellule (row, col, vol);
            vol.setCellule(cellule);
            vol.setWorldMap(this);
            cells.get(row).add(cellule);
            voleurs.add(cellule);
        } else if (c == '$') {
            Argent money = new Argent();
            cellule = new Cellule (row, col, money);
            cells.get(row).add(cellule);
            argents.add(cellule);
        } else if (c == '_') {
            cellule = new Cellule (row, col);
            cells.get(row).add(cellule);
            if(row == nbRows || col == nbCols || row == 0 || col == 0) {
                sorties.add(cellule);
            }
        }
    }
    
    public Cellule get(int row, int col) {
        return cells.get(row).get(col);
    }
    
    public ArrayList<ArrayList<Cellule>> getCells() {
        return cells;
    }
    
    public void set(int row, int col, Occupant occ) {
        cells.get(row).get(col).setOccupant(occ);
    }
    public void set(int row, int col) {
        cells.get(row).get(col).setOccupant();
    }
    
    public HashSet<Cellule> getVoleurs() {
        return voleurs;
    }

    public HashSet<Cellule> getArgents() {
        return argents;
    }

    public HashSet<Cellule> getDrones() {
        return drones;
    }

    public HashSet<Cellule> getSorties() {
        return sorties;
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

}
