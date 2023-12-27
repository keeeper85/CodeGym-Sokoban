package com.codegym.task.task34.task3410.model;

import java.awt.*;

public class StorageLocation extends GameObject{


    public StorageLocation(int x, int y) {
        super(x, y);
        width = 2;
        height = 2;
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.drawOval(x, y, width, height);
        graphics.setColor(Color.RED);
    }
}
