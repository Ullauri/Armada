package com.gmail.byron.ullauri.armada.sprite;

import com.gmail.byron.ullauri.armada.GameUtil;

import org.andengine.engine.Engine;
import org.andengine.engine.handler.physics.PhysicsHandler;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public abstract class EnemyShip extends ShootingShip {
    public static final double CRASH_DAMAGE = -15;
    private final PhysicsHandler physicsHandler;
    private final float ORIGIN_X, ORIGIN_Y;


    EnemyShip(float x, float y, float shootingPointXOffSet, float shootingPointYOffSet, TiledTextureRegion textureRegion,
              VertexBufferObjectManager vertexBufferObjectManager, int fireRate) {
        super(x, y, shootingPointXOffSet, shootingPointYOffSet, textureRegion, vertexBufferObjectManager, fireRate);
        ORIGIN_X = x;
        ORIGIN_Y = y;

        physicsHandler = new PhysicsHandler(this);
        this.registerUpdateHandler(physicsHandler);
    }

    public void setVelocityX(float velocityX) {
        physicsHandler.setVelocityX(velocityX);
    }

    public void setVelocityY(float velocityY) {
        physicsHandler.setVelocityY(velocityY);
    }

    public void setVelocity(float velocityX, float velocityY) {
        physicsHandler.setVelocity(velocityX, velocityY);
    }

    public void destroyed() {
        final Engine.EngineLock engineLock = GameUtil.getEngineLock();
        engineLock.lock();

        GameUtil.detachFromScene(this);
        dispose();

        engineLock.unlock();
    }

    public void update() {
        super.update();

        if (this.getY() < 0 || this.getY() + this.getWidth() > GameUtil.CAMERA_HEIGHT) {
            float negateDY = -this.physicsHandler.getVelocityY();
            this.physicsHandler.setVelocityY(negateDY);
        }

        if (this.getX() > GameUtil.CAMERA_WIDTH) {
            setPosition(ORIGIN_X, ORIGIN_Y);
            physicsHandler.setVelocity(physicsHandler.getVelocityX() - 5,
                    physicsHandler.getVelocityY() - 5);
            setHp(100);
        }
    }

    @Override
    protected void onManagedUpdate(final float pSecondsElapsed) {
        update();

        super.onManagedUpdate(pSecondsElapsed);
    }

    @Override
    public String toString() {
        return super.toString()
                + "\ndX: " + physicsHandler.getVelocityX()
                + "\ndY: " + physicsHandler.getVelocityY();
    }

}