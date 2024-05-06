package DungeonCrawler.ui;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageReader {
    public BufferedImage image;

    public BufferedImage readImage(String path) throws IOException
    {
        try
        {
            image = ImageIO.read(getClass().getResourceAsStream(path));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return image;
    }
}
