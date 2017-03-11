package com.gmail.byron.ullauri.armada;

import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import java.util.ArrayList;
import java.util.List;


public abstract class ShootingShip extends AnimatedSprite {
    private final List<Bullet> bullets;
    private double hp;
    private int fireRate;


    ShootingShip(float x, float y, TiledTextureRegion textureRegion, VertexBufferObjectManager vertexBufferObjectManager,
                 int fireRate) {
        super(x, y, textureRegion, vertexBufferObjectManager);
        this.fireRate = fireRate;

        hp = 100;
        this.bullets = new ArrayList<>();
    }

    public void update() {

    }

    public void shot(double bulletDamage) {
        hp -= bulletDamage;
    }

    public abstract void shoot();

}
