package com.mygdx.test;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.ScreenUtils;

import static com.mygdx.test.utils.Constants.PPM;

public class MyTest2 extends ApplicationAdapter {
//	SpriteBatch batch;
//	Texture img;
//
//	@Override
//	public void create () {
//		batch = new SpriteBatch();
//		img = new Texture("badlogic.jpg");
//	}
//
//	@Override
//	public void render () {
//		ScreenUtils.clear(1, 0, 0, 1);
//		batch.begin();
//		batch.draw(img, 0, 0);
//		batch.end();
//	}
//
//	@Override
//	public void dispose () {
//		batch.dispose();
//		img.dispose();
//	}
	private boolean DEBUG = false;
	//private final float SCALE = 2.0f;

	private OrthographicCamera camera;

	private Box2DDebugRenderer b2dr;
	private World world;
	private Body player;

	@Override
	public void create() {
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		camera = new OrthographicCamera();
		camera.setToOrtho(false, w/2, h/2);

		world = new World(new Vector2(0, -9.8f), false);
		b2dr = new Box2DDebugRenderer();

		player = createBox(8, 10, 32, 32, false);
		createBox(0, 0, 64, 32, true);

	}

	@Override
	public void render() {
		update(Gdx.graphics.getDeltaTime());

		//Render
		Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		b2dr.render(world, camera.combined.scl(PPM));
	}

	@Override
	public void resize(int width, int height) {
		camera.setToOrtho(false, width/2, height/2);
	}

	public void dispose() {
		world.dispose();
		b2dr.dispose();
	}

	public void update(float delta) {
		world.step(1/60f, 6, 2);

		inputUpdate(delta);
		cameraUpdate(delta);
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
			player.applyForceToCenter(0, 300, false);
		}

		player.setLinearVelocity(horizontalForce * 5, player.getLinearVelocity().y);
	}

	public void cameraUpdate(float delta) {
		Vector3 position = camera.position;
		position.x = player.getPosition().x * PPM;
		position.y = player.getPosition().y * PPM;
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
		def.fixedRotation = true;
		pBody = world.createBody(def);

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(width/2/PPM, height/2/PPM); //A centerből méri a 32-t ezért osztani kell 2 -vel, hogy 32 magas legyen és ne 64!

		pBody.createFixture(shape, 1.0f);
		shape.dispose();

		return pBody;
	}

}
