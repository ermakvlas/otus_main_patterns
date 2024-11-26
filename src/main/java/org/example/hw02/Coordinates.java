package org.example.hw02;

public class Coordinates {
    private int x;
    private int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public static Coordinates plus(Coordinates startPosition, Coordinates delta) {
        return new Coordinates(startPosition.getX() + delta.getX(), startPosition.getY() + delta.getY());
    }
}
