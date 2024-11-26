package org.example.hw02;

/**
 * Равномерное прямолинейное движение МТ на плоскости
 */
public class Move {

    private final Movable movable;

    public Move(Movable movable) {
        this.movable = movable;
    }

    public void execute() {
        if (movable == null) {
            throw new IllegalStateException("Movable object is null");
        }
        if (movable.getPosition() == null) {
            throw new IllegalStateException("There is no start position for movable object");
        }
        if (movable.getVelocity() == null) {
            throw new IllegalStateException("There is no velocity for movable object");
        }
        movable.setPosition(Coordinates.plus(movable.getPosition(), movable.getVelocity()));
    }
}
