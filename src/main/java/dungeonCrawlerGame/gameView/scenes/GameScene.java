package dungeonCrawlerGame.gameView.scenes;

import dungeonCrawlerGame.Config;
import dungeonCrawlerGame.controller.CollisionChecker;
import dungeonCrawlerGame.controller.GameState;
import dungeonCrawlerGame.controller.KeyAction;

import dungeonCrawlerGame.gameManager.entity.enemy.BatImages;
import dungeonCrawlerGame.gameManager.entity.enemy.Monster;
import dungeonCrawlerGame.gameManager.entity.enemy.Monsters;
import dungeonCrawlerGame.gameManager.entity.player.Player;
import dungeonCrawlerGame.gameManager.entity.player.PlayerState;

import dungeonCrawlerGame.gameManager.item.ItemObject;
import dungeonCrawlerGame.gameManager.item.ItemProperties;
import dungeonCrawlerGame.gameManager.item.Items;
import dungeonCrawlerGame.gameManager.location.Cells;
import dungeonCrawlerGame.gameManager.location.DungeonMap;
import dungeonCrawlerGame.gameManager.location.LocationRender;
import dungeonCrawlerGame.gameManager.location.Locations;
import dungeonCrawlerGame.gameView.MyGame;
import dungeonCrawlerGame.gameView.Scene;
import dungeonCrawlerGame.gameView.SceneManager;

import javax.swing.*;
import java.awt.*;

public class GameScene extends JPanel implements Scene {

    SceneManager sceneManager;
    KeyAction keyAction;
    MyGame myGame;
    public static Player player;
    Monsters monsters;
    Items items = new Items();
    PlayerState playerState;
    CollisionChecker collisionChecker;
    DungeonMap dungeonMap = new DungeonMap();
    Locations locations;
    LocationRender locationRender;
    public static int currentLocationNum;
    public static Cells[][] currentLocation;

    public GameScene(SceneManager sceneManager, MyGame myGame, KeyAction keyAction) {
        this.sceneManager = sceneManager;
        this.myGame = myGame;
        this.keyAction = keyAction;
        currentLocationNum = dungeonMap.getCurrentWorldLocation();
        player = new Player();
        monsters = new Monsters();
        playerState = new PlayerState();
        collisionChecker = new CollisionChecker(player);
        dungeonMap = new DungeonMap();
        locations = new Locations();
        locationRender = new LocationRender(dungeonMap, locations);
        InitGame();
    }

    @Override
    public void update() {
        if (SceneManager.gameState == GameState.RUNNING) {

            currentLocationNum = dungeonMap.getCurrentWorldLocation();
            currentLocation = locations.returnLocation(currentLocationNum);
            locationRender.getNewLocation();

            player.moveEntity();
            dungeonMap.updateCurrentWorldLocation(player);

            monsters.checkDead();
            monsters.checkForAttack();
            monsters.checkCollision();
            monsters.updateMonsters();
            monsters.checkForDamage();

            player.increaseStamina();
            monsters.checkDead();
            items.checkIfPickedUp();
            playerState.useFromInventory();

            if (player.getHealth() <= 0) {
                SceneManager.gameState = GameState.GAME_OVER;
            }
        }
        else if (SceneManager.gameState == GameState.GAME_OVER){
            sceneManager.setScene(myGame.start);
        }
    }


    @Override
    public void render(Graphics g) {
            //Draw current location
            locationRender.drawCurrentLocation(g);

            //Draw player
            g.drawImage(player.getImage(), player.getPosX(), player.getPosY(), Config.CELL_SIZE, Config.CELL_SIZE, null);
            player.renderAttack(g);

            //Draw items in current location
            items.drawItems(g);

            //Draw monsters in current location
            monsters.drawMonsters(g);

            //Draw player health bar
            playerState.renderPlayerHP(g);

            //Draw player attack bar
            playerState.renderPlayerStamina(g);
            playerState.renderInventoryValue(g);
            playerState.renderInventoryImages(g);

            if (SceneManager.gameState == GameState.PAUSED) {
                g.setColor(new Color(0, 0, 0, 150));
                g.fillRoundRect(150, 150, 1428, 660, 10, 10);

                g.setColor(Color.WHITE);
                g.setFont(new Font("Arial", Font.BOLD, 100));
                g.drawString("PAUSED", 680, 320);

                g.setColor(Color.WHITE);
                g.setFont(new Font("Arial", Font.BOLD, 70));
                g.drawString("Press Enter to continue the game", 320, 550);

                g.setColor(Color.WHITE);
                g.setFont(new Font("Arial", Font.BOLD, 50));
                g.drawString("Press ESC to quit game", 600, 700);
            }
            else if ( SceneManager.gameState == GameState.GAME_OVER){
                g.setColor(new Color(0, 0, 0, 150));
                g.fillRoundRect(150, 150, 1428, 660, 10, 10);

                g.setColor(Color.WHITE);
                g.setFont(new Font("Arial", Font.BOLD, 100));
                g.drawString("GAME OVER...", 550, 500);
        }
    }

    private void InitGame() {

        dungeonMap.setDefaultLocation();

        //Location 1
        items.addItem(new ItemObject(2 * Config.CELL_SIZE, 2 * Config.CELL_SIZE, 1, ItemProperties.KEY));
        items.addItem(new ItemObject(14 * Config.CELL_SIZE, 9 * Config.CELL_SIZE, 1, ItemProperties.DOOR));
        monsters.addMonster(new Monster(9 * Config.CELL_SIZE, 7 * Config.CELL_SIZE, 1, BatImages.BAT_UP1, 500, 30));

        //Location 2
        items.addItem(new ItemObject(7 * Config.CELL_SIZE, 5 * Config.CELL_SIZE, 2, ItemProperties.DOOR));
        items.addItem(new ItemObject(6 * Config.CELL_SIZE, Config.CELL_SIZE, 2, ItemProperties.POTION));
        items.addItem(new ItemObject(Config.CELL_SIZE, Config.CELL_SIZE, 2, ItemProperties.COIN));
        monsters.addMonster(new Monster(13 * Config.CELL_SIZE, 5 * Config.CELL_SIZE, 2, BatImages.BAT_UP1, 500, 30));

        //Location 3
        items.addItem(new ItemObject(3 * Config.CELL_SIZE, 5 * Config.CELL_SIZE, 3, ItemProperties.DOOR));
        items.addItem(new ItemObject(13 * Config.CELL_SIZE, 9 * Config.CELL_SIZE, 3, ItemProperties.DOOR));
        items.addItem(new ItemObject(10 * Config.CELL_SIZE, 3 * Config.CELL_SIZE, 3, ItemProperties.KEY));
        items.addItem(new ItemObject(Config.CELL_SIZE, 8 * Config.CELL_SIZE, 3, ItemProperties.POTION));
        items.addItem(new ItemObject(15 * Config.CELL_SIZE, 6 * Config.CELL_SIZE, 3, ItemProperties.COIN));
        items.addItem(new ItemObject(8 * Config.CELL_SIZE, 4 * Config.CELL_SIZE, 3, ItemProperties.COIN));
        monsters.addMonster(new Monster(4 * Config.CELL_SIZE, 7 * Config.CELL_SIZE, 3, BatImages.BAT_UP1, 500, 30));
        monsters.addMonster(new Monster(9 * Config.CELL_SIZE, 3 * Config.CELL_SIZE, 3, BatImages.BAT_UP1, 500, 30));

        //Location 4
        items.addItem(new ItemObject(5 * Config.CELL_SIZE, 9 * Config.CELL_SIZE, 4, ItemProperties.DOOR));
        monsters.addMonster(new Monster(7 * Config.CELL_SIZE, 2 * Config.CELL_SIZE, 4, BatImages.BAT_UP1, 500, 30));
        monsters.addMonster(new Monster(4 * Config.CELL_SIZE, 7 * Config.CELL_SIZE, 4, BatImages.BAT_UP1, 500, 30));


        //Location 5
        items.addItem(new ItemObject(14 * Config.CELL_SIZE, 6 * Config.CELL_SIZE, 5, ItemProperties.POTION));
        items.addItem(new ItemObject(10 * Config.CELL_SIZE, 8 * Config.CELL_SIZE, 5, ItemProperties.COIN));
        items.addItem(new ItemObject(12 * Config.CELL_SIZE, 2 * Config.CELL_SIZE, 5, ItemProperties.COIN));
        monsters.addMonster(new Monster(7 * Config.CELL_SIZE, Config.CELL_SIZE, 5, BatImages.BAT_UP1, 500, 30));
        monsters.addMonster(new Monster(2 * Config.CELL_SIZE, 7 * Config.CELL_SIZE, 5, BatImages.BAT_UP1, 500, 30));


        //Location 6
        items.addItem(new ItemObject(13 * Config.CELL_SIZE, 9 * Config.CELL_SIZE, 6, ItemProperties.DOOR));
        items.addItem(new ItemObject(2 * Config.CELL_SIZE, 2 * Config.CELL_SIZE, 6, ItemProperties.KEY));
        items.addItem(new ItemObject(8 * Config.CELL_SIZE, Config.CELL_SIZE, 6, ItemProperties.COIN));
        items.addItem(new ItemObject(10 * Config.CELL_SIZE, 8 * Config.CELL_SIZE, 6, ItemProperties.COIN));
        items.addItem(new ItemObject(16 * Config.CELL_SIZE, 7 * Config.CELL_SIZE, 6, ItemProperties.COIN));
        monsters.addMonster(new Monster(2 * Config.CELL_SIZE, 4 * Config.CELL_SIZE, 6, BatImages.BAT_UP1, 500, 30));
        monsters.addMonster(new Monster(12 * Config.CELL_SIZE, 6 * Config.CELL_SIZE, 6, BatImages.BAT_UP1, 500, 30));
        monsters.addMonster(new Monster(12 * Config.CELL_SIZE, 2 * Config.CELL_SIZE, 6, BatImages.BAT_UP1, 500, 30));


        //Location 7
        items.addItem(new ItemObject(6 * Config.CELL_SIZE, 2 * Config.CELL_SIZE, 7, ItemProperties.KEY));
        items.addItem(new ItemObject(16 * Config.CELL_SIZE, 8 * Config.CELL_SIZE, 7, ItemProperties.KEY));
        items.addItem(new ItemObject(3 * Config.CELL_SIZE, Config.CELL_SIZE, 7, ItemProperties.POTION));
        items.addItem(new ItemObject(8 * Config.CELL_SIZE, Config.CELL_SIZE, 7, ItemProperties.COIN));
        items.addItem(new ItemObject(Config.CELL_SIZE, Config.CELL_SIZE, 7, ItemProperties.COIN));
        monsters.addMonster(new Monster(3 * Config.CELL_SIZE, 6 * Config.CELL_SIZE, 7, BatImages.BAT_UP1, 500, 30));
        monsters.addMonster(new Monster(15 * Config.CELL_SIZE, 3 * Config.CELL_SIZE, 7, BatImages.BAT_UP1, 500, 30));


        //Location 8
        items.addItem(new ItemObject(12 * Config.CELL_SIZE, 2 * Config.CELL_SIZE, 8, ItemProperties.COIN));
        items.addItem(new ItemObject(16 * Config.CELL_SIZE, 7 * Config.CELL_SIZE, 8, ItemProperties.COIN));
        monsters.addMonster(new Monster(14 * Config.CELL_SIZE, 4 * Config.CELL_SIZE, 8, BatImages.BAT_UP1, 500, 30));
        monsters.addMonster(new Monster(4 * Config.CELL_SIZE, 3 * Config.CELL_SIZE, 8, BatImages.BAT_UP1, 500, 30));
        monsters.addMonster(new Monster(4 * Config.CELL_SIZE, 6 * Config.CELL_SIZE, 8, BatImages.BAT_UP1, 500, 30));
        monsters.addMonster(new Monster(8 * Config.CELL_SIZE, 3 * Config.CELL_SIZE, 8, BatImages.BAT_UP1, 500, 30));
        monsters.addMonster(new Monster(8 * Config.CELL_SIZE, 6 * Config.CELL_SIZE, 8, BatImages.BAT_UP1, 500, 30));

        //Location 9
        items.addItem(new ItemObject(2 * Config.CELL_SIZE, 2 * Config.CELL_SIZE, 9, ItemProperties.KEY));
        items.addItem(new ItemObject(Config.CELL_SIZE, 6 * Config.CELL_SIZE, 9, ItemProperties.COIN));
        items.addItem(new ItemObject(13 * Config.CELL_SIZE, 5 * Config.CELL_SIZE, 9, ItemProperties.COIN));
        monsters.addMonster(new Monster(15 * Config.CELL_SIZE, 2 * Config.CELL_SIZE, 9, BatImages.BAT_UP1, 500, 30));
        monsters.addMonster(new Monster(5 * Config.CELL_SIZE, 2 * Config.CELL_SIZE, 9, BatImages.BAT_UP1, 500, 30));


        //Location 10
        items.addItem(new ItemObject(11 * Config.CELL_SIZE, 6 * Config.CELL_SIZE, 10, ItemProperties.POTION));
        items.addItem(new ItemObject(9 * Config.CELL_SIZE, 2 * Config.CELL_SIZE, 10, ItemProperties.COIN));
        items.addItem(new ItemObject(12 * Config.CELL_SIZE, Config.CELL_SIZE, 10, ItemProperties.COIN));
        items.addItem(new ItemObject(16 * Config.CELL_SIZE, 3 * Config.CELL_SIZE, 10, ItemProperties.COIN));
        monsters.addMonster(new Monster(4 * Config.CELL_SIZE, 4 * Config.CELL_SIZE, 10, BatImages.BAT_UP1, 500, 30));
        monsters.addMonster(new Monster(14 * Config.CELL_SIZE, 5 * Config.CELL_SIZE, 10, BatImages.BAT_UP1, 500, 30));


        //Location 11
        items.addItem(new ItemObject(5 * Config.CELL_SIZE, 8 * Config.CELL_SIZE, 11, ItemProperties.KEY));
        items.addItem(new ItemObject(2 * Config.CELL_SIZE, 2 * Config.CELL_SIZE, 11, ItemProperties.COIN));
        items.addItem(new ItemObject(4 * Config.CELL_SIZE, 7 * Config.CELL_SIZE, 11, ItemProperties.COIN));
        monsters.addMonster(new Monster(3 * Config.CELL_SIZE, 4 * Config.CELL_SIZE, 11, BatImages.BAT_UP1, 500, 30));
        monsters.addMonster(new Monster(14 * Config.CELL_SIZE, 3 * Config.CELL_SIZE, 11, BatImages.BAT_UP1, 500, 30));


        //Location 12
        items.addItem(new ItemObject(8 * Config.CELL_SIZE, 6 * Config.CELL_SIZE, 12, ItemProperties.DOOR));
        items.addItem(new ItemObject(9 * Config.CELL_SIZE, 4 * Config.CELL_SIZE, 12, ItemProperties.POTION));
        items.addItem(new ItemObject(3 * Config.CELL_SIZE, 7 * Config.CELL_SIZE, 12, ItemProperties.COIN));
        monsters.addMonster(new Monster(4 * Config.CELL_SIZE, 4 * Config.CELL_SIZE, 12, BatImages.BAT_UP1, 500, 30));
        monsters.addMonster(new Monster(13 * Config.CELL_SIZE, 3 * Config.CELL_SIZE, 12, BatImages.BAT_UP1, 500, 30));

    }
}

