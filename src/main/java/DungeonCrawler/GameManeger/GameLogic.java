package DungeonCrawler.GameManeger;

import DungeonCrawler.Dungeon.DrawLocation;
import DungeonCrawler.Dungeon.DungeonLocation;
import DungeonCrawler.Entities.Player;

import static DungeonCrawler.GameManeger.GameEngine.dungeonLocation;

public class GameLogic {

    public static void updateGame(){
        GameEngine.player.moveEntity();
        GameEngine.currentLocationNum = dungeonLocation.getCurrentWorldLocation();
        GameEngine.drawLocation.getNewLocation();
    }
}
