package com.codegym.task.task34.task3410.model;

import java.awt.*;

public class Wall extends CollisionObject{
    public Wall(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.drawRect(x, y, width, height);
        graphics.setColor(Color.LIGHT_GRAY);
    }
}
