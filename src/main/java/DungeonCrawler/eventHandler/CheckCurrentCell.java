package DungeonCrawler.eventHandler;

import DungeonCrawler.Config;
import DungeonCrawler.dungeon.Locations;
import DungeonCrawler.entities.Player;
import DungeonCrawler.gameManager.GameEngine;
import DungeonCrawler.ui.Cells;

public class CheckCurrentCell {

    static Locations location = new Locations();
    public static Cells currentCell;

    public static Cells getCurrentCell(Player player) {
        int playerColumn = (player.playerPosX + (Config.CELL_SIZE/2)) / Config.CELL_SIZE;
        int playerRow = (player.playerPosY + Config.CELL_SIZE/2) / Config.CELL_SIZE;

        Cells[][] currentLocation = location.returnLocation(GameEngine.currentLocationNum);

        currentCell = currentLocation[playerRow][playerColumn];
        return currentCell;
    }
}
