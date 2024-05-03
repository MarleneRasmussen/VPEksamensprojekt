package DungeonCrawler;

import DungeonCrawler.Dungeon.DungeonLocation;

import java.awt.*;

public interface Location {
    public void drawLocation(Graphics2D g2);
    public int[][] getLocation(DungeonLocation location);
}
