package com.codegym.task.task34.task3410.controller;

import com.codegym.task.task34.task3410.model.Direction;
import com.codegym.task.task34.task3410.model.GameObjects;
import com.codegym.task.task34.task3410.model.LevelLoader;
import com.codegym.task.task34.task3410.model.Model;
import com.codegym.task.task34.task3410.view.View;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Controller implements EventListener{

    private View view;
    private Model model;

    public Controller() {
        view = new View(this);
        model = new Model();
        view.init();
        model.restart();
        model.setEventListener(this);
        view.setEventListener(this);
    }

    public static void main(String[] args) {

        new Controller();

    }

    @Override
    public void move(Direction direction) {
        model.move(direction);
        view.update();
    }

    @Override
    public void restart() {
        model.restart();
        view.update();
    }

    @Override
    public void startNextLevel() {
        model.startNextLevel();
        view.update();
    }

    @Override
    public void levelCompleted(int level) {
        view.completed(level);
    }

    public GameObjects getGameObjects(){
        return model.getGameObjects();
    }
}
