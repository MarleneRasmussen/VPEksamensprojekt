package DungeonCrawler.entities;

import DungeonCrawler.Config;
import DungeonCrawler.gameManager.GameEngine;

public class Monster {
    public int monsterPosX;
    public int monsterPosY;
    public int mLocationNum;
    public int monsterSpeed;

    public Monster(int monsterPosX, int monsterPosY, int monsterSpeed, int mLocationNum) {
        this.monsterPosX = monsterPosX;
        this.monsterPosY = monsterPosY;
        this.monsterSpeed = monsterSpeed;
        this.mLocationNum = mLocationNum;
    }

    public int getPosX() {
        return monsterPosX;
    }

    public int getPosY() {
        return monsterPosY;
    }

    public int getLocationNum() {
        return mLocationNum;
    }

    public int getMonsterSpeed() {
        return monsterSpeed;
    }


    public void update() {
        if (mLocationNum == GameEngine.currentLocationNum) {
            int random = (int) (Math.random() * 4);
            switch (random) {
                case 0:
                    if (monsterPosX + Config.CELL_SIZE < Config.LOCATION_WIDTH - Config.CELL_SIZE) {
                        monsterPosX += monsterSpeed;
                    }
                    break;
                case 1:
                    if (monsterPosX - Config.CELL_SIZE > 0) {
                        monsterPosX -= monsterSpeed;
                    }
                    break;
                case 2:
                    if (monsterPosY + Config.CELL_SIZE < Config.LOCATION_HEIGHT - Config.CELL_SIZE) {
                        monsterPosY += monsterSpeed;
                    }
                    break;
                case 3:
                    if (monsterPosY - Config.CELL_SIZE > 0) {
                        monsterPosY -= monsterSpeed;
                    }
                    break;
            }
        }
    }
}

