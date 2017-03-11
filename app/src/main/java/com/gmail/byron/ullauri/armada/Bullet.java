package com.gmail.byron.ullauri.armada;

import org.andengine.engine.handler.physics.PhysicsHandler;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;


public final class Bullet extends Rectangle {
    private final double DAMAGE;

    Bullet(float x, float y, VertexBufferObjectManager vertexBufferObjectManager) {
        this(x, y, 8, 8, vertexBufferObjectManager, -5, new Color(255, 0, 0));
    }

    Bullet(float x, float y, float width, float height, VertexBufferObjectManager vertexBufferObjectManager, double damage, Color color) {
        super(x, y, width, height, vertexBufferObjectManager);
        this.setColor(color);
        DAMAGE = damage;

        PhysicsHandler ph = new PhysicsHandler(this);
        this.registerUpdateHandler(ph);
    }

    public double getDAMAGE() {
        return DAMAGE;
    }

}
