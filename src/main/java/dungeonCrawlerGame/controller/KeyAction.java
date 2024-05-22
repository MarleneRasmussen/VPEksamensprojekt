package dungeonCrawlerGame.controller;

import dungeonCrawlerGame.gameView.SceneManager;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyAction implements KeyListener {

    public static boolean up, down, right, left, attack, usePotion;

    @Override
    public void keyTyped(KeyEvent e) {
        //Nothing
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W) {
            KeyAction.up = true;
        }
        if (key == KeyEvent.VK_A) {
            KeyAction.left = true;
        }
        if (key == KeyEvent.VK_S) {
            KeyAction.down = true;
        }
        if (key == KeyEvent.VK_D) {
            KeyAction.right = true;
        }
        if (key == KeyEvent.VK_SPACE) {
            attack = true;
        }
        if (key == KeyEvent.VK_U){
            usePotion = true;
        }
        if (key == KeyEvent.VK_ESCAPE){
            if (SceneManager.gameState == GameState.PAUSED || SceneManager.gameState == GameState.MAIN_MENU || SceneManager.gameState == GameState.GAME_OVER) {
                System.exit(0);
            }
        }
        if(key ==KeyEvent.VK_ENTER) {
            SceneManager.gameState = GameState.RUNNING;
        }

        if(key ==KeyEvent.VK_P){
        if (SceneManager.gameState == GameState.RUNNING) {
            SceneManager.gameState = GameState.PAUSED;
        } else if (SceneManager.gameState == GameState.PAUSED) {
            SceneManager.gameState = GameState.RUNNING;
        }
    }
}

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W) {
            KeyAction.up = false;
        }
        if (key == KeyEvent.VK_A) {
            KeyAction.left = false;
        }
        if (key == KeyEvent.VK_S) {
            KeyAction.down = false;
        }
        if (key == KeyEvent.VK_D) {
            KeyAction.right = false;
        }
        if (key == KeyEvent.VK_SPACE) {
            attack = false;
        }
    }
}
