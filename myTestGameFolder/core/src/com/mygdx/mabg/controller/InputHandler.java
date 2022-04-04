package com.mygdx.mabg.controller;

import com.badlogic.gdx.InputAdapter;

public class InputHandler extends InputAdapter {

    GamePhysics gamePhysics;

    public InputHandler(GamePhysics gamePhysics) {
        this.gamePhysics = gamePhysics;
    }


}
