package dungeonCrawlerGame;

import dungeonCrawlerGame.controller.KeyAction;
import dungeonCrawlerGame.gameView.MyGame;
import dungeonCrawlerGame.gameView.Scene;
import dungeonCrawlerGame.gameView.SceneManager;
import dungeonCrawlerGame.gameView.scenes.GameScene;
import dungeonCrawlerGame.gameView.scenes.StartScene;

import javax.swing.*;

public class RunGame {

    public static KeyAction keyAction = new KeyAction();

    public static void main(String[] args) {
        JFrame frame = new JFrame("Dungeon Crawler");
        SceneManager sceneManager = new SceneManager(Config.LOCATION_WIDTH, Config.LOCATION_HEIGHT);
        MyGame myGame = new MyGame();
        KeyAction keyAction = new KeyAction();

        Scene start = new StartScene(sceneManager, myGame, keyAction);
        Scene game = new GameScene(sceneManager, myGame, keyAction);

        sceneManager.setScene(start);
        frame.add(sceneManager);

        frame.addKeyListener(keyAction);

        frame.pack();
        frame.setVisible(true);
        frame.setFocusable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        sceneManager.startGame();
    }
}
