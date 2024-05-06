package DungeonCrawler.Recourses;

import DungeonCrawler.Config;
import DungeonCrawler.Dungeon.Locations;
import DungeonCrawler.Entities.Player;
import DungeonCrawler.GameManeger.GameLogic;
import DungeonCrawler.ui.Cells;

public class CollisionChecker {

    Locations location = new Locations();

    public void checkCollision(Player player){
        Cells [][] currentLocation = location.returnLocation(GameLogic.currentLocationNum);

        int playerLeft = player.playerPosX + Config.CELL_SIZE/3;
        int playerRight = player.playerPosX + (Config.CELL_SIZE/3)*2;
        int playerTop = player.playerPosY + Config.CELL_SIZE/3;
        int playerBottom = player.playerPosY + Config.CELL_SIZE;

        int playerRightColumn = playerRight / Config.CELL_SIZE;
        int playerLeftColumn = playerLeft / Config.CELL_SIZE;
        int playerTopRow = playerTop / Config.CELL_SIZE;
        int playerBottomRow = playerBottom / Config.CELL_SIZE;

        boolean cell1;
        boolean cell2;

        switch (player.direction) {
            case UP:
                playerTopRow = (playerTop - player.playerSpeed) / Config.CELL_SIZE;
                cell1 = currentLocation[playerTopRow][playerLeftColumn].isSolid();
                cell2 = currentLocation[playerTopRow][playerRightColumn].isSolid();

                if (cell1 || cell2)
                    player.collision = true;

                break;
            case DOWN:
                playerBottomRow = (playerBottom + player.playerSpeed) / Config.CELL_SIZE;
                cell1 = currentLocation[playerBottomRow][playerLeftColumn].isSolid();
                cell2 = currentLocation[playerBottomRow][playerRightColumn].isSolid();
                if (cell1 || cell2)
                    player.collision = true;

                break;
            case LEFT:
                playerLeftColumn = (playerLeft - player.playerSpeed) / Config.CELL_SIZE;
                cell1 = currentLocation[playerTopRow][playerLeftColumn].isSolid();
                cell2 = currentLocation[playerBottomRow][playerLeftColumn].isSolid();
                if (cell1 || cell2)
                    player.collision = true;

                break;
            case RIGHT:
                playerRightColumn = (playerRight + player.playerSpeed) / Config.CELL_SIZE;
                cell1 = currentLocation[playerTopRow][playerRightColumn].isSolid();
                cell2 = currentLocation[playerBottomRow][playerRightColumn].isSolid();
                if (cell1 || cell2)
                    player.collision = true;

                break;
        }
    }
}

