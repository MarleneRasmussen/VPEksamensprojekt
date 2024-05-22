package dungeonCrawlerGame.gameManager.entity.enemy;

import dungeonCrawlerGame.Config;
import dungeonCrawlerGame.controller.Direction;
import dungeonCrawlerGame.gameManager.entity.Entity;

import java.awt.*;
import java.util.Random;

import static dungeonCrawlerGame.gameView.scenes.GameScene.player;

public class Monster implements Entity {


    private int posX;
    private int posY;
    private int health;
    private int locationNumber;
    public Direction direction;
    private Image image;
    private int damage;
    private boolean collision;
    public boolean playerCollision;
    private final int maxHealth;
    public Rectangle entityBounds = new Rectangle(posX, posY, Config.CELL_SIZE, Config.CELL_SIZE);

    private int monsterSpeed;
    private int turnCounter = 0;
    private int imageCounter = 0;
    private int imageNum = 1;
    private int damageCounter = 0;

    public Monster(int startX, int startY, int location, BatImages image, int health, int damage) {
        this.posX = startX;
        this.posY = startY;
        this.locationNumber = location;
        this.maxHealth = health;
        this.health = health;
        this.damage = damage;
        direction = Direction.DOWN;
        monsterSpeed = 3;
    }

    public int getMaxHealth(){
        return maxHealth;
    }

    @Override
    public void moveEntity() {
        //Can not move out of the location
        if (posX >= Config.CELL_SIZE && posY >= Config.CELL_SIZE && posX + Config.CELL_SIZE <= Config.LOCATION_WIDTH - Config.CELL_SIZE &&
                posY + Config.CELL_SIZE <= Config.LOCATION_HEIGHT - Config.CELL_SIZE) {
            turnCounter ++;
            if (turnCounter == 20) {
                Random random = new Random();
                int i = random.nextInt(10);
                switch (i) {
                    case 0:
                        direction = Direction.RIGHT;
                        break;
                    case 1:
                        direction = Direction.LEFT;
                        break;
                    case 2:
                        direction = Direction.UP;
                        break;
                    case 3:
                        direction = Direction.DOWN;
                        break;
                }
                turnCounter = 0;
            }
            if(!collision && !playerCollision) {
                switch (direction) {

                    case RIGHT:
                        posX += monsterSpeed;
                        break;
                    case LEFT:
                        posX -= monsterSpeed;
                        break;
                    case UP:
                        posY -= monsterSpeed;
                        break;
                    case DOWN:
                        posY += monsterSpeed;
                        break;
                }
            }
        }
        else {
            if (posX <= Config.CELL_SIZE) {
                direction = Direction.RIGHT;
                posX += monsterSpeed;
            } else if (posX + Config.CELL_SIZE >= Config.LOCATION_WIDTH - Config.CELL_SIZE) {
                direction = Direction.LEFT;
                posX -= monsterSpeed;
            } else if (posY <= Config.CELL_SIZE) {
                direction = Direction.DOWN;
                posY += monsterSpeed;
            } else {
                direction = Direction.UP;
                posY -= monsterSpeed;
            }
        }

        imageCounter++;
        if (imageCounter > 12) {
            if (imageNum == 1) {
                imageNum = 2;
            } else if (imageNum == 2) {
                imageNum = 1;
            }
            imageCounter = 0;
        }
    }

    @Override
    public Rectangle getBounds() {
        return entityBounds;
    }

    @Override
    public int getPosX() {
        return this.posX;
    }

    @Override
    public int getPosY() {
        return this.posY;
    }

    @Override
    public int getHealth() {
        return this.health;
    }

    @Override
    public void takeDamage(int i) {
        health = health - i;
    }

    @Override
    public int getSpeed() {
        return this.monsterSpeed;
    }

    @Override
    public void setCollision(boolean collision) {
        this.collision = collision;
    }

    @Override
    public void setLocationNumber(int locationNumber) {
        this.locationNumber = locationNumber;
    }

    @Override
    public void setPlayerMonsterCollision(boolean playerCollision) {
        this.playerCollision = playerCollision;
    }

    @Override
    public int getLocationNumber() {
        return this.locationNumber;
    }

    @Override
    public Direction getDirection() {
        return this.direction;
    }

    @Override
    public Image getImage() {
        switch (direction) {
            case UP:
                if (imageNum == 1) {
                    image = BatImages.BAT_UP1.getImage();
                } else if (imageNum == 2) {
                    image = BatImages.BAT_UP2.getImage();
                }
                break;
            case DOWN:
                if (imageNum == 1) {
                    image = BatImages.BAT_DOWN1.getImage();
                } else if (imageNum == 2) {
                    image = BatImages.BAT_DOWN2.getImage();
                }
                break;
            case LEFT:
                if (imageNum == 1) {
                    image = BatImages.BAT_LEFT1.getImage();
                } else if (imageNum == 2) {
                    image = BatImages.BAT_LEFT2.getImage();
                }
                break;
            case RIGHT:
                if (imageNum == 1) {
                    image = BatImages.BAT_RIGHT1.getImage();
                } else if (imageNum == 2) {
                    image = BatImages.BAT_RIGHT2.getImage();
                }
                break;
        }
        return image;
    }

    @Override
    public void attacks() {
        damageCounter++;
        if (damageCounter == 20) {
            damageCounter = 0;
            player.takeDamage(this.damage);
        }
    }
}
