package DungeonCrawler;

import java.awt.*;

public interface Entity
{
    void moveEntity();
    void setEntityLocation();
    void getEntityImage();
    void drawEntity(Graphics2D g2);
}
