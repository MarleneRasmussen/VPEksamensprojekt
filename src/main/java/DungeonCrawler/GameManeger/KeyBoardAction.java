package DungeonCrawler.GameManeger;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyBoardAction implements KeyListener {
    public static boolean up = false;
    public static boolean down = false;
    public static boolean left = false;
    public static boolean right = false;

    @Override
    public void keyTyped(KeyEvent e)
    {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W) {
            up = true;
        }
        if (key == KeyEvent.VK_S) {
            down = true;
        }
        if (key == KeyEvent.VK_A) {
            left = true;
        }
        if (key == KeyEvent.VK_D) {
            right = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W) {
            up =false;
        }
        if (key == KeyEvent.VK_S) {
            down = false;
        }
        if (key == KeyEvent.VK_A) {
            left = false;
        }
        if (key == KeyEvent.VK_D) {
            right = false;
        }
    }
}
