package DungeonCrawler.ui;

import java.awt.image.BufferedImage;
import java.io.IOException;

public enum EntityImage {
    PLAYER_UP1("/Player/PlayerUp_1.png"),
    PLAYER_UP2("/Player/PlayerUp_2.png"),
    PLAYER_DOWN1("/Player/PlayerDown_1.png"),
    PLAYER_DOWN2("/Player/PlayerDown_2.png"),
    PLAYER_LEFT1("/Player/PlayerLeft_1.png"),
    PLAYER_LEFT2("/Player/PlayerLeft_2.png"),
    PLAYER_RIGHT1("/Player/PlayerRight_1.png"),
    PLAYER_RIGHT2("/Player/PlayerRight_2.png");

    private final BufferedImage image;

    EntityImage(String path) {
        try {
            ImageReader imageReader = new ImageReader();
            this.image = imageReader.readImage(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public BufferedImage getImage() {
        return image;
    }
}

