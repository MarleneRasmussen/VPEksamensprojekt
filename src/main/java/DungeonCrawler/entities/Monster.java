package DungeonCrawler.entities;

import DungeonCrawler.Config;
import DungeonCrawler.gameManager.GameEngine;

import java.awt.*;

public class Monster {
    public int monsterPosX =100;
    public int monsterPosY=100;

    public static int mLocationNum = 2;

    public void drawMonster(Graphics2D g2) {
        if (mLocationNum == GameEngine.currentLocationNum) {

            g2.setColor(Color.RED);
            g2.fillRect(monsterPosX, monsterPosY, Config.CELL_SIZE, Config.CELL_SIZE);
        }
    }
}
