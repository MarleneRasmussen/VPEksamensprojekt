package DungeonCrawler;

import DungeonCrawler.GameManeger.GameEngine;
import DungeonCrawler.GameManeger.GameLogic;

public class RunGame {
    public static void main(String[] args)
    {
        GameLogic gameLogic = new GameLogic();
        GameEngine.init(gameLogic);
        GameEngine.startGame();

    }
}
