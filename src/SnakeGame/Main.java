package SnakeGame;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Snake Game");
        frame.setBounds(50,10,900,680);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        GamePanel panel = new GamePanel();
        panel.setBackground(Color.DARK_GRAY);


        frame.add(panel);


        frame.setVisible(true);

    }
}
