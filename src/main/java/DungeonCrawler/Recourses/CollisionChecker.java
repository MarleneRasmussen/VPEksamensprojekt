package DungeonCrawler.Recourses;

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

        int playerLeft = player.playerPosX;
        int playerRight = player.playerPosX + gp.cellSize;
        int playerTop = player.playerPosY;
        int playerBottom = player.playerPosY + gp.cellSize;

        int playerRightColumn = playerRight / gp.cellSize;
        int playerLeftColumn = playerLeft / gp.cellSize;
        int playerTopRow = playerTop / gp.cellSize;
        int playerBottomRow = playerBottom / gp.cellSize;

        int cellNum1;
        int cellNum2;

            switch (player.direction) {
                case "up":
                    playerTopRow = (playerTop - player.playerSpeed) / gp.cellSize;
                    cellNum1 = currentLocation[playerTopRow][playerLeftColumn];
                    cellNum2 = currentLocation[playerTopRow][playerRightColumn];
                    if (cellNum1 == 12 || cellNum2 == 12)
                        player.collision = true;

                    break;
                case "down":
                    playerBottomRow = (playerBottom + player.playerSpeed) / gp.cellSize;
                    cellNum1 = currentLocation[playerBottomRow][playerLeftColumn];
                    cellNum2 = currentLocation[playerBottomRow][playerRightColumn];
                    if (cellNum1 == 12 || cellNum2 == 12)
                        player.collision = true;

                    break;
                case "left":
                    playerLeftColumn = (playerLeft - player.playerSpeed) / gp.cellSize;
                    cellNum1 = currentLocation[playerTopRow][playerLeftColumn];
                    cellNum2 = currentLocation[playerBottomRow][playerLeftColumn];
                    if (cellNum1 == 12 || cellNum2 == 12)
                        player.collision = true;

                    break;
                case "right":
                    playerRightColumn = (playerRight + player.playerSpeed) / gp.cellSize;
                    cellNum1 = currentLocation[playerTopRow][playerRightColumn];
                    cellNum2 = currentLocation[playerBottomRow][playerRightColumn];
                    if (cellNum1 == 12 || cellNum2 == 12)
                        player.collision = true;

                    break;
            }
    }
}

