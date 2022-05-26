package com.mygdx.mabg.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.mabg.controller.Test2;

import static com.mygdx.mabg.utils.Constants.PPM;

public class Pig {

    public enum State {GOOD, BAD, VERYBAD, DAID}

    private Body body;
    private Texture texture;

    private Sprite sprite;
    private State currentState, prevState;

    private Animation pigBad, pigGood, pigVeryBad, pigDaid;
    private float stateTime = 0;
    public float health = 100;

    //    private TestClass controller;
    private Test2 controller;
    public Pig(String textureName, Test2 gameController, int x, int y) {
        controller = gameController;
        texture = new Texture(textureName);

        sprite = new Sprite(texture);

        body = gameController.createBall(x, y, texture.getWidth(), texture.getHeight(), false);

        currentState = State.GOOD;
        prevState = State.GOOD;
    }

    public Body getBody() {
        return body;
    }

    public void draw() {

        sprite.setPosition(body.getPosition().x*PPM - (sprite.getWidth()/2), body.getPosition().y*PPM - (sprite.getHeight()/2));
        sprite.setRotation(body.getAngle()*MathUtils.radiansToDegrees);
        sprite.setOrigin(sprite.getWidth()/2,sprite.getHeight()/2);
        sprite.draw(controller.getSpriteBatch());
    }

    public State getState() {
        prevState = currentState;
        if (health >= 75) {
            return currentState = State.GOOD;
        } else if (health >= 50) {
            return currentState = State.BAD;
        } else if (health > 8) {
            return currentState = State.VERYBAD;
        } else {
            return currentState = State.DAID;
        }
    }

//    public TextureRegion getTR(float dt) {
//        currentState = getState();
//        TextureRegion region;
//        switch (currentState) {
//            case GOOD:
//                region = pigGood.getKeyFrame(stateTime, true);
//                break;
//            case BAD:
//                region = pigBad.getKeyFrame(stateTime, true);
//                break;
//            case VERYBAD:
//                region = pigVeryBad.getKeyFrame(stateTime, true);
//                break;
//            default:
//                region = pigDaid.getKeyFrame(stateTime, false);
//                break;
//
//        }
//        stateTime = currentState == prevState ? stateTime + dt : 0;
//        return region;
//    }
}
