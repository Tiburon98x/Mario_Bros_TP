
package tp1.logic;

import java.util.List;
import tp1.logic.gameobjects.GameObject;
import tp1.logic.gameobjects.Mario;

public interface GameConfiguration {
    public int getRemainingTime();
    public int getPoints();
    public int getLives();
    public Mario getMario();
    public List<GameObject> getObjects();
}