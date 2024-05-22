package dungeonCrawlerGame.controller;

import dungeonCrawlerGame.Config;
import dungeonCrawlerGame.gameManager.entity.Entity;
import dungeonCrawlerGame.gameManager.entity.enemy.Monster;
import dungeonCrawlerGame.gameManager.entity.player.Player;
import dungeonCrawlerGame.gameManager.item.ItemObject;
import dungeonCrawlerGame.gameManager.item.Items;
import dungeonCrawlerGame.gameManager.location.Cells;
import dungeonCrawlerGame.gameManager.location.Locations;
import dungeonCrawlerGame.gameView.scenes.GameScene;

import java.util.List;

public class CollisionChecker {

    static Player player;
    static Locations locations = new Locations();

    public CollisionChecker(Player player){
        this.player = player;
    }

        public static void checkCellCollision(Entity entity, int locationNumber) {
            Cells[][] currentLocation = locations.returnLocation(locationNumber);

            int entityLeft = entity.getPosX() + Config.CELL_SIZE / 6;
            int entityRight = entity.getPosX() + (Config.CELL_SIZE / 6) * 5;
            int entityTop = entity.getPosY() + Config.CELL_SIZE / 3;
            int entityBottom = entity.getPosY() + Config.CELL_SIZE;

            int entityRightColumn = entityRight / Config.CELL_SIZE;
            int entityLeftColumn = entityLeft / Config.CELL_SIZE;
            int entityTopRow = entityTop / Config.CELL_SIZE;
            int entityBottomRow = entityBottom / Config.CELL_SIZE;

            Cells cell1;
            Cells cell2;

            switch (entity.getDirection()) {
                case UP:
                    entityTopRow = (entityTop - entity.getSpeed()) / Config.CELL_SIZE;
                    cell1 = currentLocation[entityTopRow][entityLeftColumn];
                    cell2 = currentLocation[entityTopRow][entityRightColumn];
                    if (cell1.isSolid() || cell2.isSolid())
                        entity.setCollision(true);

                    break;
                case DOWN:
                    entityBottomRow = (entityBottom + entity.getSpeed()) / Config.CELL_SIZE;
                    cell1 = currentLocation[entityBottomRow][entityLeftColumn];
                    cell2 = currentLocation[entityBottomRow][entityRightColumn];
                    if (cell1.isSolid() || cell2.isSolid())
                        entity.setCollision(true);

                    break;
                case LEFT:
                    entityLeftColumn = (entityLeft - entity.getSpeed()) / Config.CELL_SIZE;
                    cell1 = currentLocation[entityTopRow][entityLeftColumn];
                    cell2 = currentLocation[entityBottomRow][entityLeftColumn];
                    if (cell1.isSolid() || cell2.isSolid())
                        entity.setCollision(true);

                    break;
                case RIGHT:
                    entityRightColumn = (entityRight + entity.getSpeed()) / Config.CELL_SIZE;
                    cell1 = currentLocation[entityTopRow][entityRightColumn];
                    cell2 = currentLocation[entityBottomRow][entityRightColumn];
                    if (cell1.isSolid() || cell2.isSolid())
                        entity.setCollision(true);
                    break;
            }
        }

        public static Cells getCurrentCell(Player player) {
            Cells[][] currentLocation = GameScene.currentLocation;

            int playerCenter = player.getPosX() + Config.CELL_SIZE / 2;
            int playerBottom = player.getPosY() + Config.CELL_SIZE - 10;

            int playerColumn = playerCenter / Config.CELL_SIZE;
            int playerBottomRow = playerBottom / Config.CELL_SIZE;

            return currentLocation[playerBottomRow][playerColumn];
        }

        public static int checkEntityCollision(Entity entity, List<Monster> monsters) {

            int index = -1;
            for (int i = 0; i < monsters.size(); i++) {
                if (monsters.get(i).getLocationNumber() == GameScene.currentLocationNum) {

                    Monster monster = monsters.get(i);
                    monster.getBounds().x = monster.getPosX();
                    monster.getBounds().y = monster.getPosY();

                    entity.getBounds().x = entity.getPosX();
                    entity.getBounds().y = entity.getPosY();

                    switch (entity.getDirection()) {
                        case UP:
                            entity.getBounds().y -= entity.getSpeed();
                            if (entity.getBounds().intersects(monster.getBounds())) {
                                entity.setCollision(true);
                                index = i;
                            }
                            break;
                        case DOWN:
                            entity.getBounds().y += entity.getSpeed();
                            if (entity.getBounds().intersects(monster.getBounds())) {
                                entity.setCollision(true);
                                index = i;
                            }
                            break;
                        case LEFT:
                            entity.getBounds().x -= entity.getSpeed();
                            if (entity.getBounds().intersects(monster.getBounds())) {
                                entity.setCollision(true);
                                index = i;
                            }
                            break;
                        case RIGHT:
                            entity.getBounds().x += entity.getSpeed();
                            if (entity.getBounds().intersects(monster.getBounds())) {
                                entity.setCollision(true);
                                index = i;
                            }
                            break;
                    }
                }
            }
            return index;
        }

        public static void checkPlayerCollision(Entity entity) {

            entity.getBounds().x = entity.getPosX();
            entity.getBounds().y = entity.getPosY();

            player.getBounds().x = player.getPosX();
            player.getBounds().y = player.getPosY();

            switch(entity.getDirection()){
                case UP:
                    entity.getBounds().y -= entity.getSpeed();
                    if(entity.getBounds().intersects(player.getBounds())){
                        entity.setPlayerMonsterCollision(true);
                    }
                    break;
                case DOWN:
                    entity.getBounds().y += entity.getSpeed();
                    if(entity.getBounds().intersects(player.getBounds())){
                        entity.setPlayerMonsterCollision(true);
                    }
                    break;
                case LEFT:
                    entity.getBounds().x -= entity.getSpeed();
                    if(entity.getBounds().intersects(player.getBounds())){
                        entity.setPlayerMonsterCollision(true);
                    }
                    break;
                case RIGHT:
                    entity.getBounds().x += entity.getSpeed();
                    if(entity.getBounds().intersects(player.getBounds())){
                        entity.setPlayerMonsterCollision(true);
                    }
                    break;
            }
        }

        public static int checkDamageCollision(Entity entity){
            int damage = 0;

            entity.getBounds().x = entity.getPosX();
            entity.getBounds().y = entity.getPosY();

            switch(player.getDirection()) {
                case UP:
                    if (player.playerAttacks()) {
                        player.getBounds().x = player.getPosX();
                        player.getBounds().y = player.getPosY() - Config.CELL_SIZE;
                        if (entity.getBounds().intersects(player.getBounds())) {
                            damage = Config.DEFAULT_PLAYER_DAMAGE;
                        }
                    }
                    break;
                case DOWN:
                    if (player.playerAttacks()) {
                        player.getBounds().x = player.getPosX();
                        player.getBounds().y = player.getPosY() + Config.CELL_SIZE;
                        if (entity.getBounds().intersects(player.getBounds())) {
                            damage = Config.DEFAULT_PLAYER_DAMAGE;
                        }
                    }
                    break;
                case LEFT:
                    if (player.playerAttacks()) {
                        player.getBounds().x = player.getPosX() - Config.CELL_SIZE;
                        player.getBounds().y = player.getPosY();
                        if (entity.getBounds().intersects(player.getBounds())) {
                            damage = Config.DEFAULT_PLAYER_DAMAGE;
                        }
                    }
                    break;
                case RIGHT:
                    if (player.playerAttacks()) {
                        player.getBounds().x = player.getPosX() + Config.CELL_SIZE;
                        player.getBounds().y = player.getPosY();
                        if (entity.getBounds().intersects(player.getBounds())) {
                            damage = Config.DEFAULT_PLAYER_DAMAGE;
                        }
                        break;
                    }
            }
            return damage;
        }

        public static int checkItemCollision(Entity entity) {

            int index = -1;

            for (int i = 0; i < Items.getItem().size(); i++) {
                if (Items.getItem().get(i).getLocationNumber() == GameScene.currentLocationNum) {
                    ItemObject item = Items.getItem().get(i);
                    item.itemBounds.x = item.getPosX();
                    item.itemBounds.y = item.getPosY();

                    entity.getBounds().x = entity.getPosX();
                    entity.getBounds().y = entity.getPosY();

                    switch (entity.getDirection()) {
                        case UP:
                            entity.getBounds().y -= entity.getSpeed();
                            if (entity.getBounds().intersects(item.itemBounds)) {
                                if(item.getItemProperties().isSolid()) {
                                    entity.setCollision(true);
                                    if (entity instanceof Player) {
                                        index = i;
                                    }
                                }
                                else if(!item.getItemProperties().isSolid() && entity instanceof Player){
                                    item.pickUp(true);
                                }
                            }
                            break;
                        case DOWN:
                            entity.getBounds().y += entity.getSpeed();
                            if (entity.getBounds().intersects(item.itemBounds)) {
                                if(item.getItemProperties().isSolid()) {
                                    entity.setCollision(true);
                                    if (entity instanceof Player) {
                                        index = i;
                                    }
                                }
                                else if(!item.getItemProperties().isSolid() && entity instanceof Player){
                                    item.pickUp(true);
                                }
                            }
                            break;
                        case LEFT:
                            entity.getBounds().x -= entity.getSpeed();
                            if (entity.getBounds().intersects(item.itemBounds)) {
                                if(item.getItemProperties().isSolid()) {
                                    entity.setCollision(true);
                                    if (entity instanceof Player) {
                                        index = i;
                                    }
                                }
                                else if(!item.getItemProperties().isSolid() && entity instanceof Player){
                                    item.pickUp(true);
                                }
                            }
                            break;
                        case RIGHT:
                            entity.getBounds().x += entity.getSpeed();
                            if (entity.getBounds().intersects(item.itemBounds)) {
                                if(item.getItemProperties().isSolid()) {
                                    entity.setCollision(true);
                                    if (entity instanceof Player) {
                                        index = i;
                                    }
                                }
                                else if(!item.getItemProperties().isSolid() && entity instanceof Player){
                                    item.pickUp(true);
                                }
                            }
                            break;
                    }
                    entity.getBounds().x = entity.getPosX();
                    entity.getBounds().y = entity.getPosY();
                }
            }
            return index;
        }
    }