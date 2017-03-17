package com.gmail.byron.ullauri.armada;

import org.andengine.engine.Engine.EngineLock;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.util.FPSLogger;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.ui.activity.SimpleBaseGameActivity;

import java.util.ArrayList;
import java.util.List;

public class MainGameActivity extends SimpleBaseGameActivity {
    private Scene scene;
    private BitmapTextureAtlas playerShipTextureAtlas, enemyTextureAtlas;
    private TiledTextureRegion playerShipTextureRegion, enemyTextureRegion;


    @Override
    public EngineOptions onCreateEngineOptions() {
        final Camera camera = new Camera(0, 0, GameUtil.CAMERA_WIDTH, GameUtil.CAMERA_HEIGHT);

        return new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED,
                new RatioResolutionPolicy(GameUtil.CAMERA_WIDTH, GameUtil.CAMERA_HEIGHT), camera);
    }

    @Override
    protected void onCreateResources() {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");

        playerShipTextureAtlas = new BitmapTextureAtlas(this.getTextureManager(), 256, 128,
                TextureOptions.BILINEAR);
        playerShipTextureRegion = BitmapTextureAtlasTextureRegionFactory
                .createTiledFromAsset(playerShipTextureAtlas, this, "red_ship.png", 0, 0, 2, 1);
        playerShipTextureAtlas.load();

    }

    @Override
    protected Scene onCreateScene() {
        this.mEngine.registerUpdateHandler(new FPSLogger());
        EngineLock engineLock = mEngine.getEngineLock();
        VertexBufferObjectManager vertexBufferObjectManager = this.getVertexBufferObjectManager();

        float playerX = (GameUtil.CAMERA_WIDTH) - playerShipTextureRegion.getHeight();
        float playerY = (GameUtil.CAMERA_HEIGHT / 2) - (playerShipTextureRegion.getWidth() / 2);
        PlayerShip playerShip = new PlayerShip(playerX, playerY, playerShipTextureRegion,
                vertexBufferObjectManager);
        playerShip.setRotation(-90);
        playerShip.setScale(.65f);

        scene = new TestLevel(playerShip);
        GameUtil.initScene(scene, engineLock, playerShip);
        scene.setBackground(new Background(0.09804f, 0.6274f, 0.8784f));

        return scene;
    }

    private class TestLevel extends Scene {
        private PlayerShip playerShip;
        private final List<EnemyShip> enemyShips = new ArrayList<>();


        TestLevel(PlayerShip playerShip) {
            this.playerShip = playerShip;

            attachChild(this.playerShip);
            registerTouchArea(this.playerShip);
            setTouchAreaBindingOnActionDownEnabled(true);
        }

        @Override
        protected void onManagedUpdate(final float pSecondsElapsed) {
            GameUtil.update();

            super.onManagedUpdate(pSecondsElapsed);
        }

    }

}
