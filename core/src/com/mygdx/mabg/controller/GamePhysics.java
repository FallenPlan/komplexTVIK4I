package com.mygdx.mabg.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Circle;
import com.mygdx.mabg.view.Bird;

public class GamePhysics {

    private Bird bird;

    public GamePhysics(Bird bird) {
        this.bird = bird;
    }

//    public void pressed(int x, int y) {
//        Circle c = new Circle(bird.bird.position.x, bird.bird.position.y, 24);
//        if (c.contains(x, Gdx.graphics.getHeight() - y) && bird.getState() == Bird.State.STANDING) {
//            float fx = x;
//            float fy = Gdx.graphics.getHeight() - y;
//            bird.bird.position.set(fx <= 25 ? 25 : fx, fy < 85 ? 85 : fy);
//        }
//    }

}
