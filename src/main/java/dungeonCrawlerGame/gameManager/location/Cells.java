package dungeonCrawlerGame.gameManager.location;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public enum Cells {
    BS("/Cells/BlackSpace.png", true, 0),
    GR("/Cells/Grass.png", false, 4),
    WA("/Cells/Water.png", true,0),
    W1("/Cells/Water_GrassB.png", true,0),
    W2("/Cells/Water_GrassT.png", true,0),
    LA("/Cells/Lava.png", true, 0),
    SA("/Cells/Sand.png", false, 3),
    T0("/Cells/Tile.png", false,5),
    T1("/Cells/BrokenTile.png", false,5),
    T2("/Cells/Tile_SandBottom.png", false,5),
    T3("/Cells/Tile_SandBottomLeft.png", false,5),
    T4("/Cells/Tile_SandBottomRight.png", false,5),
    T5("/Cells/Tile_SandLeft.png", false,5),
    T6("/Cells/Tile_SandRight.png", false,5),
    T7("/Cells/Tile_SandTop.png", false,5),
    T8("/Cells/Tile_SandTopLeft.png", false,5),
    T9("/Cells/Tile_SandTopRight.png", false,5),
    II("/Cells/Wall.png", true,0),
    I1("/Cells/Wall_Fire.png", true,0),
    B1("/Cells/Bridge_BottomRight.png", false,5),
    B2("/Cells/Bridge_TopRight.png", false,5),
    B3("/Cells/Bridge_BottomLeft.png", false,5),
    B4("/Cells/Bridge_TopLeft.png", false,5);


    private BufferedImage image;
    private boolean isSolid;
    private int speedImpact;

    Cells(String path, boolean isSolid, int speedImpact) {
        this.speedImpact = speedImpact;
        try{
            this.image = ImageIO.read(getClass().getResourceAsStream(path));
            this.isSolid = isSolid;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public BufferedImage getImage() {
        return image;
    }
    public boolean isSolid() {
        return isSolid;
    }
    public int getSpeedImpact() {
        return speedImpact;
    }
}

