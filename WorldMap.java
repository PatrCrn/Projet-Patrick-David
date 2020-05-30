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
    private ArrayList<Cellule> voleurs = new ArrayList<>();
    private ArrayList<Cellule> argents = new ArrayList<>();
    private ArrayList<Cellule> drones = new ArrayList<>();
    private ArrayList<Cellule> sorties = new ArrayList<>();
    private int nbRows;
    private int nbCols;
    
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
            cells.get(row).add(cellule);
        } else if (c == 'I') {
            Voleur vol = new Voleur();
            cellule = new Cellule (row, col, vol);
            cells.get(row).add(cellule);
        } else if (c == '$') {
            Argent money = new Argent();
            cellule = new Cellule (row, col, money);
            cells.get(row).add(cellule);
        } else if (c == '_') {
            cellule = new Cellule (row, col);
            cells.get(row).add(cellule);
        }
    }
    
    public Cell get(int row, int col) {
        return cells.get(row).get(col);
    }
    
    public ArrayList getVoleurs() {
        return voleurs;
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
}
