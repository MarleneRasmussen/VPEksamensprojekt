package DungeonCrawler.GameManeger;

import DungeonCrawler.Config;
import DungeonCrawler.Recourses.CollisionChecker;

public class GameEngine {

    private static GamePanel gamePanel;
    public static GameLogic gameLogic;
    public static CollisionChecker collisionChecker;

    private static Thread gameThread;

    public static void init(GameLogic gameLogic){
        GameEngine.gameLogic = gameLogic;
        gamePanel = new GamePanel();
        gameThread = new Thread(GameEngine::loop);
    }

    public static void startGame(){
        gameThread.start();
    }

    public static void loop(){
        double nextDraw = System.nanoTime() + Config.INTERVAL;

        while (gameThread != null){
            GameLogic.updateGame();
            gamePanel.repaint();

            try {
                double remaining = nextDraw - System.nanoTime();
                Thread.sleep((long) (remaining / 1000000));
                nextDraw += Config.INTERVAL;

                if (remaining < 0){
                    remaining = 0;
                }
            }
            catch (InterruptedException e){
                throw new RuntimeException(e);
            }
        }

    }


}
