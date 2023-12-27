package com.codegym.task.task34.task3410.view;

import com.codegym.task.task34.task3410.controller.Controller;
import com.codegym.task.task34.task3410.controller.EventListener;
import com.codegym.task.task34.task3410.model.GameObjects;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame {

    private Controller controller;
    private Board board;

    public View(Controller controller) throws HeadlessException {
        this.controller = controller;
    }

    public void init() {
        board = new Board(this);
        add(board);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setTitle("Sokoban");
        setVisible(true);
    }

    public void setEventListener(EventListener eventListener){
        board.setEventListener(eventListener);
    }

    public void update(){
        board.repaint();
    }

    public GameObjects getGameObjects(){
        return controller.getGameObjects();
    }

    public void completed(int level){
        this.update();
        JOptionPane.showMessageDialog(this, "Congratulations! You have completed level " + level);
        controller.startNextLevel();
    }
}
