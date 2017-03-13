package com.gmail.byron.ullauri.armada;


import org.andengine.engine.handler.physics.PhysicsHandler;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import java.util.concurrent.ThreadLocalRandom;

public abstract class EnemyShip extends ShootingShip {
    private PhysicsHandler mPhysicsHandler;

    EnemyShip(float x, float y, TiledTextureRegion textureRegion,
              VertexBufferObjectManager vertexBufferObjectManager, int fireRate) {
        super(x, y, textureRegion, vertexBufferObjectManager, fireRate);

        mPhysicsHandler = new PhysicsHandler(this);
        this.registerUpdateHandler(mPhysicsHandler);
    }

    public void setVelocityX(float velocityX) {
        mPhysicsHandler.setVelocityX(velocityX);
    }

    public void setVelocity(float velocityX, float velocityY) {
        mPhysicsHandler.setVelocity(velocityX, velocityY);
    }

    public void update() {
        super.update();

        if (this.getY() <= 0 || this.getY() + this.getHeight() > GameUtil.CAMERA_HEIGHT) {
            float negateDY = -this.mPhysicsHandler.getVelocityY();
            this.mPhysicsHandler.setVelocityY(negateDY);
        }

        if (this.getX() > GameUtil.CAMERA_WIDTH) {
            float randomX = GameUtil.nextFloat(GameUtil.CAMERA_WIDTH);
            float randomY = GameUtil.nextFloat(GameUtil.CAMERA_HEIGHT);
            this.setPosition(randomX, randomY);
            super.setHp(100);
        }
    }

    public abstract void aim();

}