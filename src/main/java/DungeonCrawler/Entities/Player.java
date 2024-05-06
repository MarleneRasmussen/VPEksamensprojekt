package DungeonCrawler.Entities;

import DungeonCrawler.Config;
import DungeonCrawler.GameManeger.GameEngine;
import DungeonCrawler.GameManeger.GamePanel;
import DungeonCrawler.controller.Direction;
import DungeonCrawler.controller.KeyBoardAction;
import DungeonCrawler.ui.ImageReader;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player {

    GameEngine gameEngine;
    GamePanel gp;
    ImageReader imgR = new ImageReader();

    public Direction direction;

    public int playerPosX;
    public int playerPosY;
    public int playerSpeed = 5;
    public int speedDecrease = 3;

    public BufferedImage upD1, upD2, leftD1, leftD2, rightD1, rightD2, downD1, downD2;
    public boolean collision = false;
    public boolean slow = false;

    public int imageCounter = 0;
    public int imageNum = 1;

    public Player(GamePanel gp){
        this.gp = gp;
        getEntityImage();
        setEntityLocation();
    }

    public void moveEntity() {
        if (KeyBoardAction.down || KeyBoardAction.up || KeyBoardAction.left || KeyBoardAction.right) {
            if (KeyBoardAction.up) {
                direction = Direction.UP;
            }
            if (KeyBoardAction.down) {
                direction = Direction.DOWN;
            }
            if (KeyBoardAction.left) {
                direction = Direction.LEFT;
            }
            if (KeyBoardAction.right) {
                direction = Direction.RIGHT;
            }
            slow = false;
            collision = false;

            if (playerPosX >= 10 && playerPosX + Config.CELL_SIZE + 10 <= Config.LOCATION_WIDTH
                    && playerPosY + Config.CELL_SIZE + 10 <= Config.LOCATION_HEIGHT && playerPosY >= 10) {
                GameEngine.collisionChecker.checkCollision(this);
            }

            setPlayerSpeed();
            if (!collision) {
                switch (direction) {
                    case UP:
                        if (playerPosX >= 10 && playerPosX + Config.CELL_SIZE + 10 <= Config.LOCATION_WIDTH) {
                            playerPosY -= playerSpeed;
                        }
                        break;
                    case DOWN:
                        if (playerPosX >= 10 && playerPosX + Config.CELL_SIZE + 10 <= Config.LOCATION_WIDTH) {
                            playerPosY += playerSpeed;
                        }
                        break;
                    case LEFT:
                        if (playerPosY + Config.CELL_SIZE + 10 <= Config.LOCATION_HEIGHT && playerPosY >= 10) {
                            playerPosX -= playerSpeed;
                        }
                        break;
                    case RIGHT:
                        if (playerPosY + Config.CELL_SIZE + 10 <= Config.LOCATION_HEIGHT && playerPosY >= 10) {
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

        public void setEntityLocation () {
            playerPosX = Config.LOCATION_WIDTH / 2;
            playerPosY = Config.LOCATION_HEIGHT / 2;
            direction = Direction.DOWN;
        }

        public void getEntityImage () {
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

        public void setPlayerSpeed() {
            if (slow) {
                playerSpeed = speedDecrease;
            } else {
                playerSpeed = 5;
            }
        }

        public void drawEntity (Graphics2D g2){
            BufferedImage img = null;

            switch (direction) {
                case UP:
                    if (imageNum == 1) {
                        img = upD1;
                    }
                    if (imageNum == 2) {
                        img = upD2;
                    }
                    break;
                case DOWN:
                    if (imageNum == 1) {
                        img = downD1;
                    }
                    if (imageNum == 2) {
                        img = downD2;
                    }
                    break;
                case LEFT:
                    if (imageNum == 1) {
                        img = leftD1;
                    }
                    if (imageNum == 2) {
                        img = leftD2;
                    }
                    break;
                case RIGHT:
                    if (imageNum == 1) {
                        img = rightD1;
                    }
                    if (imageNum == 2) {
                        img = rightD2;
                    }
                    break;
            }
            g2.drawImage(img, playerPosX, playerPosY, Config.CELL_SIZE, Config.CELL_SIZE, null);
        }
    }


