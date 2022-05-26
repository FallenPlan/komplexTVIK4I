package com.mygdx.mabg.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.joints.MouseJoint;
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef;
import com.mygdx.mabg.view.Bird;

public class MouseController extends InputAdapter implements Screen {


    Test2 test2 = new Test2();
    private World world = test2.getWorld();
    private Box2DDebugRenderer renderer = test2.getB2dr();
    private OrthographicCamera camera = test2.getCamera();
//    private Body ball, ground;

    private Body ground;

    private Bird ball;
    private MouseJointDef jointDef;
    private MouseJoint joint;

    private Vector3 tmp = new Vector3();
    private Vector2 tmp2 = new Vector2();

    private QueryCallback queryCallback = new QueryCallback() {

        public boolean reportFixture(Fixture fixture) {
            if(!fixture.testPoint(tmp.x, tmp.y))
                return false;

            jointDef.bodyB = fixture.getBody();
            jointDef.target.set(tmp.x, tmp.y);
            joint = (MouseJoint) test2.getWorld().createJoint(jointDef);
            return false;
        }
    };

    @Override
    public void show() {
//        world = new World(new Vector2(0, -9.81f), true);
//        renderer = new Box2DDebugRenderer();
//        camera = new OrthographicCamera();

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        Gdx.input.setInputProcessor(this);

        //Ball
//        CircleShape circleShape = new CircleShape();
//        circleShape.setRadius(1);

//        ball = test2.getWorld().createBody(bodyDef);
//        ball.createFixture(circleShape, 1);
        ball = test2.getBird();

        // ground
//        EdgeShape groundShape = new EdgeShape();
//        groundShape.set(-500,0,500,0);
//
//        bodyDef.type = BodyDef.BodyType.StaticBody;
//        ground = world.createBody(bodyDef);
//        ground.createFixture(groundShape, 0);
//
//        groundShape.dispose();

        ground = test2.getGround();

        // mouse joint
        jointDef = new MouseJointDef();
        jointDef.bodyA = test2.getGround();
        jointDef.collideConnected = true;
        jointDef.maxForce = 500;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        world.step(1 / 60f, 8, 3);
        renderer.render(world, camera.combined);
    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width / 25;
        camera.viewportHeight = height / 25;
        camera.update();
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

    @Override
    public void dispose() {
        world.dispose();
        renderer.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        camera.unproject(tmp.set(screenX, screenY, 0));
        world.QueryAABB(queryCallback, tmp.x, tmp.y, tmp.x, tmp.y);
        if (button == Input.Buttons.LEFT) {
            return true;
        }
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
}
