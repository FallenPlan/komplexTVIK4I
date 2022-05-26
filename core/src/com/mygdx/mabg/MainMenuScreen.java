package com.mygdx.mabg;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.mabg.controller.Test2;

public class MainMenuScreen implements Screen {

    Test2 test2;

    Texture playButtonActive;
    Texture playButtonInactive;
    Texture exitButtonActive;
    Texture exitButtonInactive;

    public MainMenuScreen(Test2 test2) {
        this.test2 = test2;
        playButtonActive = new Texture("play_button.png");
        exitButtonActive = new Texture("exit_button.png");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        //Render
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        test2.getBatch().begin();

        test2.getBatch().draw(exitButtonActive, 10,10,100,50);

        test2.getBatch().end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
