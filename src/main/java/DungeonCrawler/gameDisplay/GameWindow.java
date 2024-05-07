package DungeonCrawler.gameDisplay;

import DungeonCrawler.Frame;

import javax.swing.*;

public class GameWindow implements Frame {
    @Override
    public void addGameFrame(GamePanel gamelPanel ) {
        JFrame window = new JFrame("Dungeon Crawler");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Dungeon Crawler");

        window.add(gamelPanel);
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}
