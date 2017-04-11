package com.gmail.byron.ullauri.armada.sprite;

import com.gmail.byron.ullauri.armada.GameUtil;

import org.andengine.engine.Engine.EngineLock;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import java.util.ArrayList;
import java.util.List;


public abstract class ShootingShip extends AnimatedSprite {
    private final int FIRE_RATE;
    private final List<Bullet> bullets;
    private double hp = 100;
    private float shootingPointXOffSet, shootingPointYOffSet;


    public ShootingShip(float x, float y, float shootingPointXOffSet, float shootingPointYOffSet, TiledTextureRegion textureRegion,
                        VertexBufferObjectManager vertexBufferObjectManager, int fireRate) {
        super(x, y, textureRegion, vertexBufferObjectManager);
        this.shootingPointXOffSet = shootingPointXOffSet;
        this.shootingPointYOffSet = shootingPointYOffSet;
        FIRE_RATE = fireRate;
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

//    public void shot(double bulletDamage) {
//        hp -= bulletDamage;
//    }

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
        final EngineLock engineLock = GameUtil.getEngineLock();
        engineLock.lock();

        GameUtil.detachFromScene(bullet);
//        bullet.dispose();

        engineLock.unlock();
    }

    public void update() {
        if (GameUtil.inSync(FIRE_RATE)) {
            shoot();

            Bullet furthestBullet = bullets.get(0);
            if (GameUtil.isOutOfBounds(furthestBullet.getX(), furthestBullet.getY()))
                removeBullet(furthestBullet);

//            System.out.println("BULLETS SIZE: " + getBullets().size());
        }
    }

    public abstract void shoot();

    @Override
    public String toString() {
        return "Fire Rate: " + FIRE_RATE
                + "\nHP: " + hp
                + "\nBullets: " + bullets.toString()
                + "\nX: " + this.getX()
                + "\nY: " + this.getY();
    }

}