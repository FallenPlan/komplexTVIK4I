package com.mygdx.mabg.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.mabg.controller.GameController;
import com.mygdx.mabg.controller.GamePhysics;
import com.mygdx.mabg.controller.InputHandler;
import com.mygdx.mabg.controller.MyAngryBirds;
import com.mygdx.mabg.model.B2World;
import com.mygdx.mabg.model.ControllerLogic;
import com.mygdx.mabg.model.ImpMath;
import com.mygdx.mabg.model.World;

public class Play extends InputAdapter implements Screen {

    public static OrthographicCamera orthographicCamera;
    private MyAngryBirds myAngryBirds;
    private GameController gameController;
    private Viewport gamePort;
    private World world;
    public Bird bird;
    private GamePhysics gamePhysics;
    private SpriteBatch batch;
    private Stage stage;
    private ShapeRenderer shapeRenderer;
    public static InputMultiplexer inputMultiplexer;
    private TiledMap map;

    public Play(MyAngryBirds myAngryBirds) {

        shapeRenderer = new ShapeRenderer();
        this.myAngryBirds = myAngryBirds;
        stage = new Stage();
        orthographicCamera = new OrthographicCamera();
        gamePort = new FitViewport(MyAngryBirds.V_WIDTH, MyAngryBirds.V_HIEGHT, orthographicCamera);

        orthographicCamera.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);

        //Phiscs
//        world = new World(ImpMath.DT, false);
//
//        bird = new Bird(world, 0);
//
//        gameController = new GameController(world, myAngryBirds.batch, bird);
//
//        gamePhysics = new GamePhysics(bird);
    }

    @Override
    public void show() {
        {

            inputMultiplexer = new InputMultiplexer();
            inputMultiplexer.addProcessor(gameController.stage);
            inputMultiplexer.addProcessor(new InputHandler(gamePhysics));
            inputMultiplexer.addProcessor(this);
            new B2World(world, map);   // just floor
            Gdx.input.setInputProcessor(inputMultiplexer);

//            ControllerLogic.boxArray.clear();
//            ControllerLogic.circleArray.clear();
//            new GetDb(world);
//            {
//                Music s = Gdx.audio.newMusic(Gdx.files.internal("sounds/game.wav"));
//                s.setLooping(true);
//                s.setVolume(10);
//                s.play();
//
//            }
        }
        if(ControllerLogic.ISRERUN){
            bird.player.position.set(ControllerLogic.POS.x,ControllerLogic.POS.y);
            bird.player.velocity.set(ControllerLogic.vel.x,ControllerLogic.vel.y);
            bird.player.shape.initialize();
        }
    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
