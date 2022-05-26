package com.mygdx.mabg.controller;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.mygdx.mabg.MainMenuScreen;

public class Runnable extends Game {

//    public static MyAngryBirds angryBirds = null;
    public static Test2 test2 = null;
//    public static MainMenuScreen menuScreen = null;
    public static MouseController mouseController = null;

    @Override
    public void create() {
//        angryBirds = new MyAngryBirds();
        test2 = new Test2();
        test2.create();
//        menuScreen = new MainMenuScreen(test2);
    }

    @Override
    public void dispose() {
//        angryBirds.dispose();
    }

    @Override
    public void pause() {
//        angryBirds.pause();
    }

//    @Override
//    public void hide() {
//
//    }
//
////    @Override
////    public void resume() {
//////        angryBirds.resume();
////    }
//
//    @Override
//    public void show() {
//
//    }

//    @Override
//    public void render(float delta) {
////        angryBirds.render(delta);
//        menuScreen.render(delta);
//    }

    @Override
    public void render() {
        test2.render();
    }

    @Override
    public void resize(int width, int height) {
//        angryBirds.resize(width, height);
        test2.resize(width, height);
    }
}
