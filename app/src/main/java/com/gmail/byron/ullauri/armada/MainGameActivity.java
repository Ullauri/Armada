
package com.gmail.byron.ullauri.armada;


import org.andengine.engine.Engine.EngineLock;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.util.FPSLogger;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.ui.activity.SimpleBaseGameActivity;

import java.util.ArrayList;
import java.util.List;

public class MainGameActivity extends SimpleBaseGameActivity {
    private TiledTextureRegion playerFaceTextureRegion, enemyFaceTextureRegion;
    private PlayerShip playerShip;
    private final List<EnemyShip> enemyShips = new ArrayList<>();


    @Override
    public EngineOptions onCreateEngineOptions() {
        final Camera camera = new Camera(0, 0, GameUtil.CAMERA_WIDTH, GameUtil.CAMERA_HEIGHT);

        return new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED,
                new RatioResolutionPolicy(GameUtil.CAMERA_WIDTH, GameUtil.CAMERA_HEIGHT), camera);
    }

    @Override
    protected void onCreateResources() {


    }

    @Override
    protected Scene onCreateScene() {
        this.mEngine.registerUpdateHandler(new FPSLogger());
        final EngineLock engineLock = mEngine.getEngineLock();
        final VertexBufferObjectManager vertexBufferObjectManager = this.getVertexBufferObjectManager();
        final Scene mainLevel = new MainLevel();

        GameUtil.initScene(mainLevel, engineLock, playerShip);
        return new Scene();
    }

    private class MainLevel extends Scene {

        @Override
        protected void onManagedUpdate(final float pSecondsElapsed) {

            super.onManagedUpdate(pSecondsElapsed);
        }

    }

}
