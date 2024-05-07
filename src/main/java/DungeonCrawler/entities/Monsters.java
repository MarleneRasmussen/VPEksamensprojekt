package DungeonCrawler.entities;

import DungeonCrawler.gameManager.GameEngine;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Monsters
    {
        private List<Monster> monsters;

        public Monsters()
        {
            monsters = new ArrayList<Monster>();
        }

        public void addMonster(Monster monster)
        {
            monsters.add(monster);
        }

        public void updateMonsters()
        {
            for (Monster monster : monsters)
            {
                monster.update();
            }
        }

        public List<Monster> getMonsters()
        {
            return monsters;
        }

        public void drawMonsters(Graphics2D g2)
        {
            for (Monster monster : monsters)
            {
                if (monster.getLocationNum() == GameEngine.currentLocationNum)
                {
                    int x = monster.getPosX();
                    int y = monster.getPosY();
                    g2.drawRect(x, y, 50, 50);
                }
            }
        }
    }

