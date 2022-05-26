package com.mygdx.mabg.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.mabg.view.Bird;

public class GamePhysics {

    private float distance, angle;
    private Vector2 velocityVector;
    private Bird bird;

    private World world;

    public void setBird(Bird bird) {
        this.bird = bird;
    }

    public GamePhysics(Bird bird) {
        this.bird = bird;
    }

    public Vector2 getTrajectoryPoint(Vector2 start, Vector2 velocity, float n) {
        float t = 1 / 60f;
        float tt = t * t;
        float stepVelocityX = t * -velocity.x;
        float stepVelocityY = t * -velocity.y;
        float stepGravityX = tt * world.getGravity().x;
        float stepGravityY = tt * (-9.8f);
        float tpx = start.x + n * stepVelocityX + 0.5f * (n * n + n) * stepGravityX;
        float tpy = start.y + n * stepVelocityY + 0.5f * (n * n + n) * stepGravityY;
        return new Vector2(tpx, tpy);
    }

    public void pressed(int screenX, int screenY) {
        Circle cc = new Circle(bird.getBody().getPosition().x, bird.getBody().getPosition().y, 24);
        if (cc.contains(screenX, Gdx.graphics.getHeight() - screenY)) {
            float x = screenX;
            float y = Gdx.graphics.getHeight() - screenY;
            bird.getBody().getPosition().set(x <= 25 ? 25 : x, y < 85 ? 85 : y);
        }
    }

    public void dragged(int screenX, int screenY) {
        Circle cc = new Circle(bird.getBody().getPosition().x, bird.getBody().getPosition().y, 16);
        if (cc.contains(screenX, Gdx.graphics.getHeight() - screenY)) {
            Vector2 b = new Vector2();
            Vector2 c = new Vector2();
            velocityVector = new Vector2();

            b.set(new Vector2(bird.getBody().getPosition().x, bird.getBody().getPosition().y));
            c.set(162, 157);

            velocityVector.set(b).sub(c);

            distance = velocityVector.len();

            angle = MathUtils.atan2(velocityVector.y, velocityVector.x);
            angle %= 2 * MathUtils.PI;

            float x = screenX;
            float y = Gdx.graphics.getHeight() - screenY;

            bird.getBody().getPosition().set(x <= 25 ? 25 : x, y < 85 ? 85 : y);
        }

    }

    public void mouseUp() {
            float velX = (2.25f * -MathUtils.cos(angle) * (distance));
            float velY = (2.25f * -MathUtils.sin(angle) * (distance));

            bird.getBody().getLinearVelocity().set(velX, velY);

    }

    public Vector2 getVelocityVector() {
        return velocityVector;
    }

}
