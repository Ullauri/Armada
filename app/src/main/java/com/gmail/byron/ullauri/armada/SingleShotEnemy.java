package com.gmail.byron.ullauri.armada;

import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;

public class SingleShotEnemy extends EnemyShip {
    public static final double BULLET_DAMAGE = -5;


    SingleShotEnemy(float x, float y, TiledTextureRegion textureRegion,
                    VertexBufferObjectManager vertexBufferObjectManager, int fireRate) {
        super(x, y, textureRegion, vertexBufferObjectManager, fireRate);
    }

    private float[] aim() {
        float[] aimXY = new float[2];
        float[] playerXY = GameUtil.getPlayerPosition();

        aimXY[0] = playerXY[0] - this.getX();
        aimXY[1] = playerXY[1] - this.getY();

        return aimXY;
    }

    @Override
    public void shoot() {
        Bullet bullet = new Bullet(this.getX(), this.getY(), 9, this.getVertexBufferObjectManager(), BULLET_DAMAGE, new Color(255, 0, 0));
        float[] target = aim();
        bullet.setVelocity(target[0], target[0]);
        super.addBullet(bullet);
        GameUtil.attatchToScene(bullet);
    }

}