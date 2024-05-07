package DungeonCrawler.gameDisplay;

import DungeonCrawler.Config;
import DungeonCrawler.controller.KeyBoardAction;
import DungeonCrawler.gameManager.GameEngine;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    KeyBoardAction key = new KeyBoardAction();

    public GamePanel(){
        this.setPreferredSize(new Dimension(Config.LOCATION_WIDTH, Config.LOCATION_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(key);
        this.setFocusable(true);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        try {
            GameEngine.drawLocation.drawCurrentLocation(g2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        GameEngine.player.drawEntity(g2);
        GameEngine.monster.drawMonster(g2);
        }

    }

