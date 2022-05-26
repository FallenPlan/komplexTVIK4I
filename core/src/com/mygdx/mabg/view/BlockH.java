package com.mygdx.mabg.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.mabg.controller.Test2;

import static com.mygdx.mabg.utils.Constants.PPM;

public class BlockH {

    private Body body;
    private Texture texture;

    private Sprite sprite;

    //    private TestClass controller;
    private Test2 controller;


    public BlockH(String textureName, Test2 gameController, int x, int y) {
        controller = gameController;
        texture = new Texture(textureName);

        sprite = new Sprite(texture);

        body = gameController.createHorizontalBody(x, y, texture.getWidth(), texture.getHeight(), false);
    }

    public Body getBody() {
        return body;
    }

    public void drawHorizontal() {
        sprite.setPosition(body.getPosition().x*PPM - (sprite.getWidth()/2), body.getPosition().y*PPM - (sprite.getHeight()/2));
        sprite.setRotation(body.getAngle()* MathUtils.radiansToDegrees + 90);
        sprite.setOrigin(sprite.getWidth()/2,sprite.getHeight()/2);
        sprite.draw(controller.getSpriteBatch());
    }
}
