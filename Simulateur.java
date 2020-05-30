import fr.emse.simulator.Simulator;
import fr.emse.simulator.gui.MapFrame;

import java.io.File;

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

    }

    @Override
    public boolean isOver() {
        return worldMap.getVoleurs().isEmpty();
    }

    public WorldMap getWorldMap() {
        return worldMap;
    }

    @Override
    public void run() {
        MapFrame frame = new MapFrame(worldMap);
        while (!isOver()){
            frame.repaint(200);
            runOneStep();
        }
    }

    public static void main(String[] args) {
        File file = new File("niveauTest.txt");
        Simulator simulator = new Simulateur(file);
        simulator.loadMap(file);
        simulator.run();
    }
}
