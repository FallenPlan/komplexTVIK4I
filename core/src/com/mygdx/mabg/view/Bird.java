package com.mygdx.mabg.view;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
//import com.mygdx.mabg.model.Body;
import com.badlogic.gdx.physics.box2d.Body;

import static com.mygdx.mabg.utils.Constants.PPM;

public class Bird extends Sprite {
    public enum State {FLYING, FALLING, ONAIR, STANDING, STOPPED}

    public State currentState;
    public State previousState;

    public static World world;
    public Body bird;
    public float gravity;
    private TextureRegion textureRegion;


    public Bird() {
    }

//    public State getState() {
//        if (isZero(bird.velocity.x, bird.velocity.y) && previousState == State.STANDING) {
//            return currentState = State.STANDING;
//        } else if (isZero(bird.velocity.x, bird.velocity.y) && previousState != State.STANDING) {
//            return currentState = State.STOPPED;
//        } else if (bird.velocity.x > 0 && bird.velocity.y > 0 && previousState != State.FALLING && previousState != State.ONAIR) {
//            return currentState = State.FLYING;
//        } else if (bird.velocity.x > 0 && bird.velocity.y < 0 && previousState != State.ONAIR) {
//            return currentState = State.FALLING;
//        } else {
//            return previousState = currentState = State.ONAIR;
//        }
//    }


//    public boolean isZero(float x, float y) {
//        return x == 0 && y == 0;
//    }
}
