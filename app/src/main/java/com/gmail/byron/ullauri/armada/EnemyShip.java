package com.gmail.byron.ullauri.armada;

import org.andengine.engine.handler.physics.PhysicsHandler;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public abstract class EnemyShip extends ShootingShip {
    private PhysicsHandler physicsHandler;
    private final float ORIGIN_X, ORIGIN_Y;


    EnemyShip(float x, float y, TiledTextureRegion textureRegion,
              VertexBufferObjectManager vertexBufferObjectManager, int fireRate) {
        super(x, y, textureRegion, vertexBufferObjectManager, fireRate);
        ORIGIN_X = x;
        ORIGIN_Y = y;

        physicsHandler = new PhysicsHandler(this);
        this.registerUpdateHandler(physicsHandler);
    }

    public void setVelocityX(float velocityX) {
        physicsHandler.setVelocityX(velocityX);
    }

    public void setVelocity(float velocityX, float velocityY) {
        physicsHandler.setVelocity(velocityX, velocityY);
    }

    public void update() {
        super.update();

        if (this.getY() <= 0 || this.getY() + this.getHeight() > GameUtil.CAMERA_HEIGHT) {
            float negateDY = -this.physicsHandler.getVelocityY();
            this.physicsHandler.setVelocityY(negateDY);
        }

        if (this.getX() > GameUtil.CAMERA_WIDTH) {
            this.setPosition(ORIGIN_X, ORIGIN_Y);
            physicsHandler.setVelocity(physicsHandler.getVelocityX() - 5,
                    physicsHandler.getVelocityY() - 5);
            super.setHp(100);
        }
    }

    @Override
    public String toString() {
        return super.toString()
                + "\ndX: " + physicsHandler.getVelocityX()
                + "\ndY: " + physicsHandler.getVelocityY();
    }
}