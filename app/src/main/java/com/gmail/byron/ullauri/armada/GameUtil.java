package com.gmail.byron.ullauri.armada;

import org.andengine.engine.Engine.EngineLock;
import org.andengine.entity.scene.Scene;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import java.util.Random;

/**
 * Created by ullauri on 2/9/17.
 *
 */


public final class GameUtil {
    private static final Random RANDOM = new Random(System.nanoTime());
    private static Scene scene;
    private static EngineLock engineLock;
    private static VertexBufferObjectManager vertexBufferObjectManager;
    private static int cycleCount = 0, wave = 1, enemyCount;

    private GameUtil() {
    }

    public static int getRandomInt(int n) {
        return RANDOM.nextInt(n);
    }


}
