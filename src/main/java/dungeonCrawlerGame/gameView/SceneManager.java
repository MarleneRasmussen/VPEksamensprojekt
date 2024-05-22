package dungeonCrawlerGame.gameView;

import dungeonCrawlerGame.Config;
import dungeonCrawlerGame.controller.GameState;

import javax.swing.*;
import java.awt.*;

public class SceneManager extends JPanel implements Runnable{

    private Scene curScene;
    private Thread gameTread;
    public static GameState gameState;

    public SceneManager(int width, int height) {
        this.curScene = null;
        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
        requestFocus();
        setBackground(Color.BLACK);
        gameState = GameState.MAIN_MENU;
    }

    public void setScene(Scene scene){
        curScene = scene;
    }

    public void startGame(){
        gameTread = new Thread(this);
        gameTread.start();
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        curScene.render(g);
        //Call game render with this and g
    }

    @Override
    public void run() {

        double nextDraw = System.nanoTime() + Config.INTERVAL;

        while (gameTread != null){
                curScene.update();
                repaint();
            try {
                double remaining = nextDraw - System.nanoTime();
                if (remaining > 0)
                    Thread.sleep((long) (remaining / 1000000));
                nextDraw += Config.INTERVAL;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
