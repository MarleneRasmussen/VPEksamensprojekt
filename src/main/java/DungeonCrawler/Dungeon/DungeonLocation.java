package DungeonCrawler.Dungeon;

import DungeonCrawler.Entities.Player;
import DungeonCrawler.GameManeger.GamePanel;

public class DungeonLocation {
    Player player;
    GamePanel gp;
    public int locationWidth = 1728;
    public int locationHeight = 960;
    int worldColumn;
    int worldRow;

    public int locationNum;

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
        if (player.playerPosX > locationWidth) {
            player.playerPosX = 0;
            worldColumn++;
        }
        if (player.playerPosX < 0) {
            player.playerPosX = locationWidth;
            worldColumn--;
        }
        if (player.playerPosY > locationHeight) {
            player.playerPosY = 0;
            worldRow++;
        }
        if (player.playerPosY < 0) {
            player.playerPosY = locationHeight;
            worldRow--;
        }
        else{
            worldColumn = worldColumn;
            worldRow = worldRow;
        }
        return worldMap[worldRow][worldColumn];
    }
}
