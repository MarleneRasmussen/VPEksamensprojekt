package DungeonCrawler;

public class Config {
    //View layout configs
    public static final int CELL_PIXEL = 32;
    public static final int SCALE = 3;

    public static final int CELL_SIZE = CELL_PIXEL * SCALE; //48x48 pixel
    public static final int LOCATION_COLUMN = 18;
    public static final int LOCATION_ROW = 10;
    public static final int LOCATION_WIDTH = LOCATION_COLUMN * CELL_SIZE;
    public static final int LOCATION_HEIGHT = LOCATION_ROW * CELL_SIZE;
}
