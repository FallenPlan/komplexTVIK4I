package com.mygdx.mabg.controller;

import com.badlogic.gdx.InputAdapter;

public class InputHandler extends InputAdapter {

    GamePhysics ph;
    public InputHandler(GamePhysics ph) {
        this.ph=ph;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        ph.pressed(screenX,screenY);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        ph.mouseUp();
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        ph.dragged(screenX, screenY);
        return false;
    }

}
