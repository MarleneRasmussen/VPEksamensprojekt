package DungeonCrawler.ui;

import java.awt.image.BufferedImage;
import java.io.IOException;

public enum Cells {
    BS("/Cells/BlackSpace.png", true, 0),
    GR("/Cells/Grass.png", false, 3),
    LA("/Cells/Lava.png", true, 0),
    SA("/Cells/Sand.png", false, 2),
    TI("/Cells/Tile.png", false,5),
    BL("/Cells/Tile_SandBL.png", false,5),
    BR("/Cells/Tile_SandBR.png", false,5),
    TL ("/Cells/Tile_SandTL.png", false,5),
    TR ("/Cells/Tile_SandTR.png", false,5),
    ST ("/Cells/Tile_SandTop.png", false,5),
    SB ("/Cells/Tile_SandBottom.png", false,5),
    SL ("/Cells/Tile_SandLeft.png", false,5),
    SR ("/Cells/Tile_SandRight.png", false,5),
    WA("/Cells/Wall.png", true,0);

    private BufferedImage image;
    private boolean isSolid;
    private int speedImpact;

    Cells(String path, boolean isSolid, int speedImpact) {
        this.speedImpact = speedImpact;
        try{
            ImageReader imageReader = new ImageReader();
            this.image = imageReader.readImage(path);
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
}
