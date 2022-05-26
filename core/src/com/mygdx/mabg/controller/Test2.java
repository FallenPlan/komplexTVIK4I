package com.mygdx.mabg.controller;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.joints.MouseJoint;
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.mabg.MainMenuScreen;
import com.mygdx.mabg.view.*;

import java.util.HashMap;

import static com.mygdx.mabg.utils.Constants.PPM;

public class Test2 extends Game {

//    final HashMap<String, Sprite> sprites = new HashMap<String, Sprite>();

    private boolean DEBUG = false;

    private final float SCALE = 2.0f;

    private SpriteBatch batch; //Textúrák renderelésére
//    private Viewport viewport;
    private World world;
    private OrthographicCamera camera;
    private Box2DDebugRenderer b2dr;

    private Body ground, ball;
    private Bird bird;
    private Pig pig, pig2, pig3, pig4;
    private Block block, block2, block3, block4, block5, block6, block7, block8;
    private BlockH blockH1, blockH2, blockH3, blockH4;
    private Texture texture, groundTexture, background, blockTexture, csuzli, csuzli2;

    private Sprite sprite, sprite2;
    private GamePhysics gamePhysics;
    private InputHandler inputHandler;
    private PlayScreen playScreen;
    private MouseController mouseController;

    private final Vector2 mouseInWorld2D = new Vector2();
    private final Vector3 mouseInWorld3D = new Vector3();

//    private final OrthographicCamera cam;

    public Test2() {
    }

    Texture exitButtonActive;

    @Override
    public void create() {

//        exitButtonActive = new Texture("exit_button.png");
//        Box2D.init();
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, w/SCALE, h/SCALE);

        world = new World(new Vector2(0, -9.8f), false);
        b2dr = new Box2DDebugRenderer();

        mouseController = new MouseController();

        batch = new SpriteBatch();

        setScreen(new MainMenuScreen(this));

//        bird = new Bird("testCharacter.jpg", this, 10, 10);
        bird = new Bird("bird01_3.png", this, -180, 10);
        pig = new Pig("pig01.png", this, 75, 10);
        pig2 = new Pig("pig01.png", this, 75, 130);
        pig3 = new Pig("pig01.png", this, -20, 10);
        pig4 = new Pig("pig01.png", this, -20, 130);

        block = new Block("block3.png", this,50,30);
        block2 = new Block("block3.png", this,100,30);
        block3 = new Block("block3.png", this,50,140);
        block4 = new Block("block3.png", this,100,140);
        blockH3 = new BlockH("block3.png", this,-14,100);
        blockH4 = new BlockH("block3.png", this,76,100);

        block5 = new Block("block3.png", this,-40,30);
        block6 = new Block("block3.png", this,10,30);
        block7 = new Block("block3.png", this,-40,140);
        block8 = new Block("block3.png", this,10,140);
        blockH1 = new BlockH("block3.png", this,-14,180);
        blockH2 = new BlockH("block3.png", this,76,180);

        ground = createVerticalBody(0,0,400,50,true);
//        block = createBody(40, 20, 40, 80, false);

        texture = new Texture("testCharacter.jpg");
        background = new Texture("background.png");
        groundTexture = new Texture("ground.png");
        blockTexture = new Texture("block.png");
        csuzli = new Texture("csuzli1.png");
        csuzli2 = new Texture("csuzli2.png");
        sprite = new Sprite(texture);
        sprite2 = new Sprite(blockTexture);

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;

    }

    @Override
    public void render() {
//        super.render();

        update(Gdx.graphics.getDeltaTime());

        //Render
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
            sprite.translateX(-1f);
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            sprite.translateX(1f);

        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT))
            sprite.setPosition(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());

        batch.begin();
        batch.draw(background, -350, -200);

//        batch.draw(exitButtonActive, -50,80,100,50);

        bird.draw();
        pig.draw();
        pig2.draw();
        pig3.draw();
        pig4.draw();

        block.drawVertical();
        block2.drawVertical();
        block3.drawVertical();
        block4.drawVertical();
        block5.drawVertical();
        block6.drawVertical();
        block7.drawVertical();
        block8.drawVertical();
        blockH1.drawHorizontal();
        blockH2.drawHorizontal();
        blockH3.drawHorizontal();
        blockH4.drawHorizontal();

        batch.draw(groundTexture, -200, -25, 400, 65);
        batch.draw(csuzli, -190, 25, 10, 50);
        batch.draw(csuzli2, -194, 45, 12, 32);


        batch.end();

        b2dr.render(world, camera.combined.scl(PPM));

        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) Gdx.app.exit();
    }

    public Body createVerticalBody(int x, int y, int width, int height, boolean isStatic) {
        Body pBody;
        BodyDef def = new BodyDef();

        if(isStatic)
            def.type = BodyDef.BodyType.StaticBody;
        else
            def.type = BodyDef.BodyType.DynamicBody;

        def.position.set(x/PPM, y/PPM);
        def.fixedRotation = false;
        pBody = world.createBody(def);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width/2/PPM, height/2/PPM); //A centerből méri a 32-t ezért osztani kell 2 -vel, hogy 32 magas legyen és ne 64!

        pBody.createFixture(shape, 1.0f);
        shape.dispose();

        return pBody;
    }

    public Body createHorizontalBody(int x, int y, int width, int height, boolean isStatic) {
        Body pBody;
        BodyDef def = new BodyDef();

        if(isStatic)
            def.type = BodyDef.BodyType.StaticBody;
        else
            def.type = BodyDef.BodyType.DynamicBody;

        def.position.set(x/PPM, y/PPM);
        def.fixedRotation = false;
        pBody = world.createBody(def);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(height/2/PPM, width/2/PPM); //A centerből méri a 32-t ezért osztani kell 2 -vel, hogy 32 magas legyen és ne 64!

        pBody.createFixture(shape, 1.0f);
        shape.dispose();

        return pBody;
    }

    public Body createBall(int x, int y, int width, int height, boolean isStatic) {
        Body pBody;
        BodyDef def = new BodyDef();

        if(isStatic)
            def.type = BodyDef.BodyType.StaticBody;
        else
            def.type = BodyDef.BodyType.DynamicBody;

        def.position.set(x/PPM, y/PPM);
        def.fixedRotation = false;
        pBody = world.createBody(def);

        //Bird
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(0.3f);

        pBody = world.createBody(def);
        pBody.createFixture(circleShape, 2.0f);

        circleShape.dispose();

        return pBody;
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width/SCALE, height/SCALE);
//            camera.viewportWidth = width/2;
//            camera.viewportHeight = height/2;
//            camera.position.set(width/SCALE, height/SCALE, 0); //by default camera position on (0,0,0)
    }

    public void update(float delta) {
        world.step(1/60f, 6, 2);

        inputUpdate(delta);
        cameraUpdate(delta);
        batch.setProjectionMatrix(camera.combined);
    }

    public void inputUpdate(float delta) {
        int horizontalForce = 0;

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) { //UP
            horizontalForce -= 1;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) { //DOWN
            horizontalForce += 1;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP)) { //LEFT
            bird.getBody().applyForceToCenter(0, 300, false);
        }

        bird.getBody().setLinearVelocity(horizontalForce * 5, bird.getBody().getLinearVelocity().y);

    }

    public void cameraUpdate(float delta) {
        Vector3 position = camera.position;
        position.x = ground.getPosition().x;
        position.y = 160;
        camera.position.set(position);

        camera.update();
    }


    public Body getGround() {
        return ground;
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public SpriteBatch getSpriteBatch() {
        return batch;
    }

    public World getWorld() {
        return world;
    }

    public Box2DDebugRenderer getB2dr() {
        return b2dr;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public Bird getBird() {
        return bird;
    }

    public void dispose() {
        world.dispose();
        b2dr.dispose();
        batch.dispose();
        texture.dispose();
    }

}
