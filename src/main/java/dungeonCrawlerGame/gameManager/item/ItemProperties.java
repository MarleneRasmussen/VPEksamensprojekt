package dungeonCrawlerGame.gameManager.item;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public enum ItemProperties {
    COIN(false, "/Items/Coin.png"),
    KEY(false, "/Items/Key.png"),
    POTION(false, "/Items/Position.png"),
    DOOR(true, "/Items/Door.png");

    private String path;
    private boolean isSolid;
    private BufferedImage image;


    ItemProperties(boolean isSolid, String path) {
        this.isSolid = isSolid;
        try{
            this.image = ImageIO.read(getClass().getResourceAsStream(path));
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
