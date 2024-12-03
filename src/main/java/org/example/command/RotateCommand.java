package org.example.command;

import org.example.model.Rotatable;

/**
 * Поворот вокруг оси
 */
public class RotateCommand implements Command {

    private final Rotatable rotatable;

    public RotateCommand(Rotatable rotatable) {
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
