package com.gmail.byron.ullauri.armada.sprite;

import com.gmail.byron.ullauri.armada.GameUtil;

import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;

public class SingleShotEnemy extends EnemyShip {
    public static final double BULLET_DAMAGE = 5;


    public SingleShotEnemy(float x, float y, float shootingPointXOffSet, float shootingPointYOffSet, TiledTextureRegion textureRegion,
                           VertexBufferObjectManager vertexBufferObjectManager, int fireRate) {
        super(x, y, shootingPointXOffSet, shootingPointYOffSet, textureRegion, vertexBufferObjectManager, fireRate);
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
        Bullet bullet = new Bullet(this.getShootingPointX(), this.getShootingPointY(), 9, this.getVertexBufferObjectManager(), BULLET_DAMAGE, new Color(0, 255, 0));
        float[] target = aim();
        bullet.setVelocity(target[0], target[1]);
//        bullet.setVelocityX(300);
        addBullet(bullet);
        GameUtil.attatchToScene(bullet);
    }

}