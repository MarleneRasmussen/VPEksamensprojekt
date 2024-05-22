package dungeonCrawlerGame.gameManager.entity.player;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public enum PlayerImage {

    PLAYER_UP1("/Player/PlayerUP_1.png"),
    PLAYER_UP2("/Player/PlayerUp_2.png"),
    PLAYER_DOWN1("/Player/PlayerDown_1.png"),
    PLAYER_DOWN2("/Player/PlayerDown_2.png"),
    PLAYER_LEFT1("/Player/PlayerLeft_1.png"),
    PLAYER_LEFT2("/Player/PlayerLeft_2.png"),
    PLAYER_RIGHT1("/Player/PlayerRight_1.png"),
    PLAYER_RIGHT2("/Player/PlayerRight_2.png"),
    PLAYER_ATTACK("/Player/Attack.png");

    private final BufferedImage image;

    PlayerImage(String path) {
        try {
            this.image = ImageIO.read(getClass().getResourceAsStream(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public BufferedImage getImage() {
        return image;
    }
}

