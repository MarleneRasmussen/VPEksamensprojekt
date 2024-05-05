package DungeonCrawler.GameManeger;

import DungeonCrawler.Dungeon.DrawLocation;
import DungeonCrawler.Dungeon.DungeonLocation;
import DungeonCrawler.Entities.Player;
import DungeonCrawler.Entity;
import DungeonCrawler.Recourses.CollisionChecker;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    public final int cellSizePixel = 32;
    public final int scale = 3;

    public final int cellSize = cellSizePixel * scale; //48x48 pixel
    public final int locationColumn = 18;
    public final int locationRow = 10;
    public final int locationWidth = locationColumn * cellSize;
    public final int locationHeight = locationRow * cellSize;

    public int FPS = 60;
    public int currentLocationNum;
    public int nextLocationNum;

    KeyBoardAction key = new KeyBoardAction();
    Thread gameThread;
    Entity player = new Player(this);
    DungeonLocation dl = new DungeonLocation((Player) player);
    DrawLocation drawLocation = new DrawLocation(this);
    public CollisionChecker cc = new CollisionChecker(this);

    public GamePanel(){
        this.setPreferredSize(new Dimension(locationWidth, locationHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(key);
        this.setFocusable(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run(){
        double interval = 1000000000/FPS;
        double nextDraw = System.nanoTime() + interval;

        while (gameThread != null){
            update();
            repaint();

            try {
                double remaining = nextDraw - System.nanoTime();
                Thread.sleep((long) (remaining / 1000000));
                nextDraw += interval;

                if (remaining < 0){
                    remaining = 0;
                }
            }
            catch (InterruptedException e){
                throw new RuntimeException(e);
            }
        }
    }

    public void update(){
        player.moveEntity();
        currentLocationNum = dl.getCurrentWorldLocation();
        drawLocation.getNewLocation();
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        drawLocation.drawCurrentLocation(g2);
        player.drawEntity(g2);
    }
}
