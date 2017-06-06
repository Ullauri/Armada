package com.gmail.byron.ullauri.armada.sprite;

import com.gmail.byron.ullauri.armada.GameUtil;

import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;

public class BurstShotEnemy extends EnemyShip {
    public static final double BULLET_DAMAGE = 10;
    // First two indexes: /\ burst shot
    // 0 is placed last for the middle bullet in the /|\ burst shot
    public static final float[] BURST_DIRECTIONS_Y = {-80, 80, 0};


    public BurstShotEnemy(float x, float y, float shootingPointXOffSet, float shootingPointYOffSet, TiledTextureRegion textureRegion, VertexBufferObjectManager vertexBufferObjectManager, int fireRate) {
        super(x, y, shootingPointXOffSet, shootingPointYOffSet, textureRegion, vertexBufferObjectManager, fireRate);
    }

    @Override
    public void shoot() {
        int burstCount = GameUtil.nextInt(BURST_DIRECTIONS_Y.length - 1);
        if (burstCount == 0) burstCount++;

        for (int i = 0; i <= burstCount; i++) {
            Bullet bullet = new Bullet(this.getShootingPointX(), this.getShootingPointY(), 9, this.getVertexBufferObjectManager(), BULLET_DAMAGE, new Color(0, 0, 5));
            bullet.setVelocity(250, BURST_DIRECTIONS_Y[i]);
            addBullet(bullet);
            gameUtil.attatchToScene(bullet);
        }
    }
}
