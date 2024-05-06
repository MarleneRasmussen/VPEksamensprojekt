package DungeonCrawler.Recourses;

import DungeonCrawler.Config;
import DungeonCrawler.Dungeon.Locations;
import DungeonCrawler.Entities.Player;
import DungeonCrawler.GameManeger.GamePanel;

public class CollisionChecker {
    GamePanel gp;
    Locations location = new Locations();

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkCollision(Player player){
        int [][] currentLocation = location.returnLocation(gp.currentLocationNum);

        int playerLeft = player.playerPosX + Config.CELL_SIZE/3;
        int playerRight = player.playerPosX + (Config.CELL_SIZE/3)*2;
        int playerTop = player.playerPosY + Config.CELL_SIZE/3;
        int playerBottom = player.playerPosY + Config.CELL_SIZE;

        int playerRightColumn = playerRight / Config.CELL_SIZE;
        int playerLeftColumn = playerLeft / Config.CELL_SIZE;
        int playerTopRow = playerTop / Config.CELL_SIZE;
        int playerBottomRow = playerBottom / Config.CELL_SIZE;

        int cellValue1;
        int cellValue2;

        switch (player.direction) {
            case "up":
                playerTopRow = (playerTop - player.playerSpeed) / Config.CELL_SIZE;
                cellValue1 = currentLocation[playerTopRow][playerLeftColumn];
                cellValue2 = currentLocation[playerTopRow][playerRightColumn];
                if (cellValue1 == 12 || cellValue2 == 12)
                    player.collision = true;
                else if (cellValue1 == 1 || cellValue2 == 1 || cellValue1 == 2 || cellValue2 == 2 ) {
                    player.slow = true;
                }
                else{
                    player.slow = false;
                }

                break;
            case "down":
                playerBottomRow = (playerBottom + player.playerSpeed) / Config.CELL_SIZE;
                cellValue1 = currentLocation[playerBottomRow][playerLeftColumn];
                cellValue2 = currentLocation[playerBottomRow][playerRightColumn];
                if (cellValue1 == 12 || cellValue2 == 12)
                    player.collision = true;
                else if (cellValue1 == 1 || cellValue2 == 1 || cellValue1 == 2 || cellValue2 == 2 ) {
                    player.slow = true;
                }
                else{
                    player.slow = false;
                }

                break;
            case "left":
                playerLeftColumn = (playerLeft - player.playerSpeed) / Config.CELL_SIZE;
                cellValue1 = currentLocation[playerTopRow][playerLeftColumn];
                cellValue2 = currentLocation[playerBottomRow][playerLeftColumn];
                if (cellValue1 == 12 || cellValue2 == 12)
                    player.collision = true;
                else if (cellValue1 == 1 || cellValue2 == 1 || cellValue1 == 2 || cellValue2 == 2 ) {
                    player.slow = true;
                }
                else{
                    player.slow = false;
                }

                break;
            case "right":
                playerRightColumn = (playerRight + player.playerSpeed) / Config.CELL_SIZE;
                cellValue1 = currentLocation[playerTopRow][playerRightColumn];
                cellValue2 = currentLocation[playerBottomRow][playerRightColumn];
                if (cellValue1 == 12 || cellValue2 == 12)
                    player.collision = true;
                else if (cellValue1 == 1 || cellValue2 == 1 || cellValue1 == 2 || cellValue2 == 2 ) {
                    player.slow = true;
                }
                else{
                    player.slow = false;
                }

                break;
        }
    }
}

