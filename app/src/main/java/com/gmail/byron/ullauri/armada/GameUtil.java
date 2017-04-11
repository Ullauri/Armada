package com.gmail.byron.ullauri.armada;


import com.gmail.byron.ullauri.armada.sprite.PlayerShip;

import org.andengine.engine.Engine.EngineLock;
import org.andengine.entity.IEntity;
import org.andengine.entity.scene.Scene;

import java.util.Random;

public final class GameUtil {
    public static final int CAMERA_WIDTH = 720, CAMERA_HEIGHT = 480;
    private static final int[] ENEMIES_PER_WAVE = {
            3,
            5,
            7
    };
    private static Random random = new Random();
    private static int frameCount, enemyCount;
    private static int wave;
    private static PlayerShip player;
    private static Scene scene;
    private static EngineLock engineLock;


    private GameUtil() {
    }

    public static void initScene(Scene pScene, EngineLock pEngineLock, PlayerShip pPlayer) {
        scene = pScene;
        engineLock = pEngineLock;
        player = pPlayer;
        frameCount = 0;
        enemyCount = 0;
        wave = 0;
    }

    public static EngineLock getEngineLock() {
        return engineLock;
    }

    public static void attatchToScene(IEntity entity) {
        scene.attachChild(entity);
    }

    public static void detachFromScene(IEntity entity) {
        scene.detachChild(entity);
    }

    public static float[] getPlayerPosition() {
        float[] position = new float[2];
        position[0] = player.getX();
        position[1] = player.getY();

        return position;
    }

    public static void update(int pEnemyCount) {
        if (frameCount >= 1500) {
            frameCount = 0;
            if (wave < ENEMIES_PER_WAVE.length) wave++;
        } else
            frameCount++;

        enemyCount = pEnemyCount;
    }

    public static int nextInt(int bound) {
        return random.nextInt(bound);
    }

    public static float nextFloat(float bound) {
        return random.nextFloat() * bound;
    }

    public static boolean inSync(int cycle) {
        return frameCount % cycle == 0;
    }

    public static boolean isOutOfBounds(float x, float y) {
        return (x < 0 || y < 0 || x > CAMERA_WIDTH || y > CAMERA_HEIGHT);
    }

    public static boolean enemyShortage() {
        return enemyCount < ENEMIES_PER_WAVE[wave];
    }
}