package DungeonCrawler.GameManeger;

import DungeonCrawler.Frame;

import javax.swing.*;

public class GameFrame implements Frame {
    @Override
    public void addGameFrame() {
        JFrame frame = new JFrame("Dungeon Crawler");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setTitle("Dungeon Crawler");

        GamePanel panel = new GamePanel();
        frame.add(panel);
        frame.pack();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}
