package com.codegym.task.task34.task3410.model;

import java.awt.*;

public class Player extends CollisionObject implements Movable{
    public Player(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.YELLOW);
        graphics.drawOval(x, y, width, height);
    }

    @Override
    public void move(int x, int y) {
        int shiftX = this.x + x;
        int shiftY = this.y + y;

        this.setX(shiftX);
        this.setY(shiftY);
    }
}
