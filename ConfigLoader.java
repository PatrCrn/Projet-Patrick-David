import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
/*
* Cette classe sert à
* */
public class ConfigLoader {
    private static int nbLigne;
    private static  int nbColonne;

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

    private static void verifTropDecollonnes(String collones) throws Exception{
        if (collones.length()  > nbColonne) {
            throw new Exception("Le nombre de collonnes est trop élevé par rapport au nombre voulu!");
        }
    }

    private static void verifTropDelignes(ArrayList<String> lignes) throws Exception{
        if (lignes.size() > nbLigne) {
            throw new Exception("Le nombre de lignes est trop élevé par rapport au nombre voulu !");
        }
    }

    private static void setDimension(WorldMap worldMap){
        worldMap.setNbCols(nbColonne);
        worldMap.setNbRows(nbLigne);
    }

    public static void load(WorldMap worldMap, File file){
        addCoordonneCell(worldMap, ConfigLoader.read(file));
        setDimension(worldMap);
    }

//    public static void main(String[] args) {
//        load(new WorldMap(), new File("niveauTest.txt"));
//    }
}

