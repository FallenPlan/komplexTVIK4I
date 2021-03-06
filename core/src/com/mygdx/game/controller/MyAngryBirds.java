package com.mygdx.game.controller;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public class MyAngryBirds extends Game {

    public static boolean isOneDarged = false;
    private Body bird, box;
//    private SpriteBatch batch; //Textúrák renderelésére
    private Texture texture;
    private World world;

//    public static PlayScreen playScreen = null;
    public static GameController game = null;
    public static GamePhysics physics = null;


    @Override
    public void create() {

        game = new GameController();
        game.create();

        physics = new GamePhysics();
        physics.create();

//        batch = new SpriteBatch();
//        playScreen = new PlayScreen();
//        playScreen.create();
//        setScreen(splash);

    }

    @Override
    public void resize(int width, int height) {
        game.resize(width, height);
//        playScreen.resize(width, height);
        physics.resize(width, height);
    }

//    @Override
//    public void show() {
//        physics.show();
//    }

    @Override
    public void render() {
        super.render();
        game.render();
        physics.render();
    }

    @Override
    public void dispose() {
//        playScreen.dispose();
        physics.dispose();
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
}
