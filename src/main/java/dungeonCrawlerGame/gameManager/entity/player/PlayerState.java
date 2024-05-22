package dungeonCrawlerGame.gameManager.entity.player;

import dungeonCrawlerGame.Config;
import dungeonCrawlerGame.controller.KeyAction;
import dungeonCrawlerGame.gameManager.item.ItemObject;
import dungeonCrawlerGame.gameManager.item.ItemProperties;
import dungeonCrawlerGame.gameView.scenes.GameScene;

import java.awt.*;

public class PlayerState {
    private static int coins;
    private static int keys;
    private static int potions;

    public static void addItemToInventory(ItemObject item) {
        if(item.getItemProperties() == ItemProperties.COIN) {
            coins++;
        }
        if(item.getItemProperties() == ItemProperties.KEY) {
            keys++;
        }
        if(item.getItemProperties() == ItemProperties.POTION) {
            potions++;
        }
    }

    public static void updateInventory(ItemObject item) {
        if(item.getItemProperties() == ItemProperties.DOOR && keys > 0) {
            item.pickUp(true);
            keys--;
        }
    }

    public void useFromInventory() {
        if(potions > 0 && GameScene.player.getHealth() < Config.DEFAULT_PLAYER_HEALTH && KeyAction.usePotion) {
                GameScene.player.setHealth(Config.DEFAULT_PLAYER_HEALTH);
                potions--;
        }
    }

    public void renderInventoryValue(Graphics g) {
        g.setColor(new Color(0,0,0,150));
        g.fillRoundRect(1130, 10, 575, 70, 10, 10);

        g.setColor(new Color(255,255,255));
        g.setFont(new Font("Arial", Font.BOLD, 50));
        g.drawString(String.valueOf(coins), 1220, 65);

        g.setColor(new Color(255,255,255));
        g.setFont(new Font("Arial", Font.BOLD, 50));
        g.drawString(String.valueOf(keys), 1420, 65);

        g.setColor(new Color(255,255,255));
        g.setFont(new Font("Arial", Font.BOLD, 50));
        g.drawString(String.valueOf(potions), 1620, 65);
    }

    public void renderInventoryImages(Graphics g) {
        g.drawImage(ItemProperties.COIN.getImage(), 1150, 20, 50,50, null);
        g.drawImage(ItemProperties.KEY.getImage(), 1350, 20, 50,50,null);
        g.drawImage(ItemProperties.POTION.getImage(), 1550, 20, 50,50,null);
    }

    public void renderPlayerHP(Graphics g) {

        double playerHealth = GameScene.player.getHealth();
        double playerMaxHealth = Config.DEFAULT_PLAYER_HEALTH;
        System.out.println(playerHealth);
        int barWidth = 200;
        int playerHealthBar = (int) ((playerHealth / playerMaxHealth) * barWidth);

        g.setColor(new Color(0,0,0,150));
        g.fillRoundRect(100, 10,320, 50, 10, 10);

        g.setColor(new Color(255,255,255));
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("HEALTH:" ,110, 43);

        g.setColor(new Color(50,0,0));
        g.fillRoundRect( 200, 20, barWidth + 6, 31, 10, 10);

        g.setColor(new Color(255,0,60));
        g.fillRoundRect( 203, 23, playerHealthBar, 25, 10, 10);
    }

    public void renderPlayerStamina(Graphics g) {

        double playerStamina = GameScene.player.getStamina();
        double playerMaxStamina = Config.DEFAULT_PLAYER_STAMINA;
        int barWidth = 200;
        int playerStaminaBar = (int) ((playerStamina / playerMaxStamina) * barWidth);

        g.setColor(new Color(0,0,0,150));
        g.fillRoundRect(500, 10,330, 50, 10, 10);

        g.setColor(new Color(255,255,255));
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("STAMINA:" ,510, 43);

        g.setColor(new Color(20,0,90));
        g.fillRoundRect( 610, 20, barWidth + 6, 31, 10, 10);

        g.setColor(new Color(50,100,255));
        g.fillRoundRect( 613, 23, playerStaminaBar, 25, 10, 10);
    }
}
