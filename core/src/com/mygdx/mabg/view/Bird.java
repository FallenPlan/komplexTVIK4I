package com.mygdx.mabg.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
//import com.mygdx.mabg.model.Body;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.mabg.controller.GameController;
import com.mygdx.mabg.controller.GamePhysics;

import static com.mygdx.mabg.utils.Constants.PPM;

public class Bird {

    private Body body;
    private Texture texture;
//    private GameController controller;
//
//    public Bird(String textureName, GameController gameController, int x, int y) {
//        controller = gameController;
//        texture = new Texture(textureName);
//
//        body = gameController.createBox(x, y, texture.getWidth(), texture.getHeight(), false);
//    }
//
//    public void draw() {
//        controller.getSpriteBatch().draw(texture, body.getPosition().x*PPM - (texture.getWidth()/2),
//                body.getPosition().y*PPM - (texture.getHeight()/2));
//    }

    private GamePhysics physics;

    public Bird(String textureName, GamePhysics gamePhysics, int x, int y) {
        physics = gamePhysics;
        texture = new Texture(textureName);

        body = gamePhysics.createBody("bird01_2", 20, 20, 0);
    }

    public void draw() {
        physics.getSpriteBatch().draw(texture, body.getPosition().x*PPM - (texture.getWidth()/2),
                body.getPosition().y*PPM - (texture.getHeight()/2));
    }
}
