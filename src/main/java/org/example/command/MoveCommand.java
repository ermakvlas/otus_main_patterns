package org.example.command;

import org.example.model.Coordinates;
import org.example.model.Movable;

/**
 * Равномерное прямолинейное движение МТ на плоскости
 */
public class MoveCommand implements Command {

    private final Movable movable;

    public MoveCommand(Movable movable) {
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
