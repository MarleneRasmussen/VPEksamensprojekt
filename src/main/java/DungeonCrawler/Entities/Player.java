package DungeonCrawler.Entities;

import DungeonCrawler.Entity;
import DungeonCrawler.GameManeger.GamePanel;
import DungeonCrawler.GameManeger.KeyBoardAction;
import DungeonCrawler.Recourses.ImageReader;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player implements Entity
{
    GamePanel gp;
    KeyBoardAction key;
    ImageReader imgR = new ImageReader();

    public int playerPosX;
    public int playerPosY;
    public int playerSpeed;

    public BufferedImage upD1, upD2, leftD1, leftD2, rightD1, rightD2, downD1, downD2;
    public String direction;
    public boolean collision = false;

    public int imageCounter = 0;
    public int imageNum = 1;

    public Player(GamePanel gp){
        this.gp = gp;

        getEntityImage();
        setEntityLocation();
    }

    @Override
    public void moveEntity() {
        if (KeyBoardAction.down || KeyBoardAction.up || KeyBoardAction.left || KeyBoardAction.right) {
            if (KeyBoardAction.up) {
                direction = "up";
            }
            if (KeyBoardAction.down) {
                direction = "down";
            }
            if (KeyBoardAction.left) {
                direction = "left";
            }
            if (KeyBoardAction.right) {
                direction = "right";
            }

            collision = false;
            if (playerPosX >= 10 && playerPosX + gp.cellSize + 10 <= gp.locationWidth
                    && playerPosY + gp.cellSize + 10 <= gp.locationHeight && playerPosY >= 10){
                    gp.cc.checkCollision(this);
            }

            if (collision == false){
                switch (direction) {
                    case "up":
                        if(playerPosX >= 10 && playerPosX + gp.cellSize + 10 <= gp.locationWidth) {
                            playerPosY -= playerSpeed;
                        }
                        break;
                    case "down":
                        if(playerPosX >= 10 && playerPosX + gp.cellSize + 10 <= gp.locationWidth){
                            playerPosY += playerSpeed;
                        }
                        break;
                    case "left":
                        if(playerPosY + gp.cellSize + 10 <= gp.locationHeight && playerPosY >= 10) {
                            playerPosX -= playerSpeed;
                        }
                        break;
                    case "right":
                        if(playerPosY + gp.cellSize + 10 <= gp.locationHeight && playerPosY >= 10) {
                            playerPosX += playerSpeed;
                        }
                        break;
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
    }

    @Override
    public void setEntityLocation() {
        playerPosX = gp.locationWidth/2;
        playerPosY = gp.locationHeight/2;
        playerSpeed = 4;
        direction = "down";
    }

    @Override
    public void getEntityImage() {
        try {
            upD1 = imgR.readImage("/Player/PlayerUp_1.png");
            upD2 = imgR.readImage("/Player/PlayerUp_2.png");
            downD1 = imgR.readImage("/Player/PlayerDown_1.png");
            downD2 = imgR.readImage("/Player/PlayerDown_2.png");
            rightD1 = imgR.readImage("/Player/PlayerRight_1.png");
            rightD2 = imgR.readImage("/Player/PlayerRight_2.png");
            leftD1 = imgR.readImage("/Player/PlayerLeft_1.png");
            leftD2 = imgR.readImage("/Player/PlayerLeft_2.png");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void drawEntity(Graphics2D g2) {
        BufferedImage img = null;

        switch (direction) {
            case "up":
                if (imageNum == 1) {
                    img = upD1;
                }
                if (imageNum == 2) {
                    img = upD2;
                }
                break;
            case "down":
                if (imageNum == 1) {
                    img = downD1;
                }
                if (imageNum == 2) {
                    img = downD2;
                }
                break;
            case "left":
                if (imageNum == 1) {
                    img = leftD1;
                }
                if (imageNum == 2) {
                    img = leftD2;
                }
                break;
            case "right":
                if (imageNum == 1) {
                    img = rightD1;
                }
                if (imageNum == 2) {
                    img = rightD2;
                }
                break;
        }
        g2.drawImage(img, playerPosX, playerPosY, gp.cellSize, gp.cellSize, null);
    }
}
