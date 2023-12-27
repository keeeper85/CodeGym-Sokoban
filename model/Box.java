package com.codegym.task.task34.task3410.model;

import java.awt.*;

public class Box extends CollisionObject implements Movable{
    public Box(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.ORANGE);
        graphics.drawRect(x, y, width, height);
    }

    @Override
    public void move(int x, int y) {
        int shiftX = this.x + x;
        int shiftY = this.y + y;

        this.setX(shiftX);
        this.setY(shiftY);
    }
}
