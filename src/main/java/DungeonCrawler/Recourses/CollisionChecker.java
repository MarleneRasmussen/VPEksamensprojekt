package DungeonCrawler.Recourses;

import DungeonCrawler.Config;
import DungeonCrawler.Dungeon.Locations;
import DungeonCrawler.Entities.Player;
import DungeonCrawler.GameManeger.GamePanel;
import DungeonCrawler.ui.Cells;

public class CollisionChecker {
    GamePanel gp;
    Locations location = new Locations();
    Cells[][] currentLocation;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkCollision(Player player){
        currentLocation = location.returnLocation(gp.currentLocationNum);

        int playerLeft = player.playerPosX + Config.CELL_SIZE/3;
        int playerRight = player.playerPosX + (Config.CELL_SIZE/3)*2;
        int playerTop = player.playerPosY + Config.CELL_SIZE/3;
        int playerBottom = player.playerPosY + Config.CELL_SIZE;

        int playerRightColumn = playerRight / Config.CELL_SIZE;
        int playerLeftColumn = playerLeft / Config.CELL_SIZE;
        int playerTopRow = playerTop / Config.CELL_SIZE;
        int playerBottomRow = playerBottom / Config.CELL_SIZE;

        boolean cellValue1;
        boolean cellValue2;

        switch (player.direction) {
            case UP:
                playerTopRow = (playerTop - player.playerSpeed) / Config.CELL_SIZE;
                cellValue1 = currentLocation[playerTopRow][playerLeftColumn].isSolid();
                cellValue2 = currentLocation[playerTopRow][playerRightColumn].isSolid();
                if (cellValue1 || cellValue2)
                    player.collision = true;
                /*else if (cellValue1 == 1 || cellValue2 == 1 || cellValue1 == 2 || cellValue2 == 2 ) {
                    player.slow = true;
                }
                else{
                    player.slow = false;
                }*/

                break;
            case DOWN:
                playerBottomRow = (playerBottom + player.playerSpeed) / Config.CELL_SIZE;
                cellValue1 = currentLocation[playerBottomRow][playerLeftColumn].isSolid();
                cellValue2 = currentLocation[playerBottomRow][playerRightColumn].isSolid();
                if (cellValue1 || cellValue2)
                    player.collision = true;
                /*else if (cellValue1 == 1 || cellValue2 == 1 || cellValue1 == 2 || cellValue2 == 2 ) {
                    player.slow = true;
                }
                else{
                    player.slow = false;
                }*/

                break;
            case LEFT:
                playerLeftColumn = (playerLeft - player.playerSpeed) / Config.CELL_SIZE;
                cellValue1 = currentLocation[playerTopRow][playerLeftColumn].isSolid();
                cellValue2 = currentLocation[playerBottomRow][playerLeftColumn].isSolid();
                if (cellValue1 || cellValue2)
                    player.collision = true;
                /*else if (cellValue1 == 1 || cellValue2 == 1 || cellValue1 == 2 || cellValue2 == 2 ) {
                    player.slow = true;
                }
                else{
                    player.slow = false;
                }*/

                break;
            case RIGHT:
                playerRightColumn = (playerRight + player.playerSpeed) / Config.CELL_SIZE;
                cellValue1 = currentLocation[playerTopRow][playerRightColumn].isSolid();
                cellValue2 = currentLocation[playerBottomRow][playerRightColumn].isSolid();
                if (cellValue1 || cellValue2)
                    player.collision = true;
                /*else if (cellValue1 == 1 || cellValue2 == 1 || cellValue1 == 2 || cellValue2 == 2 ) {
                    player.slow = true;
                }
                else{
                    player.slow = false;
                }*/

                break;
        }
    }
}

