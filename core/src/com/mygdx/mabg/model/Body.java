package com.mygdx.mabg.model;


import com.mygdx.mabg.view.Polygon;

public class Body {
    public final Vector position = new Vector();
    public final Vector velocity = new Vector();
    public final Vector force = new Vector();
    public float angularVelocity;
    public final Polygon shape;
    public float torque;
    public float orient;
    public float mass, invMass, inertia, invInertia;
    public float restitution;
    public float staticFriction;
    public float dynamicFriction;

    public Body(Polygon shape, int x, int y) {
        this.shape = shape;

        position.set(x, y);
        velocity.set(0,0);
        angularVelocity = 0;
        torque = 0;
        orient = ImpMath.random( -ImpMath.PI, ImpMath.PI );
        shape.body = this;
        shape.initialize();
    }

    public void applyImpulse( Vector impulse, Vector contactVector )
    {
        velocity.addsi( impulse, invMass );
        angularVelocity += invInertia * Vector.cross( contactVector, impulse );
    }

    public void setStatic()
    {
        inertia = 0.0f;
        invInertia = 0.0f;
        mass = 0.0f;
        invMass = 0.0f;
    }

    public void setOrient( float radians )
    {
        orient = radians;
        shape.setOrient( radians );
    }
}
