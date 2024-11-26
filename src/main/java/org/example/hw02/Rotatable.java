package org.example.hw02;

/**
 * Вращающийся вокруг своей оси объект
 */
public interface Rotatable {

    int getDirection();

    void setDirection(int direction);

    int getAngularVelocity();

    int getDirectionsNum();
}
