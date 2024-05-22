package dungeonCrawlerGame.gameManager.entity.enemy;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public enum BatImages {
    BAT_UP1("/Monsters/Bat_Up1.png"),
    BAT_UP2("/Monsters/Bat_Up2.png"),
    BAT_DOWN1("/Monsters/Bat_Down1.png"),
    BAT_DOWN2("/Monsters/Bat_Down2.png"),
    BAT_RIGHT1("/Monsters/Bat_Right1.png"),
    BAT_RIGHT2("/Monsters/Bat_Right2.png"),
    BAT_LEFT1("/Monsters/Bat_Left1.png"),
    BAT_LEFT2("/Monsters/Bat_Left2.png");


    private final String path;
    private BufferedImage image;

    BatImages(String path) {
        this.path = path;
        try{
            this.image = ImageIO.read(getClass().getResourceAsStream(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public BufferedImage getImage() {
        return image;
    }

}
