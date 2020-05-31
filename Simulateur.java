import fr.emse.simulator.Simulator;
import fr.emse.simulator.gui.MapFrame;

import java.util.ArrayList;
import java.io.File;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Simulateur extends Simulator {
    private WorldMap worldMap;

    public Simulateur(File file) {
        super(file);
    }

    @Override
    public void loadMap(File file) {
        worldMap = new WorldMap();
        ConfigLoader.load(worldMap, file);
    }

    @Override
    public void runOneStep() {
        for (Voleur voleur : worldMap.getVoleurs()) {
            voleur.deplacer();
        }
        if (!worldMap.getCellVoleurs().isEmpty()) {
            for (Drone drone : worldMap.getDrones()) {
                drone.deplacer();
            }
        }

//        ArrayList<ArrayList<Cellule>> arrCells = worldMap.getCells();
//        for(int x = 0; x < arrCells.size(); x++) {
//            ArrayList<Cellule> arrCell = arrCells.get(x);
//
//            for(int i = 0; i < arrCells.get(x).size(); i++) {
//                try{
//                    Class cla = arrCell.get(i).getOccupant().getClass();
//                    if(cla == Drone.class || cla == Voleur.class) {
//                        Acteur occ = (Acteur)worldMap.getCells().get(x).get(i).getOccupant();
//                        occ.deplacer();
//                    }
//                } catch (NullPointerException e) {
//                    continue;
//                }
//            }
//        }
    }

    @Override
    public boolean isOver() {
        return worldMap.getCellVoleurs().isEmpty();
    }

    public WorldMap getWorldMap() {
        return worldMap;
    }

    @Override
    public void run() {
        MapFrame frame = new MapFrame(worldMap);
        while (!isOver()){
            frame.repaint(300);
            runOneStep();
        }
        frame.repaint(200);
    }

    public static void main(String[] args) {
        File file = new File("niveauComplexe.txt");
        Simulator simulator = new Simulateur(file);
        simulator.loadMap(file);
        simulator.run();
        //System.exit(0);
    }
}
