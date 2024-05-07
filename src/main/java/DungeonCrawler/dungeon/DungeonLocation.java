package DungeonCrawler.dungeon;

import DungeonCrawler.Config;
import DungeonCrawler.entities.Player;

public class DungeonLocation {
    Player player;
    int worldColumn;
    int worldRow;

    public int[][] worldMap = {
            {1, 2, 3, 4, 5},
            {6, 7, 8, 9, 10},
            {11, 12, 13, 14, 15},
            {16, 17, 18, 19, 20},
            {21, 22, 23, 24, 25}};
    public DungeonLocation(Player player){
        this.player = player;
        setDefaultLocation();
    }

    public void setDefaultLocation(){
        worldRow = 0;
        worldColumn = 0;
    }
    public int getCurrentWorldLocation() {
        if (player.playerPosX > Config.LOCATION_WIDTH){
            player.playerPosX = 0;
            worldColumn++;
        }
        if (player.playerPosX < 0) {
            player.playerPosX = Config.LOCATION_WIDTH;
            worldColumn--;
        }
        if (player.playerPosY > Config.LOCATION_HEIGHT) {
            player.playerPosY =0;
            worldRow++;
        }
        if (player.playerPosY < 0) {
            player.playerPosY = Config.LOCATION_HEIGHT;
            worldRow--;
        }
        return worldMap[worldRow][worldColumn];
    }
}
