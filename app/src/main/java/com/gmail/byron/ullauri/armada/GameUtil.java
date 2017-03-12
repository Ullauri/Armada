package com.gmail.byron.ullauri.armada;


import org.andengine.engine.Engine.EngineLock;
import org.andengine.entity.IEntity;
import org.andengine.entity.scene.Scene;

public final class GameUtil {
    public static final int CAMERA_WIDTH = 720, CAMERA_HEIGHT = 480;
    private static int frameCount;
    private static int wave;
    private static Scene scene;
    private static EngineLock engineLock;


    private GameUtil() {
    }

    public static void initScene(Scene pScene, EngineLock pEngineLock) {
        scene = pScene;
        engineLock = pEngineLock;
        frameCount = 0;
        wave = 1;
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

    public static void update() {
        if (frameCount >= 1500) {
            frameCount = 0;
            wave++;
        } else {
            frameCount++;
        }
    }

    public static boolean inSync(int cycle) {
        return frameCount % cycle == 0;
    }

    public static boolean isOutOfBounds(float x, float y) {
        return (x < -10 || y < -10 || x > CAMERA_WIDTH + 10 || y > CAMERA_HEIGHT + 10);
    }


}
