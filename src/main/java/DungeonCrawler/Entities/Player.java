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
                playerPosY -= playerSpeed;
            }
            if (KeyBoardAction.down) {
                direction = "down";
                playerPosY += playerSpeed;
            }
            if (KeyBoardAction.left) {
                direction = "left";
                playerPosX -= playerSpeed;
            }
            if (KeyBoardAction.right) {
                direction = "right";
                playerPosX += playerSpeed;
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

            System.out.println();
            System.out.print("X: " + playerPosX + " - Y: " + playerPosY );
            System.out.println();
        }
    }

    @Override
    public void setEntityLocation() {
        playerPosX = 100;
        playerPosY = 100;
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
