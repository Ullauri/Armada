package com.gmail.byron.ullauri.armada;

import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;


public class PlayerShip extends ShootingShip {
    public static final double BULLET_DAMAGE = -5;
    public static final int FIRE_RATE = 3;


    PlayerShip(float x, float y, TiledTextureRegion textureRegion, VertexBufferObjectManager vertexBufferObjectManager) {
        super(x, y, textureRegion, vertexBufferObjectManager, FIRE_RATE);
    }

    @Override
    public void shoot() {
        Bullet bullet = new Bullet(this.getX(), this.getY(), 7, this.getVertexBufferObjectManager(), BULLET_DAMAGE, new Color(0, 255, 0));
    }

    @Override
    protected void onManagedUpdate(final float pSecondsElapsed) {
        super.update();

        super.onManagedUpdate(pSecondsElapsed);
    }

}
