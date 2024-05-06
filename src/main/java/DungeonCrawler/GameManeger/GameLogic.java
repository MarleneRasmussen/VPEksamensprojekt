package DungeonCrawler.GameManeger;

import DungeonCrawler.Dungeon.DrawLocation;
import DungeonCrawler.Dungeon.DungeonLocation;
import DungeonCrawler.Entities.Player;

public class GameLogic {

    static Player player;
    public static int currentLocationNum;
    static DungeonLocation dl;
    static DrawLocation drawLocation;

    public static void updateGame(){
        player.moveEntity();
        currentLocationNum = dl.getCurrentWorldLocation();
        drawLocation.getNewLocation();
    }
}
