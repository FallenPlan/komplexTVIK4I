package com.mygdx.mabg.controller;

import com.badlogic.gdx.physics.box2d.Fixture;

public interface MyAngryBirdsInterface {
    void render(float delta);

    boolean reportFixture(Fixture fixture);

    void show();

    boolean touchDown(int screenX, int screenY, int pointer, int button);

    boolean touchDragged(int screenX, int screenY, int pointer);

    boolean touchUp(int screenX, int screenY, int pointer, int button);

    void hide();
}
