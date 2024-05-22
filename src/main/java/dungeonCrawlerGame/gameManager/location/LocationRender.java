package dungeonCrawlerGame.gameManager.location;

import dungeonCrawlerGame.Config;
import dungeonCrawlerGame.gameView.scenes.GameScene;

import java.awt.*;

public class LocationRender {
    private Cells[][] currentLocation;
    Cells cell;
    DungeonMap dungeonMap;
    Locations locations;

    public LocationRender (DungeonMap dungeonMap, Locations locations){
        this.dungeonMap = dungeonMap;
        this.locations = locations;
    }

    public void getNewLocation() {
        currentLocation = GameScene.currentLocation;
    }

    public void drawCurrentLocation(Graphics g) {

        int col = currentLocation.length;
        int row = currentLocation[0].length;

        int j = 0;
        int i = 0;

        int x = 0;
        int y = 0;

        for (i = 0; i < col; i++) {
            for (j = 0; j < row; j++) {
                cell = currentLocation[i][j];
                g.drawImage(cell.getImage(), x, y, Config.CELL_SIZE, Config.CELL_SIZE, null);
                x += Config.CELL_SIZE;
            }
            y += Config.CELL_SIZE;
            x = 0;
        }
    }
}
