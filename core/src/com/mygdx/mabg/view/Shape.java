package com.mygdx.mabg.view;

import com.mygdx.mabg.model.Body;
import com.mygdx.mabg.model.Matrix;

public abstract class Shape {
    public enum Type
    {
        Circle, Poly
    }

    public Body body;
    public float radius;
    public final Matrix u = new Matrix();

    public Shape()
    {
    }


    public abstract void initialize();

    public abstract void computeMass( float density );

    public abstract void setOrient( float radians );

    public abstract Type getType();
}
