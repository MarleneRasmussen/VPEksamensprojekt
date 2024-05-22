package dungeonCrawlerGame.gameView.scenes;

import dungeonCrawlerGame.controller.GameState;
import dungeonCrawlerGame.controller.KeyAction;
import dungeonCrawlerGame.gameView.MyGame;
import dungeonCrawlerGame.gameView.Scene;
import dungeonCrawlerGame.gameView.SceneManager;

import javax.swing.*;
import java.awt.*;

public class StartScene extends JPanel implements Scene {

    SceneManager sceneManager;
    KeyAction keyAction = new KeyAction();
    MyGame myGame;
    public GameState gameState;


    public StartScene(SceneManager sceneManager, MyGame myGame, KeyAction keyAction) {
        this.sceneManager = sceneManager;
        this.myGame = myGame;
        this.keyAction = keyAction;

    }

    @Override
    public void update() {
        if (SceneManager.gameState == GameState.RUNNING) {
            sceneManager.setScene(myGame.game);
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 100));
        g.drawString("Welcome to Dungeon Crawler!", 150, 200);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 70));
        g.drawString("Press Enter to start the game", 400, 550);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        g.drawString("Press ESC to quit game", 600, 700);

    }
}
