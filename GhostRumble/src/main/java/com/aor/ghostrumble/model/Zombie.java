package com.aor.ghostrumble.model;

public class Zombie extends Enemy {

    public Zombie(int x, int y) {
        super(x, y, 800);
    }

    @Override
    protected MovementStrategy createMovStrategy() {
        return new LinearMovement();
    }

}