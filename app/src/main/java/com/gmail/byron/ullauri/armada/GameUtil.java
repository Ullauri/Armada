package com.gmail.byron.ullauri.armada;


import com.gmail.byron.ullauri.armada.sprite.PlayerShip;

import org.andengine.engine.Engine.EngineLock;
import org.andengine.entity.IEntity;
import org.andengine.entity.scene.Scene;

import java.util.Random;

public enum GameUtil {
    /*
        Must be initialized in a SimpleBaseGameActivity class (MainGameActivity) after a LevelScene
        is created in onCreateScene.
     */
    INSTANCE;

    public static final int CAMERA_WIDTH = 720, CAMERA_HEIGHT = 480;
    private static final int[] ENEMIES_PER_WAVE = {
            3,
            5,
            7
    };
    private static Random RANDOM = new Random();

    public static int nextInt(int bound) {
        return RANDOM.nextInt(bound);
    }

    public static float nextFloat(float bound) {
        return RANDOM.nextFloat() * bound;
    }

    private int frameCount, enemyCount;
    private int wave;
    private PlayerShip player;
    private Scene scene;
    private EngineLock engineLock;

    public void initScene(Scene pScene, EngineLock pEngineLock, PlayerShip pPlayer) {
        scene = pScene;
        engineLock = pEngineLock;
        player = pPlayer;
        frameCount = 0;
        enemyCount = 0;
        wave = 0;
    }

    public EngineLock getEngineLock() {
        return engineLock;
    }

    public void attatchToScene(IEntity entity) {
        scene.attachChild(entity);
    }

    public void detachFromScene(IEntity entity) {
        scene.detachChild(entity);
    }

    public float[] getPlayerPosition() {
        float[] position = new float[2];
        position[0] = player.getX();
        position[1] = player.getY();

        return position;
    }

    public void update(int pEnemyCount) {
        if (frameCount >= 1500) {
            frameCount = 0;
            if (wave < ENEMIES_PER_WAVE.length) wave++;
        } else
            frameCount++;

        enemyCount = pEnemyCount;
    }

    public boolean inSync(int cycle) {
        return frameCount % cycle == 0;
    }

    public boolean isOutOfBounds(float x, float y) {
        return (x < 0 || y < 0 || x > CAMERA_WIDTH || y > CAMERA_HEIGHT);
    }

    public boolean enemyShortage() {
        return enemyCount < ENEMIES_PER_WAVE[wave];
    }
}