package com.codegym.task.task34.task3410.model;

import com.codegym.task.task34.task3410.controller.EventListener;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Model {

    public static int BOARD_CELL_SIZE = 20;
    private EventListener eventListener;
    private GameObjects gameObjects;
    private int currentLevel = 1;
//    private Path levelsTxt = Paths.get(System.getProperty("user.dir"),"com\\codegym\\task\\task34\\task3410\\res\\levels.txt");
//    private Path levelsTxt = Paths.get("C:\\Users\\raczk\\codegym\\11224209\\codegym-project\\out\\production\\codegym-project\\com\\codegym\\task\\task34\\task3410\\res\\levels.txt");
    LevelLoader levelLoader;

    public Model() {
        try {
            levelLoader = new LevelLoader(Paths.get(getClass().getResource("../res/levels.txt").toURI()));
        } catch (URISyntaxException e) {
        }
    }

    public void setEventListener(EventListener eventListener){
        this.eventListener = eventListener;
    }

    public GameObjects getGameObjects(){
        return gameObjects;
    }

    public void restartLevel(int level){
        gameObjects = levelLoader.getLevel(level);
    }

    public void restart(){
        restartLevel(currentLevel);
    }

    public void startNextLevel(){
        currentLevel++;
        restart();
    }

    public void move(Direction direction){
        Player player = gameObjects.getPlayer();
        if (checkWallCollision(player, direction) || checkBoxCollisionAndMoveIfAvailable(direction)) return;

        if (direction.equals(Direction.LEFT)){
            player.move(-BOARD_CELL_SIZE, 0);
        }
        else if (direction.equals(Direction.RIGHT)){
            player.move(BOARD_CELL_SIZE, 0);
        }
        else if (direction.equals(Direction.UP)){
            player.move(0, -BOARD_CELL_SIZE);
        }
        else if (direction.equals(Direction.DOWN)){
            player.move(0, BOARD_CELL_SIZE);
        }
        checkCompletion();
    }

    public boolean checkWallCollision(CollisionObject gameObject, Direction direction){
        for (GameObject object : gameObjects.getWalls()) {
            if (gameObject.isCollision(object, direction)) return true;
        }
        return false;
    }

    public boolean checkBoxCollisionAndMoveIfAvailable(Direction direction){
        for (Box box : gameObjects.getBoxes()) {
            if (gameObjects.getPlayer().isCollision(box, direction)) {
                if (checkWallCollision(box, direction))
                    return true;
                for (Box otherBox : gameObjects.getBoxes()) {
                    if (box.isCollision(otherBox, direction))
                        return true;
                }
                switch (direction) {
                    case LEFT: box.move(-BOARD_CELL_SIZE, 0); break;
                    case RIGHT: box.move(BOARD_CELL_SIZE, 0); break;
                    case UP: box.move(0, -BOARD_CELL_SIZE); break;
                    case DOWN: box.move(0, BOARD_CELL_SIZE);
                }
            }
        }
        return false;
    }

    public void checkCompletion(){
        int currentScore = 0;
        int completionScore = gameObjects.getStorageLocations().size();
        for (StorageLocation storageLocation : gameObjects.getStorageLocations()) {
            for (Box box : gameObjects.getBoxes()) {
                if (box.x == storageLocation.x && box.y == storageLocation.y) {
                    currentScore++;
                    break;
                }
            }
        }
        if (currentScore == completionScore) eventListener.levelCompleted(currentLevel);
    }

}
