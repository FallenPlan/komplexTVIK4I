package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.joints.MouseJoint;
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef;

import static com.mygdx.game.utils.Constants.PPM;

public class PlayScreen extends InputAdapter implements Screen, PlayScreenInterface {

    private boolean DEBUG = false;
    private final float SCALE = 2.0f;

    private World world;
    private OrthographicCamera camera;
    private Box2DDebugRenderer renderer;
    private Body bird, ground;
    private SpriteBatch batch; //Textúrák renderelésére
//    private Texture texture;

    private MouseJointDef jointDef;
    private MouseJoint joint;

    public PlayScreen() {

    }

    private Vector3 tmp = new Vector3();
    private Vector2 tmp2 = new Vector2();

    private QueryCallback queryCallback = new QueryCallback() {

        public boolean reportFixture(Fixture fixture) {
            if(!fixture.testPoint(tmp.x, tmp.y))
                return false;

            jointDef.bodyB = fixture.getBody();
            jointDef.target.set(tmp.x, tmp.y);
            joint = (MouseJoint) world.createJoint(jointDef);
            return false;
        }
    };

    @Override
    public void create() {
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        batch = new SpriteBatch();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, w/SCALE, h/SCALE);
    }

    @Override
    public void show() {
        world = new World(new Vector2(0, -9.8f), true);
        renderer = new Box2DDebugRenderer();
        camera = new OrthographicCamera();

        Gdx.input.setInputProcessor(this);

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        //Bird
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(1);

        bird = world.createBody(bodyDef);
        bird.createFixture(circleShape, 1);

        circleShape.dispose();

        //ground
        EdgeShape groundShape = new EdgeShape();
        groundShape.set(-500, 0, 500, 0);

        bodyDef.type = BodyDef.BodyType.StaticBody;
        ground = world.createBody(bodyDef);
        ground.createFixture(groundShape, 0);

        groundShape.dispose();

        // mouse joint
        jointDef = new MouseJointDef();
        jointDef.bodyA = ground;
        jointDef.collideConnected = true;
        jointDef.maxForce = 500;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        camera.unproject(tmp.set(screenX, screenY, 0));
        world.QueryAABB(queryCallback, tmp.x, tmp.y, tmp.x, tmp.y);
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if(joint == null)
            return false;

        camera.unproject(tmp.set(screenX, screenY, 0));
        joint.setTarget(tmp2.set(tmp.x, tmp.y));

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if(joint == null)
            return false;

        world.destroyJoint(joint);
        joint = null;

        return true;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        world.step(1 / 60f, 8, 3);
        renderer.render(world, camera.combined.scl(PPM));

        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) Gdx.app.exit();
    }

    @Override
    public void resize(int width, int height) {
//        camera.viewportWidth = width / 25;
//        camera.viewportHeight = height / 25;
//        camera.update();
        camera.setToOrtho(false, width/SCALE, height/SCALE);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        dispose();
    }

    public void dispose() {
        world.dispose();
        renderer.dispose();
    }
}
