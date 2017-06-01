package com.gmail.byron.ullauri.armada.sprite;

import com.gmail.byron.ullauri.armada.GameUtil;

import org.andengine.engine.Engine.EngineLock;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import java.util.ArrayList;
import java.util.List;


public abstract class ShootingShip extends AnimatedSprite {
    protected GameUtil gameUtil = GameUtil.INSTANCE;
    private final int FIRE_RATE;
    // Positions bullets when shooting
    private float shootingPointXOffSet, shootingPointYOffSet;
    private final List<Bullet> bullets;
    private double hp = 100;


    ShootingShip(float x, float y, float shootingPointXOffSet, float shootingPointYOffSet, TiledTextureRegion textureRegion,
                 VertexBufferObjectManager vertexBufferObjectManager, int fireRate) {
        super(x, y, textureRegion, vertexBufferObjectManager);
        FIRE_RATE = fireRate;
        setShootingPointOffSet(shootingPointXOffSet, shootingPointYOffSet);
        this.bullets = new ArrayList<>();
    }

    public void setShootingPointOffSet(float x, float y) {
        shootingPointXOffSet = x;
        shootingPointYOffSet = y;
    }

    public float getShootingPointX() {
        return this.getX() + shootingPointXOffSet;
    }

    public float getShootingPointY() {
        return this.getY() + shootingPointYOffSet;
    }

    public void setHp(double hp) {
        this.hp = hp;
    }

    public double getHp() {
        return hp;
    }

    public void hit(double damage) {
        hp -= damage;
    }

    public List<Bullet> getBullets() {
        return bullets;
    }

    public void addBullet(Bullet bullet) {
        bullets.add(bullet);
    }

    public void removeBullet(Bullet bullet) {
        final EngineLock engineLock = gameUtil.getEngineLock();
        engineLock.lock();

        gameUtil.detachFromScene(bullet);

        engineLock.unlock();
    }

    public void update() {
        if (!gameUtil.isOutOfBounds(this.getX(), this.getY())) {
            if (gameUtil.inSync(FIRE_RATE)) {
                shoot();

                Bullet furthestBullet = bullets.get(0);
                if (gameUtil.isOutOfBounds(furthestBullet.getX(), furthestBullet.getY()))
                    removeBullet(furthestBullet);
            }
        }
    }

    @Override
    public String toString() {
        return "Fire Rate: " + FIRE_RATE
                + "\nHP: " + hp
                + "\nBullets: " + bullets.toString()
                + "\nX: " + this.getX()
                + "\nY: " + this.getY();
    }

    public abstract void shoot();
}