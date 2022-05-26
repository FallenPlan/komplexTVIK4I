package com.mygdx.mabg.controller;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.*;

public class MyAngryBirds extends InputAdapter implements Screen {

    public static boolean isOneDarged = false;
    private Body bird, box;
//    private SpriteBatch batch; //Textúrák renderelésére
    private Texture texture;
    private World world;

//    public static PlayScreen playScreen = null;
//    public static GameController game = null;
//    public static GamePhysics physics = null;

//    public static TestClass test = null;
//    public static Test2 test = null;
    public static MouseController controller = null;


//    @Override
//    public void create() {
//
////        test = new TestClass();
////        test.create();
//
////        test = new Test2();
////        test.create();
//
////        game = new GameController();
////        game.create();
//
////        physics = new GamePhysics();
////        physics.create();
//
////        batch = new SpriteBatch();
////        playScreen = new PlayScreen();
////        playScreen.create();
////        setScreen(splash);
//
//    }

    @Override
    public void resize(int width, int height) {
//        game.resize(width, height);
//        playScreen.resize(width, height);
//        physics.resize(width, height);
//        test.resize(width, height);
        controller.resize(width, height);

    }

    @Override
    public void show() {
//        physics.show();
        controller.show();
    }

//    @Override
//    public void render() {
////        super.render();
////        game.render();
////        physics.render();
////        test.render();
//
//
//    }

    @Override
    public void render(float delta) {
        controller.render(delta);

    }

    @Override
    public void dispose() {
//        playScreen.dispose();
//        physics.dispose();
//        test.dispose();
        controller.dispose();

    }

//    @Override
//    public void render(float delta) {
//        super.render();
//        playScreen.render(delta);
//    }
//
//    @Override
//    public boolean reportFixture(Fixture fixture) {
//        return false;
//    }
//
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            controller.touchDown(screenX, screenY, pointer, button);
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
            controller.touchDragged(screenX, screenY, pointer);
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
            controller.touchUp(screenX, screenY, pointer, button);
        return true;
    }


    @Override
    public void pause() {
        controller.pause();
    }

    @Override
    public void resume() {
        controller.resume();
    }

    @Override
    public void hide() {
//        playScreen.hide();
        controller.hide();
    }
}
