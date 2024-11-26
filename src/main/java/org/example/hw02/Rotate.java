package org.example.hw02;

/**
 * Поворот вокруг оси
 */
public class Rotate {

    private final Rotatable rotatable;

    public Rotate(Rotatable rotatable) {
        this.rotatable = rotatable;
    }

    public void execute() {
        if (rotatable == null) {
            throw new IllegalStateException("There is no rotatable object");
        }
        if (rotatable.getDirectionsNum() == 0) {
            throw new IllegalStateException("Directions number is zero");
        }
        rotatable.setDirection((rotatable.getDirection() + rotatable.getAngularVelocity()) % rotatable.getDirectionsNum());
    }
}
