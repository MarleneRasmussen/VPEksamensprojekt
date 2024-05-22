package dungeonCrawlerGame.gameView;

import dungeonCrawlerGame.Config;
import dungeonCrawlerGame.RunGame;
import dungeonCrawlerGame.gameView.scenes.GameScene;
import dungeonCrawlerGame.gameView.scenes.StartScene;

public class MyGame {
    public final SceneManager sceneManager;
    public final Scene start;
    public final Scene game;

    public MyGame() {
        this.sceneManager = new SceneManager(Config.LOCATION_WIDTH, Config.LOCATION_HEIGHT);
        this.start = new StartScene(this.sceneManager, this, RunGame.keyAction);
        this.game = new GameScene(this.sceneManager, this, RunGame.keyAction);
    }
}
