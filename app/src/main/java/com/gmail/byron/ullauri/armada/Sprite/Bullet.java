package com.gmail.byron.ullauri.armada.sprite;

import org.andengine.engine.handler.physics.PhysicsHandler;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;


public final class Bullet extends Rectangle {
    private double damage;
    private PhysicsHandler physicsHandler;


    Bullet(float x, float y, float size, VertexBufferObjectManager vertexBufferObjectManager, double damage, Color color) {
        this(x, y, size, size, vertexBufferObjectManager, damage, color);
    }

    Bullet(float x, float y, float width, float height, VertexBufferObjectManager vertexBufferObjectManager, double damage, Color color) {
        super(x, y, width, height, vertexBufferObjectManager);
        setDamage(damage);
        setColor(color);

        physicsHandler = new PhysicsHandler(this);
        registerUpdateHandler(physicsHandler);
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public double getDamage() {
        return damage;
    }

    public void setVelocityX(float velocityX) {
        physicsHandler.setVelocityX(velocityX);
    }

    public void setVelocity(float velocityX, float velocityY) {
        physicsHandler.setVelocity(velocityX, velocityY);
    }

    @Override
    public String toString() {
        return "X: " + this.getX() + " Y: " + this.getY()
                + " dX: " + physicsHandler.getVelocityX()
                + " dY: " + physicsHandler.getVelocityY();
    }

}