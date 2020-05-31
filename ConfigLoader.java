
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Cette classe représente un interpréteur
 * Afin de pouvoir simuler différentes cartes, il est utile de disposer d’un interpréteur de fichier texte
 * qui pour un fichier texte passé en paramètre renvoie un monde avec des entités positionnées initialement.
 *
 * Regarder la méthode static load pour plus d'info
 *
 * @author (David Abab, Patrick Corneo)
 * @version (V1)
 */
public class ConfigLoader {
    //    nb de ligne de la carte qu'on souhaite chargé
    private static int nbLigne;
    //    nb de collonne de la carte qu'on souhaite chargé
    private static  int nbColonne;

    /**
     *  Cette méthode permets de lire le fichier via un scanner pour retourner une liste de ces ligne
     *  Elle enregistre le numéro de ligne et de collonne dans les variables de la classes
     *
     * @param fileName le fichier à ouvrir
     * @return une liste de chaque ligne du fichier text sauf la première
     */
    private static ArrayList<String> read(File fileName) {
        ArrayList<String> lignesFichier = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(fileName);
            nbLigne = scanner.nextInt();
            nbColonne = scanner.nextInt();
            scanner.nextLine();
            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                lignesFichier.add(line.strip().replaceAll(" ",""));
            }
            return lignesFichier;
        } catch (IOException var4) {
            var4.printStackTrace();
            return lignesFichier;
        }
    }

    /**
     *  Cette méthode permets d'ajouter les cellules selon les bons caractères donnée par le fichier text
     * @param worldMap le monde où les occupants seront placées
     * @param lignes Les lignes qui sont lu par la méthode .read
     */
    private static void addCoordonneCell(WorldMap worldMap, ArrayList<String> lignes){
        try {
            verifTropDelignes(lignes);
            for (int row = 0 ; row < nbLigne ; row++){
                verifTropDecollonnes(lignes.get(row));
                for (int col = 0 ; col < nbColonne ; col++){
                    //System.out.println(row + " " + col + " " + lignes.get(row).charAt(col));
                    worldMap.addCell(row, col , lignes.get(row).charAt(col));
                }
            }
        } catch (IndexOutOfBoundsException error){
            System.err.println("Le nombre de lignes ou de collonnes n'est pas suffisante par rapport aux nombres voulus!");
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Cette méthode vérifie si il y a trop de collonnes par rapport aux nb définit à la première ligne
     * @param collones une ligne du fichier sauf la première
     * @throws Exception jette l'exception si y a trop de collonnes
     */
    private static void verifTropDecollonnes(String collones) throws Exception{
        if (collones.length()  > nbColonne) {
            throw new Exception("Le nombre de collonnes est trop élevé par rapport au nombre voulu!");
        }
    }

    /**
     * Cette méthode vérifie si il y a trop de lignes par rapport aux nb définit à la première ligne
     * @param lignes les lignes du fichier sauf la première
     * @throws Exception jette l'exception si y a trop de lignes
     */
    private static void verifTropDelignes(ArrayList<String> lignes) throws Exception{
        if (lignes.size() > nbLigne) {
            throw new Exception("Le nombre de lignes est trop élevé par rapport au nombre voulu !");
        }
    }

    /**
     * Cette méthode fixe les bonnes dimensions du monde avec qu'ils puissent traiter et afficher ses données au mieux
     * @param worldMap le monde où les occupants seront placées
     */
    private static void setDimension(WorldMap worldMap){
        worldMap.setNbCols(nbColonne);
        worldMap.setNbRows(nbLigne);
        worldMap.setSolver();
    }

    /**
     * Il s'agit de la méthode principale de cette class, la lancer chagera le monde définit en paramêtre
     *
     * @param worldMap le monde où les occupants seront placées
     * @param file     le fichier à ouvrir
     */
    public static void load(WorldMap worldMap, File file){
        addCoordonneCell(worldMap, ConfigLoader.read(file));
        setDimension(worldMap);
    }

}

