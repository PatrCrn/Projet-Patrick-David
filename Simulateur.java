import fr.emse.simulator.Simulator;
import fr.emse.simulator.gui.MapFrame;

import java.util.ArrayList;
import java.io.File;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Collections;
import java.util.List;

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
        HashSet<Acteur> acteurs = new HashSet<>(worldMap.getVoleurs());
        acteurs.addAll(worldMap.getDrones());
        
        List<Acteur> temp = new ArrayList<Acteur>(acteurs);
        Collections.shuffle(temp);
        acteurs.clear();
        acteurs.addAll(temp);
        
        for(Acteur occ: acteurs) {
            if(occ.getClass() == Drone.class) {
                if (!worldMap.getCellVoleurs().isEmpty()) {
                    occ.deplacer();
                }
            } else if(occ.getCellule().getOccupant() != null) {
                occ.deplacer();
            }
        }
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
