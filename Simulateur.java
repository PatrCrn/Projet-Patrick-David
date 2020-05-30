import fr.emse.simulator.Simulator;
import fr.emse.simulator.gui.MapFrame;

import java.io.File;
/**
 * Décrivez votre classe Simulateur ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class Simulateur extends Simulator
{
    private static final int SLEEP = 300;
    private WorldMap monde;
    private int pas;

    /**
     * Constructeur d'objets de classe Simulateur
     */
    public Simulateur(File fichier) {
        super(fichier);
        pas = 0;
    }

    /**
     * Un exemple de méthode - remplacez ce commentaire par le vôtre
     *
     * @param  y   le paramètre de la méthode
     * @return     la somme de x et de y
     */
    public boolean isOver()
    {
        return monde.getVoleurs().isEmpty();
    }
    
    public void runOneStep()
    {
        
    }
    
    public void loadMap(File fichier) {
        monde = new WorldMap();
        ConfigLoader.load(monde, fichier);
    }
    
    public void run() {
        MapFrame frame = new MapFrame(monde);
        frame.repaint(100);
        while (!isOver()) {
            runOneStep();
            frame.repaint(100);
        }
        System.out.println("Le jeu est terminé!");
    }
    
    public static void main(String[] args){
        File f = new File(args[0]);        
        Simulateur sim = new Simulateur(f);
        sim.run();        
        System.exit(0);
    }
}
