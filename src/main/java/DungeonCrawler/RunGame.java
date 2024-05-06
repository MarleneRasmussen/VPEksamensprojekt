package DungeonCrawler;

import DungeonCrawler.GameManeger.GameEngine;
import DungeonCrawler.GameManeger.GameFrame;
import DungeonCrawler.GameManeger.GameLogic;

public class RunGame {
    public static void main(String[] args)
    {
        Frame gameFrame = new GameFrame();
        gameFrame.addGameFrame();
        GameLogic gameLogic = new GameLogic();
        GameEngine.init(gameLogic);
        GameEngine.startGame();

    }
}
