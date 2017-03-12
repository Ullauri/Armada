package com.gmail.byron.ullauri.armada;

import org.andengine.engine.handler.physics.PhysicsHandler;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;


public final class Bullet extends Rectangle {


    Bullet(float x, float y, float size, VertexBufferObjectManager vertexBufferObjectManager, double damage, Color color) {
        this(x, y, size, size, vertexBufferObjectManager, damage, color);
    }

    Bullet(float x, float y, float width, float height, VertexBufferObjectManager vertexBufferObjectManager, double damage, Color color) {
        super(x, y, width, height, vertexBufferObjectManager);
        this.setColor(color);

        PhysicsHandler ph = new PhysicsHandler(this);
        this.registerUpdateHandler(ph);
    }

}
