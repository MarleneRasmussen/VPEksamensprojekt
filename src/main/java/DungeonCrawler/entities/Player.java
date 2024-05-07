package DungeonCrawler.entities;

import DungeonCrawler.Config;
import DungeonCrawler.eventHandler.CheckCurrentCell;
import DungeonCrawler.gameManager.GameEngine;
import DungeonCrawler.gameDisplay.GamePanel;
import DungeonCrawler.controller.Direction;
import DungeonCrawler.controller.KeyBoardAction;
import DungeonCrawler.ui.Cells;
import DungeonCrawler.ui.EntityImage;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player {

    GamePanel gp;

    public Direction direction;

    public int playerPosX;
    public int playerPosY;
    public int playerSpeed;

    public boolean collision = false;
    Cells currentCell;

    public int imageCounter = 0;
    public int imageNum = 1;

    public Player(GamePanel gp){
        this.gp = gp;
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

            playerSpeed = Config.DEFAULT_PLAYER_SPEED;
            collision = false;
            if (playerPosX >= 10 && playerPosX + Config.CELL_SIZE + 10 <= Config.LOCATION_WIDTH
                    && playerPosY + Config.CELL_SIZE + 10 <= Config.LOCATION_HEIGHT && playerPosY >= 10) {
                GameEngine.collisionChecker.checkCollision(this);
                currentCell= CheckCurrentCell.getCurrentCell(this);
                setPlayerSpeed();
            }

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

        public void setPlayerSpeed() {
            playerSpeed = currentCell.getSpeedImpact();
        }

        public void drawEntity (Graphics2D g2){
            BufferedImage img = null;

            switch (direction) {
                case UP:
                    if (imageNum == 1) {
                        img = EntityImage.PLAYER_UP1.getImage();
                    }
                    if (imageNum == 2) {
                        img = EntityImage.PLAYER_UP2.getImage();
                    }
                    break;
                case DOWN:
                    if (imageNum == 1) {
                        img = EntityImage.PLAYER_DOWN1.getImage();
                    }
                    if (imageNum == 2) {
                        img = EntityImage.PLAYER_DOWN2.getImage();
                    }
                    break;
                case LEFT:
                    if (imageNum == 1) {
                        img = EntityImage.PLAYER_LEFT1.getImage();
                    }
                    if (imageNum == 2) {
                        img = EntityImage.PLAYER_LEFT2.getImage();
                    }
                    break;
                case RIGHT:
                    if (imageNum == 1) {
                        img = EntityImage.PLAYER_RIGHT1.getImage();
                    }
                    if (imageNum == 2) {
                        img = EntityImage.PLAYER_RIGHT2.getImage();
                    }
                    break;
            }
            g2.drawImage(img, playerPosX, playerPosY, Config.CELL_SIZE, Config.CELL_SIZE, null);
        }
    }


