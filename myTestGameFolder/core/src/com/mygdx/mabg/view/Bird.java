package com.mygdx.mabg.view;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.mabg.model.Body;

public class Bird extends Sprite {
    public enum State {FLYING, FALLING, ONAIR, STANDING, STOPPED}

    public State currentState;
    public State previousState;

    public World world;
    public Body player;
    public float gravity;
    private TextureRegion textureRegion;

    public Bird(World world, float gravity) {
//        super(PlayS)
    }

    public State getState() {
        if (isZero(player.velocity.x, player.velocity.y) && previousState == State.STANDING) {
            return currentState = State.STANDING;
        } else if (isZero(player.velocity.x, player.velocity.y) && previousState != State.STANDING) {
            return currentState = State.STOPPED;
        } else if (player.velocity.x > 0 && player.velocity.y > 0 && previousState != State.FALLING && previousState != State.ONAIR) {
            return currentState = State.FLYING;
        } else if (player.velocity.x > 0 && player.velocity.y < 0 && previousState != State.ONAIR) {
            return currentState = State.FALLING;
        } else {
            return previousState = currentState = State.ONAIR;
        }
    }

    public boolean isZero(float x, float y) {
        return x == 0 && y == 0;
    }
}
