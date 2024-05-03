package DungeonCrawler.Dungeon;

import DungeonCrawler.GameManeger.GamePanel;
import DungeonCrawler.Recourses.Cells;
import DungeonCrawler.Recourses.ImageReader;

import java.awt.*;
import java.io.IOException;

public class DrawLocation {
    GamePanel gp;
    Locations locations = new Locations();
    ImageReader reader = new ImageReader();
    Cells[] cell = new Cells[3];

    public int [][] currentLocation;

    public DrawLocation(GamePanel gp) {
        this.gp = gp;
        getCellImage();
    }

    public void getCellImage() {
        try {
            cell[0] = new Cells();
            cell[0].image = reader.readImage("/Cells/Grass.png");

            cell[1] = new Cells();
            cell[1].image = reader.readImage("/Cells/Sand.png");

            cell[2] = new Cells();
            cell[2].image = reader.readImage("/Cells/Wall.png");
            cell[2].collision = true;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void getNewLocation() {
        currentLocation = locations.returnLocation(gp.currentLocationNum);
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
                int value = currentLocation[i][j];
                g2.drawImage(cell[value].image, x, y, gp.cellSize, gp.cellSize, null);
                x += gp.cellSize;
            }
            y += gp.cellSize;
            x = 0;
        }

    }
}
