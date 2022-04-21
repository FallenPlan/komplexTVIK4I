package com.mygdx.mabg.controller;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.joints.MouseJoint;
import com.mygdx.mabg.view.PlayScreen;

public class MyAngryBirds extends Game {

    public static boolean isOneDarged = false;
    private Body bird, box;
//    private SpriteBatch batch; //Textúrák renderelésére
    private Texture texture;
    private World world;

    public static PlayScreen playScreen = null;
    public static GameController game = null;


    @Override
    public void create() {

        game = new GameController();
        game.create();
//        batch = new SpriteBatch();
//        playScreen = new PlayScreen();
//        playScreen.create();
//        setScreen(splash);

    }

    @Override
    public void resize(int width, int height) {
        game.resize(width, height);
//        playScreen.resize(width, height);
    }

//    @Override
//    public void show() {
//        playScreen.show();
//    }

    @Override
    public void render() {
        super.render();
        game.render();
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
//    @Override
//    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
//            playScreen.touchDown(screenX, screenY, pointer, button);
//        return true;
//    }
//
//    @Override
//    public boolean touchDragged(int screenX, int screenY, int pointer) {
//            playScreen.touchDragged(screenX, screenY, pointer);
//        return true;
//    }
//
//    @Override
//    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
//            playScreen.touchUp(screenX, screenY, pointer, button);
//        return true;
//    }
//
//
//    @Override
//    public void pause() {
//        playScreen.pause();
//    }
//
//    @Override
//    public void resume() {
//        playScreen.resume();
//    }
//
//    @Override
//    public void hide() {
//        playScreen.hide();
//    }
//
//    @Override
//    public void dispose() {
//        playScreen.dispose();
//    }
}
