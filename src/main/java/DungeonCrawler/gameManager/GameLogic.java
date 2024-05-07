package DungeonCrawler.gameManager;

import static DungeonCrawler.gameManager.GameEngine.dungeonLocation;

public class GameLogic {
    public static void updateGame(){
        GameEngine.player.moveEntity();
        GameEngine.currentLocationNum = dungeonLocation.getCurrentWorldLocation();
        GameEngine.drawLocation.getNewLocation();
    }
}
