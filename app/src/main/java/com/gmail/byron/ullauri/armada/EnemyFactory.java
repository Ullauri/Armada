package com.gmail.byron.ullauri.armada;

import com.gmail.byron.ullauri.armada.sprite.BurstShotEnemy;
import com.gmail.byron.ullauri.armada.sprite.EnemyShip;
import com.gmail.byron.ullauri.armada.sprite.SingleShotEnemy;

import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;

public final class EnemyFactory {
    private static final int X_OFFSET = 235, Y_OFFSET = 150;
    private static final int VELOCITY_X_MIN = 50, VELOCITY_X_MAX = 125;
    private static final int VELOCITY_Y_MIN = 50, VELOCITY_Y_MAX = 125;
    private static final int SINGLE_SHOT_FR_MIN = 50, SINGLE_SHOT_FR_MAX = 115;
    private static final int BURST_SHOT_FR_MIN = 50, BURST_SHOT_FR_MAX = 125;


    private EnemyFactory() {
    }

    public static EnemyShip createEnemy(float shootingPointXOffSet, float shootingPointYOffSet,
                                        TiledTextureRegion enemyFaceTextureRegion, VertexBufferObjectManager vertexBufferObjectManager,
                                        float scale, Color color) {
        float x = -GameUtil.nextFloat(X_OFFSET) - X_OFFSET;
        float y = GameUtil.nextFloat(GameUtil.CAMERA_HEIGHT - Y_OFFSET);
        int velocityX = GameUtil.nextInt(VELOCITY_X_MAX - VELOCITY_X_MIN) + VELOCITY_X_MIN;
        int velocityY;
        int fireRate;

        EnemyShip enemyShip;

        if (GameUtil.nextInt(100) % 4 == 0) {
            velocityY = 0;
            fireRate = GameUtil.nextInt(BURST_SHOT_FR_MAX - BURST_SHOT_FR_MIN) + BURST_SHOT_FR_MIN;
            enemyShip = new BurstShotEnemy(x, y, shootingPointXOffSet,
                    shootingPointYOffSet, enemyFaceTextureRegion, vertexBufferObjectManager, fireRate);
        } else {
            velocityY = GameUtil.nextInt(VELOCITY_Y_MAX - VELOCITY_Y_MIN) + VELOCITY_Y_MIN;
            velocityY = (velocityY % 3 == 0) ? velocityY * -1 : velocityY;
            fireRate = GameUtil.nextInt(SINGLE_SHOT_FR_MAX - SINGLE_SHOT_FR_MIN) + SINGLE_SHOT_FR_MIN;
            enemyShip = new SingleShotEnemy(x, y, shootingPointXOffSet,
                    shootingPointYOffSet, enemyFaceTextureRegion, vertexBufferObjectManager, fireRate);
        }

        enemyShip.setVelocity(velocityX, velocityY);
        enemyShip.setScale(scale);
        enemyShip.setColor(color);
        enemyShip.setRotation(-90);

        return enemyShip;
    }
}
