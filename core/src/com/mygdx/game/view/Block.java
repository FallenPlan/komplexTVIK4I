package com.mygdx.game.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.controller.GameController;

import static com.mygdx.game.utils.Constants.PPM;

public class Block {

    private Body body;
    private Texture texture;
    private GameController controller;

    public Block(String textureName, GameController gameController, int x, int y) {
        controller = gameController;
        texture = new Texture(textureName);

        body = gameController.createBox(x, y, texture.getWidth(), texture.getHeight(), false);
    }

    public void draw() {
        controller.getSpriteBatch().draw(texture, body.getPosition().x*PPM - (texture.getWidth()/2),
                body.getPosition().y*PPM - (texture.getHeight()/2));
    }

}
