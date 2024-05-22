package dungeonCrawlerGame.gameManager.entity;

import dungeonCrawlerGame.controller.Direction;

import java.awt.*;

public interface Entity {
    public int getPosX();
    public int getPosY();
    public int getHealth();
    public int getLocationNumber();
    public Direction getDirection();
    public Image getImage();
    public void attacks();
    public void moveEntity();
    public void takeDamage(int i);
    public int getSpeed();
    public void setCollision(boolean collision);
    public void setLocationNumber(int locationNumber);
    public void setPlayerMonsterCollision(boolean playerCollision);
    public Rectangle getBounds();
}
