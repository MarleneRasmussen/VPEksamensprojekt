package DungeonCrawler;

import DungeonCrawler.gameManager.GameEngine;
import DungeonCrawler.gameManager.GameLogic;

public class RunGame {
    public static void main(String[] args)
    {
        GameLogic gameLogic = new GameLogic();
        GameEngine.init(gameLogic);
        GameEngine.startGame();

    }
}
