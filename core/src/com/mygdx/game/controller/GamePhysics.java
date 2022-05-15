package com.mygdx.game.controller;

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
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.joints.MouseJoint;
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
//import com.mygdx.game.physicseditor.PhysicsShapeCache;
import com.mygdx.game.view.Bird;

import java.util.HashMap;

import static com.mygdx.game.utils.Constants.PPM;

public class GamePhysics extends Game implements InputProcessor {

    final HashMap<String, Sprite> sprites = new HashMap<String, Sprite>();

    final static float SCALE = 0.05f;

//    GamePhysics gamePhysics;

    TextureAtlas textureAtlas;
//    Sprite block;
    SpriteBatch batch;
    World world;
    OrthographicCamera camera;
    ExtendViewport viewport;
//    PhysicsShapeCache physicsBodies, birdPhysicsBodies;
    Box2DDebugRenderer debugRenderer;
    Body block, block2, bird, block3;
    Bird bird2;

    Texture texture;

    private Sprite sprite;


    private MouseJointDef jointDef;
    private MouseJoint joint;


    private Body body;
    private Texture texture2;
    private GameController controller;

//    public GamePhysics(String textureName, GameController gameController, int x, int y) {
//
//        controller = gameController;
//        texture2 = new Texture(textureName);
//        body = createBody("block", texture2.getWidth(), texture2.getHeight(), 0);
//    }

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

//        // this will calculate the triangles given your vertices
//        short triangles[] = new EarClippingTriangulator().computeTriangles(vertices1).toArray();
//// use your texture region
//        PolygonRegion polygonRegion = new PolygonRegion(textureRegion, vertices1, triangles);

        Box2D.init();
        debugRenderer = new Box2DDebugRenderer();
        world = new World(new Vector2(0, -10), true);

        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new ExtendViewport(800, 600, camera);

//        physicsBodies = new PhysicsShapeCache("physics.xml");
//        birdPhysicsBodies = new PhysicsShapeCache("physicsEditorTest.json");

        textureAtlas = new TextureAtlas("sprites.txt");
//        block = textureAtlas.createSprite("block");

        addSprites();

        block = createBody("block", 10, 50, 0);
        block2 = createBody("block", 15, 80, 0);
//        bird = createBody("bird01_2", 25, 20, 0);

//        bird = createBody("bird01_2", 20, 20, 0);

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        texture = new Texture("bird01_2.png");

        //Bird
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(10);

        bird = world.createBody(bodyDef);
        bird.createFixture(circleShape, 1);

        circleShape.dispose();

//        bird2 = new Bird("bird01_2.png", new GameController(), 20, 30);

        // mouse joint
        jointDef = new MouseJointDef();
        jointDef.bodyA = ground;
        jointDef.collideConnected = true;
        jointDef.maxForce = 500;

        block3 = createBody("block", 20, 100, 0);


        sprite = new Sprite(texture);
        sprite.setPosition(bird.getPosition().x,
                bird.getPosition().y);
    }

    @Override
    public void render() {

//        polygonSpriteBatch.draw(polygonRegion, x, y);
//        texture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);

        Gdx.gl.glClearColor(0.57f, 0.77f, 0.85f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stepWorld();

        batch.begin();

        Vector2 position = block.getPosition();
        float degrees = (float) Math.toDegrees(block.getAngle());
        drawSprite("block", position.x, position.y, degrees);

        position = block2.getPosition();
        degrees = (float) Math.toDegrees(block2.getAngle());
        drawSprite("block", position.x, position.y, degrees);

//        position = bird.getPosition();
//        degrees = (float) Math.toDegrees(bird.getAngle());
//        drawSprite("bird01_2", position.x, position.y, degrees);

//        bird2.draw();
        batch.draw(texture, bird.getPosition().x+40 - (texture.getWidth()/2),
                bird.getPosition().y+40 - (texture.getHeight()/2));

        position = block3.getPosition();
        degrees = (float) Math.toDegrees(block3.getAngle());
        drawSprite("block", position.x, position.y, degrees);

//        Vector2 position = block.getPosition();
//        drawSprite("block", position.x, position.y);


//        drawSprite("block", 0,0);
//        drawSprite("block", 30,30);


//        block.setPosition(0,0);
//        block.draw(batch);
//
//        block.setPosition(30,30);
//        block.draw(batch);

        batch.end();

        debugRenderer.render(world, camera.combined);
//        debugRenderer.render(world, camera.combined.scl(PPM));

        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) Gdx.app.exit();
    }

    private void drawSprite(String name, float x, float y, float degrees) {
//        Sprite sprite = textureAtlas.createSprite(name);
//
//        sprite.setPosition(x, y);
//
//        sprite.draw(batch);

        Sprite sprite = sprites.get(name);
        sprite.setPosition(x, y);
        sprite.setRotation(degrees);
        sprite.setOrigin(0f,0f);
        sprite.draw(batch);
    }

    private void addSprites() {
        Array<AtlasRegion> regions = textureAtlas.getRegions();

        for(AtlasRegion region : regions) {
            Sprite sprite = textureAtlas.createSprite(region.name);

            float width = sprite.getWidth() * SCALE;
            float height = sprite.getHeight() * SCALE;

            sprite.setSize(width, height);
            sprite.setOrigin(0, 0);

            sprites.put(region.name, sprite);
        }
    }

    public Body createBody(String name, float x, float y, float rotation) {
//        Body body = physicsBodies.createBody(name, world, SCALE, SCALE);
        body.setTransform(x, y, rotation);
        return body;
    }

    static final float STEP_TIME = 1f / 60f;
    static final int VELOCITY_ITERATIONS = 6;
    static final int POSITION_ITERATIONS = 2;

    float accumulator = 0;

    private void stepWorld() {
        float delta = Gdx.graphics.getDeltaTime();

        accumulator += Math.min(delta, 0.25f);

        if (accumulator >= STEP_TIME) {
            accumulator -= STEP_TIME;

            world.step(STEP_TIME, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
        }
    }

    Body ground;

    private void createGround() {
        if (ground != null) world.destroyBody(ground);
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(camera.viewportWidth, 1);
        fixtureDef.shape = shape;
        ground = world.createBody(bodyDef);
        ground.createFixture(fixtureDef);
        ground.setTransform(0, 0, 0);
        shape.dispose();
    }

    public SpriteBatch getSpriteBatch() {
        return batch;
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        batch.setProjectionMatrix(camera.combined.scl(PPM/7));
        createGround();
    }

    @Override
    public void dispose() {
        textureAtlas.dispose();
        sprites.clear();
        world.dispose();
        debugRenderer.dispose();
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
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

//    public void pressed(int x, int y) {
//        Circle c = new Circle(bird.bird.position.x, bird.bird.position.y, 24);
//        if (c.contains(x, Gdx.graphics.getHeight() - y) && bird.getState() == Bird.State.STANDING) {
//            float fx = x;
//            float fy = Gdx.graphics.getHeight() - y;
//            bird.bird.position.set(fx <= 25 ? 25 : fx, fy < 85 ? 85 : fy);
//        }
//    }

}
