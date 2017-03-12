package com.gmail.byron.ullauri.armada;

import org.andengine.engine.Engine.EngineLock;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public abstract class ShootingShip extends AnimatedSprite {
    private final int FIRE_RATE;
    private final List<Bullet> bullets;
    private double hp = 100;


    ShootingShip(float x, float y, TiledTextureRegion textureRegion,
                 VertexBufferObjectManager vertexBufferObjectManager, int fireRate) {
        super(x, y, textureRegion, vertexBufferObjectManager);
        FIRE_RATE = fireRate;
        this.bullets = new ArrayList<>();
    }

    public double getHp() {
        return hp;
    }

    public List<Bullet> getBullets() {
        return Collections.unmodifiableList(bullets);
    }

    public void addBullet(Bullet bullet) {
        bullets.add(bullet);
    }

    public void removeBullet(Bullet bullet) {
        final EngineLock engineLock = GameUtil.getEngineLock();
        engineLock.lock();

        bullets.remove(bullet);
        GameUtil.detachFromScene(bullet);
        bullet.dispose();

        engineLock.unlock();
    }

    public void shot(double bulletDamage) {
        hp -= bulletDamage;
    }

    public void update() {
        if (GameUtil.inSync(FIRE_RATE)) {
            shoot();

            Bullet furthestBullet = bullets.get(0);
            if (GameUtil.isOutOfBounds(furthestBullet.getX(), furthestBullet.getY()))
                removeBullet(furthestBullet);
        }
    }

    public abstract void shoot();

}