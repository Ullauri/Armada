package com.gmail.byron.ullauri.armada;

import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;

public class BurstShotEnemy extends EnemyShip {
    public static final double BULLET_DAMAGE = -10;
    public static final float[] BURST_DIRECTIONS = {-100, 100, 0};


    BurstShotEnemy(float x, float y, TiledTextureRegion textureRegion, VertexBufferObjectManager vertexBufferObjectManager, int fireRate) {
        super(x, y, textureRegion, vertexBufferObjectManager, fireRate);
    }

    @Override
    public void shoot() {
        int burstCount = (int) Math.ceil(GameUtil.nextFloat(BURST_DIRECTIONS.length - 1));

        for (int i = 0; i <= burstCount; i++) {
            Bullet bullet = new Bullet(this.getX(), this.getY(), 9, this.getVertexBufferObjectManager(), BULLET_DAMAGE, new Color(0, 0, 5));
            bullet.setVelocity(250, BURST_DIRECTIONS[i]);
            super.addBullet(bullet);
            GameUtil.attatchToScene(bullet);
        }
    }

}
