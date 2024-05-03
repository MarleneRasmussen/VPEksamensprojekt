package DungeonCrawler.GameManeger;

import DungeonCrawler.GameFrame;

import javax.swing.*;

public class CreateGameFrame implements GameFrame {
    @Override
    public void createGameFrame() {
        JFrame frame = new JFrame("Dungeon Crawler");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setTitle("Dungeon Crawler");

        GamePanel panel = new GamePanel();
        frame.add(panel);
        frame.pack();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        panel.startGameThread();
    }
}
