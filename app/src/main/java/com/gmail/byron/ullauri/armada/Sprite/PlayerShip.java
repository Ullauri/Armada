package com.gmail.byron.ullauri.armada.sprite;

import com.gmail.byron.ullauri.armada.GameUtil;

import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;


public class PlayerShip extends ShootingShip {
    private static final double BULLET_DAMAGE = 5;
    private static final float BULLET_SIZE = 7;
    private static final int FIRE_RATE = 3;


    public PlayerShip(float shootingPointXOffSet, float shootingPointYOffSet, TiledTextureRegion textureRegion,
                      VertexBufferObjectManager vertexBufferObjectManager) {
        super((GameUtil.CAMERA_WIDTH) - textureRegion.getHeight(), (GameUtil.CAMERA_HEIGHT / 2) - (textureRegion.getWidth() / 2),
                shootingPointXOffSet, shootingPointYOffSet, textureRegion, vertexBufferObjectManager, FIRE_RATE);
    }

    @Override
    protected void onManagedUpdate(final float pSecondsElapsed) {
        super.update();

        super.onManagedUpdate(pSecondsElapsed);
    }

    // Dragging Player Ship
    @Override
    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
        this.setPosition(pSceneTouchEvent.getX() - this.getWidth() / 2, pSceneTouchEvent.getY() - this.getHeight() / 2);

        return true;
    }

    @Override
    public void shoot() {
        Bullet bullet = new Bullet(this.getShootingPointX(), this.getShootingPointY(), BULLET_SIZE, this.getVertexBufferObjectManager(), BULLET_DAMAGE, new Color(0, 255, 0));
        bullet.setVelocityX(-400);
        addBullet(bullet);
        gameUtil.attatchToScene(bullet);
    }

}