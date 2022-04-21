package com.mygdx.mabg.controller;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

import static com.mygdx.mabg.utils.Constants.PPM;

public class workingTest extends Game {
    private boolean DEBUG = false;
    private final float SCALE = 2.0f;

    public static boolean isOneDarged = false;
    public final static int V_WIDTH = 1280;
    public final static int V_HIEGHT = 560;

    private SpriteBatch batch; //Textúrák renderelésére
    public Stage stage;
    private Viewport viewport;
    private World world;
    private OrthographicCamera camera;
    private Box2DDebugRenderer b2dr;
    private Body bird, box, ground;
    private Texture texture;

    @Override
    public void create() {
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, w/SCALE, h/SCALE);

        world = new World(new Vector2(0, -9.8f), false);
        b2dr = new Box2DDebugRenderer();

        batch = new SpriteBatch();

        bird = createBox(8, 10, 32, 32, false);
        ground = createBox(0, 0, 256, 32, true);
        box = createBox(80, 10, 20, 64, false);

        batch = new SpriteBatch();
        texture = new Texture("testCharacter.jpg");
    }

    @Override
    public void render() {

        update(Gdx.graphics.getDeltaTime());

        //Render
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(texture, bird.getPosition().x*PPM - (texture.getWidth()/2),
                bird.getPosition().y*PPM - (texture.getHeight()/2));
        batch.end();

        b2dr.render(world, camera.combined.scl(PPM));

        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) Gdx.app.exit();
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
            bird.applyForceToCenter(0, 300, false);
        }

        bird.setLinearVelocity(horizontalForce * 5, bird.getLinearVelocity().y);
    }

    public void cameraUpdate(float delta) {
        Vector3 position = camera.position;
        position.x = bird.getPosition().x * PPM;
        position.y = bird.getPosition().y * PPM;
        camera.position.set(position);

        camera.update();
    }

    public Body createBox(int x, int y, int width, int height, boolean isStatic) {
        Body pBody;
        BodyDef def = new BodyDef();

        if(isStatic)
            def.type = BodyDef.BodyType.StaticBody;
        else
            def.type = BodyDef.BodyType.DynamicBody;

        //def.type = BodyDef.BodyType.DynamicBody;
        def.position.set(x/PPM, y/PPM);
        def.fixedRotation = false;
        pBody = world.createBody(def);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width/2/PPM, height/2/PPM); //A centerből méri a 32-t ezért osztani kell 2 -vel, hogy 32 magas legyen és ne 64!

        pBody.createFixture(shape, 1.0f);
        shape.dispose();

        return pBody;
    }

    public boolean isZero(float x, float y) {
        return x == 0 && y == 0;
    }

    public void dispose() {
        world.dispose();
        b2dr.dispose();
        batch.dispose();
    }
}
