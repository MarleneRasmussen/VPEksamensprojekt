package DungeonCrawler.Dungeon;

import DungeonCrawler.GameManeger.GamePanel;
import DungeonCrawler.Recourses.Cell;
import DungeonCrawler.Recourses.ImageReader;

import java.awt.*;
import java.io.IOException;

public class DrawLocation {
    GamePanel gp;
    Locations locations = new Locations();
    ImageReader reader = new ImageReader();
    Cell[] cell = new Cell[14];

    public int [][] currentLocation;

    public DrawLocation(GamePanel gp) {
        this.gp = gp;
        getCellImage();
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
    public void getCellImage() {
        try {
            cell[0] = new Cell();
            cell[0].image = reader.readImage("/Cells/BlackSpace.png");

            cell[1] = new Cell();
            cell[1].image = reader.readImage("/Cells/Grass.png");

            cell[2] = new Cell();
            cell[2].image = reader.readImage("/Cells/Sand.png");

            cell[3] = new Cell();
            cell[3].image = reader.readImage("/Cells/Tile.png");

            cell[4] = new Cell();
            cell[4].image = reader.readImage("/Cells/Tile_SandBL.png");

            cell[5] = new Cell();
            cell[5].image = reader.readImage("/Cells/Tile_SandBottom.png");

            cell[6] = new Cell();
            cell[6].image = reader.readImage("/Cells/Tile_SandBR.png");

            cell[7] = new Cell();
            cell[7].image = reader.readImage("/Cells/Tile_SandLeft.png");

            cell[8] = new Cell();
            cell[8].image = reader.readImage("/Cells/Tile_SandRight.png");

            cell[9] = new Cell();
            cell[9].image = reader.readImage("/Cells/Tile_SandTL.png");

            cell[10] = new Cell();
            cell[10].image = reader.readImage("/Cells/Tile_SandTop.png");

            cell[11] = new Cell();
            cell[11].image = reader.readImage("/Cells/Tile_SandTR.png");

            cell[12] = new Cell();
            cell[12].image = reader.readImage("/Cells/Wall.png");

            cell[13] = new Cell();
            cell[13].image = reader.readImage("/Cells/Lava.png");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
