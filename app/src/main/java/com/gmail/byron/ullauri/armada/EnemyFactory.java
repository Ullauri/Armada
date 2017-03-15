package com.gmail.byron.ullauri.armada;

import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class EnemyFactory {
    private static final int X_OFFSET = 20;
    private static final int Y_OFFSET = 30;
    private static final int VELOCITY_X_LIMIT = 125;
    private static final int VELOCITY_Y_LIMIT = 125;
    private static final int SINGLE_SHOT_FR_LIMIT = 115;
    private static final int BURST_SHOT_FR_LIMIT = 125;

    private EnemyFactory() {
    }

    public static EnemyShip createEnemy(TiledTextureRegion enemyFaceTextureRegion, VertexBufferObjectManager vertexBufferObjectManager) {
        float x = -GameUtil.nextFloat(X_OFFSET) - X_OFFSET;
        float y = GameUtil.nextFloat(GameUtil.CAMERA_HEIGHT - Y_OFFSET) + (Y_OFFSET - 10);
        int velocityX = GameUtil.nextInt(VELOCITY_X_LIMIT);
        int velocityY;
        int fireRate;

        EnemyShip enemyShip;

        if (GameUtil.nextInt(100) % 4 == 0) {
            velocityY = 0;
            fireRate = GameUtil.nextInt(BURST_SHOT_FR_LIMIT);
            enemyShip = new BurstShotEnemy(x, y, enemyFaceTextureRegion, vertexBufferObjectManager, fireRate);
        } else {
            velocityY = GameUtil.nextInt(VELOCITY_Y_LIMIT);
            velocityY = (velocityY % 3 == 0) ? velocityY * -1 : velocityY;
            fireRate = GameUtil.nextInt(SINGLE_SHOT_FR_LIMIT);
            enemyShip = new SingleShotEnemy(x, y, enemyFaceTextureRegion, vertexBufferObjectManager, fireRate);
        }

        enemyShip.setVelocity(velocityX, velocityY);

        return enemyShip;
    }

}
