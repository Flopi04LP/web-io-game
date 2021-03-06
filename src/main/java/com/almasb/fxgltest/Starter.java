package com.almasb.fxgltest;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;

import com.almasb.fxgl.dsl.FXGL;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;

import static com.almasb.fxgl.dsl.FXGL.*;
import com.almasb.fxgl.input.Input;

import com.almasb.fxgl.input.UserAction;
import java.util.Map;
import java.util.Random;

/**
 * author Simon Kappeler
 */
public class Starter extends GameApplication {

    private boolean autofire = false;
    private int autofireCount = 0;
    private boolean amg = false;
    private Stats stats = Stats.getInstance();
    int fps;
    long lastTime;

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setIntroEnabled(false);
        settings.setTitle("Diep.io 2.0");
        settings.setFullScreenAllowed(true);
        settings.setHeight(stats.getHeight());
        settings.setWidth(stats.getWidth());
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    protected void initGame() {
        getGameScene().setBackgroundColor(Color.WHITE);
        getGameWorld().addEntityFactory(new Factory());

        spawn("player", getAppWidth() / 2, getAppHeight() / 2 - 30);
        stats.spawnFood("enemy");
        stats.spawnFood("enemy");

        stats.randomFood();
    }

    @Override
    protected void initInput() {
        Input input = FXGL.getInput();

        onKey(KeyCode.W, () -> getGameWorld().getSingleton(Entities.PLAYER).translateY(-stats.getSpeed()));
        onKey(KeyCode.S, () -> getGameWorld().getSingleton(Entities.PLAYER).translateY(stats.getSpeed()));

        onKey(KeyCode.A, () -> getGameWorld().getSingleton(Entities.PLAYER).translateX(-stats.getSpeed()));
        onKey(KeyCode.D, () -> getGameWorld().getSingleton(Entities.PLAYER).translateX(stats.getSpeed()));

        onKey(KeyCode.O, () -> this.autofire = this.autofire ? false : true);

        onBtnDown(MouseButton.PRIMARY, () -> {
            double y = stats.getPlayer().getY() + 10;
            double x = stats.getPlayer().getX() + 10;
            spawn("projectile", x, y);
        });
    }

    @Override

    protected void initPhysics() {
        new CollisionEvents().initphysics();
    }

    @Override
    protected void initUI() {
        new GUI();
    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("upgradeTokens", 0);
        vars.put("score", 0);
        vars.put("hp", 10);
        vars.put("fps", 0);
    }

    @Override
    protected void onUpdate(double tpf) {
        lastTime = System.nanoTime();
        Input input = getInput();
        getGameWorld().getSingleton(Entities.PLAYER).rotateToVector(input.getVectorToMouse(getGameWorld().getSingleton(Entities.PLAYER).getPosition()));

        if (autofire) {
            autofireCount++;
            if (autofireCount > stats.getReload()) {
                double y = getGameWorld().getSingleton(Entities.PLAYER).getY();
                double x = getGameWorld().getSingleton(Entities.PLAYER).getX();
                spawn("projectile", x - 10, y);
                autofireCount = 0;
            }
        }

        if (stats.getUpgradeScore() > 10) {
            stats.incUpgradeToken(1);
            FXGL.inc("upgradeTokens", 1);
            stats.incUpgradeScore(-(FXGL.geti("score") / 2));
        }

        boolean hasToken = (stats.getUpgradeTokens() > 0);



        int x = (int) stats.getPlayer().getX();
        int y = (int) stats.getPlayer().getY();


        Random r = new Random();
        if (r.nextInt(10000) == 2) {
            stats.spawnFood("enemy");
        }

        fps = (int) (1000000000 / (System.nanoTime() - lastTime));
        lastTime = System.nanoTime();
        // System.out.println("fps: " + fps);
    }
}
