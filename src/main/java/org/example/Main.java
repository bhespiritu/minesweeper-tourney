package org.example;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        JFrame frame = new JFrame();
        MineSweeperGame game = new MineSweeperGame(30,16,99);

        MineSweeperDisplayPane pane = new MineSweeperDisplayPane(game);

        frame.setContentPane(pane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}