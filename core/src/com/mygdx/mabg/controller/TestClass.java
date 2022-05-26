package com.mygdx.mabg.controller;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
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
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.mabg.physicseditor.PhysicsShapeCache;
import com.mygdx.mabg.view.Bird;
import com.mygdx.mabg.view.Block;

import java.util.HashMap;

import static com.mygdx.mabg.utils.Constants.PPM;

public class TestClass extends Game implements InputProcessor {

    final HashMap<String, Sprite> sprites = new HashMap<String, Sprite>();

    private boolean DEBUG = false;

    private final float SCALE = 2.0f;

    private SpriteBatch batch; //Textúrák renderelésére
    private Viewport viewport;
    private World world;
    private OrthographicCamera camera;
    private Box2DDebugRenderer b2dr;

    private Body ground;
    private Bird bird;
//    private Block block;
    private Body block;
    private Texture texture, groundTexture, background, blockTexture;
    private TextureAtlas textureAtlas;

    private Sprite sprite;

//    private PhysicsShapeCache physicsBodies;

    public TestClass() {
    }

    @Override
    public void create() {
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, w/SCALE, h/SCALE);

        world = new World(new Vector2(0, -9.8f), false);
        b2dr = new Box2DDebugRenderer();

        batch = new SpriteBatch();

//        bird = new Bird("testCharacter.jpg", this, 10, 10);

//        block = new Block();
//        TextureAtlas textureAtlas1 = new TextureAtlas();

//        block = createBody("block", 10, 50, 0);
//        block = createBody(10, 10, 20, 30, false);

//        bird = createBody(20,20,30,30,false);
        ground = createBody(0,0,200,50,true);
        block = createBody(40, 20, 40, 80, false);

        texture = new Texture("testCharacter.jpg");
        background = new Texture("background.png");
        groundTexture = new Texture("ground.png");
        blockTexture = new Texture("block.png");
        sprite = new Sprite(texture);

//        sprite.setPosition(bird.getPosition().x,
//                bird.getPosition().y);

    }

    @Override
    public void render() {

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

        bird.draw();
//        draw(texture, bird.);
//        batch.draw(blockTexture, -20, -40, 40, 80);
        draw(blockTexture, block);
//        draw(groundTexture, ground);
        batch.draw(groundTexture, -100, -25, 200, 65);

//        bird.getUserData() = bird.getAngle();
//        bird.getAngle();
        batch.end();

        b2dr.render(world, camera.combined.scl(PPM));

        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) Gdx.app.exit();
    }

//    public Body createBody(String name, float x, float y, float rotation) {
//        Body body = physicsBodies.createBody(name, world, SCALE, SCALE);
//        body.setTransform(x, y, rotation);
//        return body;
//    }

    public Body createBody(int x, int y, int width, int height, boolean isStatic) {
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

    private void addSprites() {
        Array<TextureAtlas.AtlasRegion> regions = textureAtlas.getRegions();

        for(TextureAtlas.AtlasRegion region : regions) {
            Sprite sprite = textureAtlas.createSprite(region.name);

            float width = sprite.getWidth() * SCALE;
            float height = sprite.getHeight() * SCALE;

            sprite.setSize(width, height);
            sprite.setOrigin(0, 0);

            sprites.put(region.name, sprite);
        }
    }

    private void drawSprite(String name, float x, float y, float degrees) {

        Sprite sprite = sprites.get(name);
        sprite.setPosition(x, y);
        sprite.setRotation(degrees);
        sprite.setOrigin(0f,0f);
        sprite.draw(batch);
    }

    public void draw(Texture texture, Body body) {
        getSpriteBatch().draw(texture, body.getPosition().x*PPM - (texture.getWidth()/2),
                body.getPosition().y*PPM - (texture.getHeight()/2));
//        batch.draw(texture, texture.getWidth()/2, texture.getHeight()/2, 10,10);
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width/SCALE, height/SCALE);
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
        position.x = bird.getBody().getPosition().x * PPM;
        position.y = bird.getBody().getPosition().y * PPM;
        camera.position.set(position);

        camera.update();
    }

    public SpriteBatch getSpriteBatch() {
        return batch;
    }

    public void dispose() {
        world.dispose();
        b2dr.dispose();
        batch.dispose();
        texture.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

}
