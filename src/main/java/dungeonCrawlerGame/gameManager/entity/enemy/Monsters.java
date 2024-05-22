package dungeonCrawlerGame.gameManager.entity.enemy;

import dungeonCrawlerGame.Config;
import dungeonCrawlerGame.controller.CollisionChecker;
import dungeonCrawlerGame.gameView.scenes.GameScene;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Monsters {

    private static List<Monster> monsters;
    private int damageCounter = 0;
    private int staminaCounter = 0;

    public Monsters()
    {
        monsters = new ArrayList<Monster>();
    }

    public void addMonster(Monster monster)
    {
        monsters.add(monster);
    }

    public void removeMonster(Monster monster){
        monsters.remove(monster);
    }

    public void updateMonsters()
    {
        for (Monster monster : monsters)
        {
            monster.moveEntity();
        }
    }

    public void checkDead()
    {
        for (Monster monster : monsters)
        {
            if (monster.getHealth() <= 0)
            {
                monster.setLocationNumber(0);
            }
        }
    }

    public void checkCollision()
    {
        for (int i = 0; i < monsters.size(); i++)
        {
            int index = CollisionChecker.checkEntityCollision(monsters.get(i),getMonsters());
            if (i == index)
            {
                monsters.get(i).setCollision(false);
            }
            if (monsters.get(i).getLocationNumber() != 0) {
                CollisionChecker.checkCellCollision(monsters.get(i), monsters.get(i).getLocationNumber());
            }
            CollisionChecker.checkItemCollision(monsters.get(i));
        }
    }

    public void checkForAttack(){
        for (Monster monster : monsters)
        {
            monster.setPlayerMonsterCollision(false);
            if (monster.getLocationNumber() == GameScene.currentLocationNum) {
                CollisionChecker.checkPlayerCollision(monster);
                if (monster.playerCollision) {
                    monster.attacks();
                }
            }
        }
    }

    public void checkForDamage(){
        for (Monster monster : monsters){
            if (monster.getLocationNumber()==GameScene.currentLocationNum) {
                monster.takeDamage(CollisionChecker.checkDamageCollision(monster));
            }
        }
    }

    public static List<Monster> getMonsters()
    {
        return monsters;
    }

    public void drawMonsters(Graphics g)
    {
        for (Monster monster : monsters)
        {
            if (monster.getLocationNumber() == GameScene.currentLocationNum)
            {
                g.drawImage(monster.getImage(), monster.getPosX(), monster.getPosY(), Config.CELL_SIZE, Config.CELL_SIZE, null);

                double monsterHealth = monster.getHealth();
                double monsterMaxHealth = monster.getMaxHealth();
                int monsterHealthBar = (int) ((monsterHealth / monsterMaxHealth) * Config.CELL_SIZE);

                g.setColor(new Color(50,0,0));
                g.fillRoundRect( monster.getPosX()-3, monster.getPosY() - 23, Config.CELL_SIZE+6, 21, 10, 10);

                g.setColor(new Color(255,0,60));
                g.fillRoundRect( monster.getPosX(), monster.getPosY() - 20, monsterHealthBar, 15, 10, 10);
            }
        }
    }
}

