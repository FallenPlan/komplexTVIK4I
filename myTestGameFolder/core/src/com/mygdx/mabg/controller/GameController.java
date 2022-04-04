package com.mygdx.mabg.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.mabg.view.Bird;

public class GameController implements Disposable {

    public Stage stage;
    private Viewport viewport;

    public GameController(World world, SpriteBatch batch, Bird bird) {

    }

    @Override
    public void dispose() {

    }
}
