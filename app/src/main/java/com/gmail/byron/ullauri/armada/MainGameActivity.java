package com.gmail.byron.ullauri.armada;

import com.gmail.byron.ullauri.armada.sprite.PlayerShip;

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
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.ui.activity.SimpleBaseGameActivity;
import org.andengine.util.color.Color;
import org.andengine.util.debug.Debug;


public class MainGameActivity extends SimpleBaseGameActivity {
    private GameUtil gameUtil = GameUtil.INSTANCE;
    private BuildableBitmapTextureAtlas spriteTextureAtlas;
    private TiledTextureRegion playerShipTextureRegion;
    private EnemyBank enemyBank = new EnemyBank();
    private Scene scene;


    @Override
    public EngineOptions onCreateEngineOptions() {
        final Camera camera = new Camera(0, 0, GameUtil.CAMERA_WIDTH, GameUtil.CAMERA_HEIGHT);

        return new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED,
                new RatioResolutionPolicy(GameUtil.CAMERA_WIDTH, GameUtil.CAMERA_HEIGHT), camera);
    }

    @Override
    protected void onCreateResources() {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
        spriteTextureAtlas = new BuildableBitmapTextureAtlas(this.getTextureManager(), 1024, 1024,
                TextureOptions.NEAREST);

        playerShipTextureRegion = BitmapTextureAtlasTextureRegionFactory
                .createTiledFromAsset(spriteTextureAtlas, this, "red_ship.png", 2, 1);

        TiledTextureRegion yellowShipTextureRegion = BitmapTextureAtlasTextureRegionFactory
                .createTiledFromAsset(spriteTextureAtlas, this, "yellow_ship.png", 2, 1);
        float[] yellowShipShootingPointOffSet = {(yellowShipTextureRegion.getHeight() / 2) + 10,
                (yellowShipTextureRegion.getWidth() / 2) - 5};
        Color[] yellowShipColors = {Color.WHITE, Color.CYAN, Color.GREEN, Color.WHITE};
        enemyBank.add(yellowShipTextureRegion, yellowShipShootingPointOffSet, .7f, yellowShipColors);

        TiledTextureRegion purpleShipTextureRegion = BitmapTextureAtlasTextureRegionFactory
                .createTiledFromAsset(spriteTextureAtlas, this, "purple_ship.png", 2, 1);
        float[] purpleShipShootingPointOffSet = {(purpleShipTextureRegion.getHeight() / 2) + 20,
                (yellowShipTextureRegion.getWidth() / 2) - 4};
        Color[] purpleShipColors = {Color.WHITE, Color.CYAN, Color.GREEN, Color.YELLOW, Color.PINK, Color.WHITE};
        enemyBank.add(purpleShipTextureRegion, purpleShipShootingPointOffSet, .7f, purpleShipColors);

        TiledTextureRegion blueShipTextureRegion = BitmapTextureAtlasTextureRegionFactory
                .createTiledFromAsset(spriteTextureAtlas, this, "blue_ship.png", 2, 1);
        float[] blueShipShootingPointOffSet = {(blueShipTextureRegion.getHeight() / 2) + 20,
                (blueShipTextureRegion.getWidth() / 2) - 2};
        Color[] blueShipColors = {Color.WHITE, Color.GREEN, Color.YELLOW, Color.RED, Color.PINK, Color.WHITE};
        enemyBank.add(blueShipTextureRegion, blueShipShootingPointOffSet, .8f, blueShipColors);

        TiledTextureRegion orangeShipTextureRegion = BitmapTextureAtlasTextureRegionFactory
                .createTiledFromAsset(spriteTextureAtlas, this, "orange_ship.png", 2, 1);
        float[] orangeShipShootingPointOffSet = {(orangeShipTextureRegion.getHeight() / 2),
                (orangeShipTextureRegion.getWidth() / 2)};
        Color[] orangeShipColors = {Color.WHITE, Color.CYAN, Color.GREEN, Color.YELLOW, Color.WHITE};
        enemyBank.add(orangeShipTextureRegion, orangeShipShootingPointOffSet, .9f, orangeShipColors);

        TiledTextureRegion greenShipTextureRegion = BitmapTextureAtlasTextureRegionFactory
                .createTiledFromAsset(spriteTextureAtlas, this, "green_ship.png", 2, 1);
        float[] greenShipShootingPointOffSet = {(greenShipTextureRegion.getHeight() / 2),
                (greenShipTextureRegion.getWidth() / 2)};
        Color[] greenShipColors = {Color.WHITE, Color.CYAN, Color.YELLOW, Color.PINK, Color.WHITE};
        enemyBank.add(greenShipTextureRegion, greenShipShootingPointOffSet, .9f, greenShipColors);

        try {
            this.spriteTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 0, 0));
            this.spriteTextureAtlas.load();
        } catch (ITextureAtlasBuilder.TextureAtlasBuilderException e) {
            Debug.e(e);
        }
    }

    @Override
    protected Scene onCreateScene() {
        this.mEngine.registerUpdateHandler(new FPSLogger());
        EngineLock engineLock = mEngine.getEngineLock();
        VertexBufferObjectManager vertexBufferObjectManager = this.getVertexBufferObjectManager();

        float shootingPointXOffSet = 20;
        float shootingPointYOffSet = (playerShipTextureRegion.getWidth() / 2) - 4;
        PlayerShip playerShip = new PlayerShip(shootingPointXOffSet, shootingPointYOffSet,
                playerShipTextureRegion, vertexBufferObjectManager);
        playerShip.setRotation(-90);
        playerShip.setScale(.65f);

        scene = new LevelScene(vertexBufferObjectManager, playerShip, enemyBank);
        scene.setBackground(new Background(0.09804f, 0.6274f, 0.8784f));
        gameUtil.initScene(scene, engineLock, playerShip);
        return scene;
    }

}