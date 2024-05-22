package dungeonCrawlerGame.gameManager.item;

import java.awt.image.BufferedImage;

public interface Item {
    public void pickUp(boolean pickedUp);
    public BufferedImage getImage();
    public int getPosX();
    public int getPosY();
    public int getLocationNumber();
    public ItemProperties getItemProperties();
    public void setLocationNumber(int locationNumber);
    public boolean isPickedUp();
}
