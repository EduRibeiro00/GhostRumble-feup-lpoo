package com.aor.ghostrumble;

public class Ghost extends Enemy {

    public Ghost(int x, int y) {
        super(x, y, 700);
    }

    @Override
    protected MovementStrategy createMovStrategy() {
        return new DiagonalMovement();
    }

}
