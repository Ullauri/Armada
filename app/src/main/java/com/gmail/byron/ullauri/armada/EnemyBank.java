package com.gmail.byron.ullauri.armada;

import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.util.color.Color;

import java.util.ArrayList;
import java.util.List;


public class EnemyBank {
    public class Enemy {
        private final TiledTextureRegion textureRegion;
        private final float[] shootingPointOffSet;
        private final float scale;
        private final Color[] colors;

        Enemy(TiledTextureRegion textureRegion, float[] shootingPointOffSet, float scale, Color[] colors) {
            this.textureRegion = textureRegion;
            this.shootingPointOffSet = shootingPointOffSet;
            this.scale = scale;
            this.colors = colors;
        }

        public TiledTextureRegion getTextureRegion() {
            return textureRegion;
        }

        public float[] getShootingPointOffSet() {
            return shootingPointOffSet;
        }

        public float getScale() {
            return scale;
        }

        public Color[] getColors() {
            return colors;
        }

        public Color getColor(int colorIndex) {
            return colors[colorIndex];
        }

    }

    private final List<Enemy> enemies;


    EnemyBank() {
        enemies = new ArrayList<>();
    }

    public void add(TiledTextureRegion enemyRegion, float[] enemyShootingPointOffSet, float scale, Color[] colors) {
        enemies.add(new Enemy(enemyRegion, enemyShootingPointOffSet, scale, colors));
    }

    public void remove(TiledTextureRegion enemyRegion) {
        int removeIndex = -1;

        for (int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i).getTextureRegion() == enemyRegion) {
                removeIndex = i;
                break;
            }
        }

        if (removeIndex > -1) {
            enemies.remove(removeIndex);
        }
    }

    public Enemy get(int index) {
        return enemies.get(index);
    }

    public int size() {
        return enemies.size();
    }

}
