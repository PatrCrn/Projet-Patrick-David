import fr.emse.simulator.Simulator;
import fr.emse.simulator.gui.MapFrame;

import java.util.ArrayList;
import java.io.File;
import java.util.HashSet;
import java.util.Collections;
import java.util.List;

/**
 * Cette classe représente un Simulateur.
 *Le positionnement des diverses entités sera indiqué dans un fichier texte à interpréter
 * avant le lancement de la simulation. Une fois le fichier de description de la carte chargée,
 * la simulation est effectuée pas à pas : à chaque pas de simulation (ou tour) toutes les entités
 * qui peuvent se déplacer (drone ou intrus) se déplacent puis agissent (e.g. un drone se déplace puis attrape un intrus)
 * , les unes après les autres. Les autres entités (source d’argent et barrières) ne se déplacent pas.
 * L’ordre d’action est déterminé aléatoirement à chaque pas.
 *
 * Regarder la méthode static main  pour plus d'info
 *
 * @author (David Abab, Patrick Corneo)
 * @version (V1)
 */
public class Simulateur extends Simulator {
    // Le monde à simuler
    private WorldMap worldMap;

    /**
     * Constructeur
     * @param file fichier à ouvrir pour lancer la simulation
     */
    public Simulateur(File file) {
        super(file);
    }

    /**
     * Cette méthode permets de charger de la carte
     * @param file fichier à ouvrir pour lancer la simulation
     */
    @Override
    public void loadMap(File file) {
        worldMap = new WorldMap();
        ConfigLoader.load(worldMap, file);
    }

    /**
     * Permet de lancer une étape de la simulation donc une série de déplacement et d'intéractions
     */
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

    /**
     * Permets d'arreter la simulation quand il retourne true sinon la simulation continue
     * @return Regarde si il reste des voleurs (true) sinon il retourne un boolean false
     */
    @Override
    public boolean isOver() {
        return worldMap.getCellVoleurs().isEmpty();
    }

    /**
     * Permet de retourner la map de la simulation
     * @return le Worldmap de la simulation en cours
     */
    public WorldMap getWorldMap() {
        return worldMap;
    }

    /**
     * Permets de lancer la simulation
     */
    @Override
    public void run() {
        MapFrame frame = new MapFrame(worldMap);
        while (!isOver()){
            frame.repaint(300);
            runOneStep();
        }
        frame.repaint(200);
    }

    /**
     * Ils s'agit de la méthode qui lance tout le projet, commencer par la
     * @param args il s'agit du fichier que vous souhaitez ouvrir en string, exemple : "niveauTest.txt"
     */
    public static void main(String[] args) {
        File file = new File(args[0]); //args[0]
        Simulator simulator = new Simulateur(file);
        simulator.run();
        //System.exit(0);
    }
}
