package com.gmail.byron.ullauri.armada;

import com.gmail.byron.ullauri.armada.sprite.Bullet;
import com.gmail.byron.ullauri.armada.sprite.EnemyShip;
import com.gmail.byron.ullauri.armada.sprite.PlayerShip;

import org.andengine.engine.Engine;
import org.andengine.entity.scene.Scene;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class LevelScene extends Scene {
    private final GameUtil gameUtil = GameUtil.INSTANCE;
    private final int ENEMY_SPAWN_CYCLE = 40;
    private final int REMAINING_BULLET_DAMAGE = 5;
    private final VertexBufferObjectManager vertexBufferObjectManager;
    private final PlayerShip playerShip;
    private final EnemyBank enemyBank;
    private final List<EnemyShip> enemyShips;
    private final List<Bullet> remainingBullets;


    LevelScene(VertexBufferObjectManager vertexBufferObjectManager, PlayerShip playerShip, EnemyBank enemyBank) {
        this.vertexBufferObjectManager = vertexBufferObjectManager;
        this.playerShip = playerShip;
        attachChild(this.playerShip);
        registerTouchArea(this.playerShip);

        this.enemyBank = enemyBank;
        enemyShips = new ArrayList<>();
        remainingBullets = new ArrayList<>();

        setTouchAreaBindingOnActionDownEnabled(true);
    }

    @Override
    protected void onManagedUpdate(final float pSecondsElapsed) {
        gameUtil.update(enemyShips.size());

        if (gameUtil.inSync(ENEMY_SPAWN_CYCLE) && gameUtil.enemyShortage()) {
            EnemyBank.Enemy enemy = enemyBank.get(GameUtil.nextInt(enemyBank.size()));
            TiledTextureRegion textureRegion = enemy.getTextureRegion();
            float[] shootingPointOffSet = enemy.getShootingPointOffSet();
            float scale = enemy.getScale();
            Color color = enemy.getColor(GameUtil.nextInt(enemy.getColors().length));

            EnemyShip enemyShip = EnemyFactory.createEnemy(shootingPointOffSet[0], shootingPointOffSet[1], textureRegion,
                    vertexBufferObjectManager, scale, color);
            enemyShips.add(enemyShip);
            attachChild(enemyShip);
        }

        Iterator<EnemyShip> enemyShipsIterator = enemyShips.iterator();

        while (enemyShipsIterator.hasNext()) {
            EnemyShip enemyShip = enemyShipsIterator.next();

            if (!gameUtil.isOutOfBounds(enemyShip.getX(), enemyShip.getY())) {
                if (playerShip.collidesWith(enemyShip)) {
                    playerShip.hit(EnemyShip.CRASH_DAMAGE);

                    remainingBullets.addAll(enemyShip.getBullets());
                    enemyShip.destroyed();
                    enemyShipsIterator.remove();
                }

                Iterator<Bullet> bulletsIterator = enemyShip.getBullets().iterator();

                while (bulletsIterator.hasNext()) {
                    Bullet enemyBullet = bulletsIterator.next();

                    if (playerShip.collidesWith(enemyBullet)) {
                        playerShip.hit(enemyBullet.getDamage());
                        enemyShip.removeBullet(enemyBullet);
                        bulletsIterator.remove();
                    }
                }

                bulletsIterator = playerShip.getBullets().iterator();

                while (bulletsIterator.hasNext()) {
                    Bullet playerBullet = bulletsIterator.next();

                    if (enemyShip.collidesWith(playerBullet)) {
                        enemyShip.hit(playerBullet.getDamage());
                        playerShip.removeBullet(playerBullet);
                        bulletsIterator.remove();
                    }
                }

                bulletsIterator = remainingBullets.iterator();

                while (bulletsIterator.hasNext()) {
                    Bullet remainingBullet = bulletsIterator.next();

                    if (playerShip.collidesWith(remainingBullet)) {
                        playerShip.hit(REMAINING_BULLET_DAMAGE);
                        final Engine.EngineLock engineLock = gameUtil.getEngineLock();
                        engineLock.lock();

                        detachChild(remainingBullet);

                        engineLock.unlock();
                        bulletsIterator.remove();
                    }
                }

                if (playerShip.getHp() <= 0) {
                    // End of game
                    playerShip.setColor(Color.RED);
                }

                if (enemyShip.getHp() <= 0) {
                    remainingBullets.addAll(enemyShip.getBullets());
                    enemyShip.destroyed();
                    enemyShipsIterator.remove();
                }
            }
        }


        super.onManagedUpdate(pSecondsElapsed);
    }

}