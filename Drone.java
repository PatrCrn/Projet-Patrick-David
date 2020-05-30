import fr.emse.simulator.world.Cell;
import fr.emse.simulator.world.Robot;

import java.util.ArrayList;

/**
 * Décrivez votre classe Drone ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class Drone extends Acteur implements Robot
{

    @Override
    public ArrayList<Cell> pathCible() {
        return path(cellule, worldMap.getVoleurs());
    }

    @Override
    public void deplacer() {
        ArrayList<Cell> bestChemin = pathCible();
        for(Cellule c: worldMap.getDrones()) {
            if (c.equals(cellule)) {
                worldMap.getDrones().remove(c);
                break;
            }
        }
        worldMap.set(cellule.getRow(), cellule.getCol());
        
        Cell cell = bestChemin.get(1);
        worldMap.set(cell.getRow(), cell.getCol(), this);
        
        cellule = worldMap.get(cell.getRow(), cell.getCol());
        
        verifVoisin(cellule.getRow(), cellule.getCol());
    }
    
    private void verifVoisin(int row, int col) {
        if ((Class)worldMap.get(row-1, col-1).getClass() == Voleur.class) {
            worldMap.set(row-1, col-1);
            
        } else if ((Class)worldMap.get(row-1, col).getClass() == Voleur.class) {
            worldMap.set(row-1, col);
            
        } else if ((Class)worldMap.get(row-1, col+1).getClass() == Voleur.class) {
            worldMap.set(row-1, col+1);
            
        } else if ((Class)worldMap.get(row, col-1).getClass() == Voleur.class) {
            worldMap.set(row, col-1);
            
        } else if ((Class)worldMap.get(row, col+1).getClass() == Voleur.class) {
            worldMap.set(row, col+1);
            
        } else if ((Class)worldMap.get(row+1, col-1).getClass() == Voleur.class) {
            worldMap.set(row+1, col-1);
            
        } else if ((Class)worldMap.get(row+1, col).getClass() == Voleur.class) {
            worldMap.set(row+1, col);
            
        } else if ((Class)worldMap.get(row+1, col+1).getClass() == Voleur.class) {
            worldMap.set(row+1, col+1);
        }
    }
}
