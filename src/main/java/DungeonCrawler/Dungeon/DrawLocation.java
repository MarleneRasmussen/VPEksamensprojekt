package DungeonCrawler.Dungeon;

import DungeonCrawler.Config;
import DungeonCrawler.GameManeger.GameEngine;
import DungeonCrawler.GameManeger.GameLogic;
import DungeonCrawler.GameManeger.GamePanel;
import DungeonCrawler.ui.Cells;
import DungeonCrawler.ui.ImageReader;

import java.awt.*;


public class DrawLocation {
    Locations locations = new Locations();
    ImageReader reader = new ImageReader();
    Cells cell;

    public Cells [][] currentLocation;

    public void getNewLocation() {
        currentLocation = locations.returnLocation(GameEngine.currentLocationNum);
    }

    public void drawCurrentLocation(Graphics g2) {
        int col = currentLocation.length;
        int row = currentLocation[0].length;

        int j = 0;
        int i = 0;

        int x = 0;
        int y = 0;

        for (i = 0; i < col; i++) {
            for (j = 0; j < row; j++) {
                cell = currentLocation[i][j];
                g2.drawImage(cell.getImage(), x, y, Config.CELL_SIZE, Config.CELL_SIZE, null);
                x += Config.CELL_SIZE;
            }
            y += Config.CELL_SIZE;
            x = 0;
        }
    }
}