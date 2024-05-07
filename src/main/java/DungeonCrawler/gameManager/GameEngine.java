package DungeonCrawler.gameManager;

import DungeonCrawler.Config;
import DungeonCrawler.dungeon.DrawLocation;
import DungeonCrawler.dungeon.DungeonLocation;
import DungeonCrawler.Frame;
import DungeonCrawler.entities.Monster;
import DungeonCrawler.entities.Player;
import DungeonCrawler.eventHandler.CollisionChecker;
import DungeonCrawler.gameDisplay.GameWindow;
import DungeonCrawler.gameDisplay.GamePanel;

public class GameEngine {

    private static GamePanel gamePanel;
    public static GameLogic gameLogic;
    public static CollisionChecker collisionChecker;
    public static Frame gameFrame;
    private static Thread gameThread;

    public static Player player;
    public static Monster monster;
    public static boolean monsterInRange;
    public static int currentLocationNum;
    public static DungeonLocation dungeonLocation;
    public static DrawLocation drawLocation;

    public static void init(GameLogic gameLogic){
        GameEngine.gameLogic = gameLogic;
        gamePanel = new GamePanel();
        collisionChecker = new CollisionChecker();
        player = new Player(gamePanel);
        monster = new Monster();
        dungeonLocation = new DungeonLocation(player);
        currentLocationNum = dungeonLocation.getCurrentWorldLocation();

        drawLocation = new DrawLocation();

        gameFrame = new GameWindow();
        gameFrame.addGameFrame(gamePanel);

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
                if(remaining > 0)
                Thread.sleep((long) (remaining / 1000000));
                nextDraw += Config.INTERVAL;
            }
            catch (InterruptedException e){
                throw new RuntimeException(e);
            }
        }

    }


}
