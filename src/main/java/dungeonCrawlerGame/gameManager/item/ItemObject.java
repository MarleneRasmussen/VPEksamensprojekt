package dungeonCrawlerGame.gameManager.item;

import dungeonCrawlerGame.Config;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ItemObject implements Item{
    private int posX;
    private int posY;
    private int locationNumber;
    private final ItemProperties itemProperties;
    private BufferedImage image;
    private boolean pickedUp;
    public Rectangle itemBounds;

    public ItemObject(int posX, int posY, int locationNumber, ItemProperties itemProperties)
    {
        this.posX = posX;
        this.posY = posY;
        this.locationNumber = locationNumber;
        this.itemProperties = itemProperties;
        pickedUp = false;
        setRect();
    }

    public void setRect(){
        if (this.itemProperties == ItemProperties.DOOR){
            itemBounds = new Rectangle(posX, posY, Config.CELL_SIZE * 2, Config.CELL_SIZE );
        }else{
            itemBounds = new Rectangle(posX, posY, Config.CELL_SIZE, Config.CELL_SIZE);
        }
    }

    @Override
    public void pickUp(boolean pickedUp){
        this.pickedUp = pickedUp;
    }

    @Override
    public BufferedImage getImage() {
        switch (itemProperties) {
            case COIN:
                image = ItemProperties.COIN.getImage();
                break;
            case KEY:
                image = ItemProperties.KEY.getImage();
                break;
            case POTION:
                image = ItemProperties.POTION.getImage();
                break;
            case DOOR:
                image = ItemProperties.DOOR.getImage();
                break;
        }
        return image;
    }

    @Override
    public int getPosX() {
        return posX;
    }

    @Override
    public int getPosY() {
        return posY;
    }

    @Override
    public int getLocationNumber() {
        return locationNumber;
    }

    @Override
    public ItemProperties getItemProperties() {
        return itemProperties;
    }

    @Override
    public void setLocationNumber(int locationNumber) {
        this.locationNumber = locationNumber;
    }

    @Override
    public boolean isPickedUp() {
        return pickedUp;
    }

}

