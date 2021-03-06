package com.almasb.fxgltest;

import com.almasb.fxgl.dsl.FXGL;

import static com.almasb.fxgl.dsl.FXGL.onCollisionBegin;

/**
 * author simon Florian Büchi
 */
public class CollisionEvents {

    Stats stats = Stats.getInstance();

    public void initphysics() {

        // Projectile hits Food
        onCollisionBegin(Entities.PROJECTILE, Entities.FOOD_RECTANGLE, (bullet, enemy) -> {
            bullet.removeFromWorld();
            enemy.removeFromWorld();
            stats.spawnFood("food_rectangle");
            FXGL.inc("score", +1);
            stats.incUpgradeScore(1);
        });
        onCollisionBegin(Entities.PROJECTILE, Entities.FOOD_TRIANGLE, (bullet, enemy) -> {
            bullet.removeFromWorld();
            enemy.removeFromWorld();
            stats.spawnFood("food_triangle");
            FXGL.inc("score", +2);
            stats.incUpgradeScore(2);
        });
        onCollisionBegin(Entities.PROJECTILE, Entities.FOOD_OCTAGON, (bullet, enemy) -> {
            bullet.removeFromWorld();
            enemy.removeFromWorld();
            stats.spawnFood("food_octagon");
            FXGL.inc("score", +5);
            stats.incUpgradeScore(5);
        });

        // Player hits food
        onCollisionBegin(Entities.PLAYER, Entities.FOOD_RECTANGLE, (player, rect) -> {
            FXGL.inc("hp", -1);
            stats.checkIfDead();

        });
        onCollisionBegin(Entities.PLAYER, Entities.FOOD_TRIANGLE, (player, rect) -> {
            FXGL.inc("hp", -2);
            stats.checkIfDead();

        });
        onCollisionBegin(Entities.PLAYER, Entities.FOOD_OCTAGON, (player, rect) -> {
            FXGL.inc("hp", -5);
            stats.checkIfDead();

        });
        onCollisionBegin(Entities.PLAYER, Entities.ENEMY, (player, rect) -> {
            FXGL.inc("hp", -5);
            rect.removeFromWorld();
            stats.spawnFood("enemy");
            stats.checkIfDead();

        });

        // Projectile hits enemy
        onCollisionBegin(Entities.PROJECTILE, Entities.ENEMY, (proj, enemy) -> {
            proj.removeFromWorld();
            enemy.removeFromWorld();
            stats.spawnFood("enemy");
            FXGL.inc("score", 10);
            stats.incUpgradeScore(5);
        });
        onCollisionBegin(Entities.PROJECTILE, Entities.ENEMY_TRIANGLE, (proj, enemy) -> {
            proj.removeFromWorld();
            enemy.removeFromWorld();
            stats.spawnFood("enemy-triangle");
            FXGL.inc("score", 10);
            stats.incUpgradeScore(5);
        });

        // Projectile hits Player
        onCollisionBegin(Entities.PLAYER, Entities.ENEMY_PROJECTILE, (player, rect) -> {
            rect.removeFromWorld();
            FXGL.inc("hp", -2);
            stats.checkIfDead();

        });

        // Player hits Medkit
        onCollisionBegin(Entities.PLAYER, Entities.MED_BIG, (player, med) -> {
            stats.spawnFood("med_big");
            med.removeFromWorld();
            FXGL.inc("hp", 10);
        });
        onCollisionBegin(Entities.PLAYER, Entities.MED_SMALL, (player, med) -> {
            stats.spawnFood("med_small");
            med.removeFromWorld();
            FXGL.inc("hp", 5);
        });
    }
}
