package SnakeGame;

import javax.swing.*;
import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener, KeyListener {

    private int[] snakexlength = new int[750];
    private int[] snakeylength = new int [750];
    private int lengthOfSnake =3;


    private boolean left = false;
    private boolean right = true;
    private boolean up = false;
    private boolean down = false;

    private int moves = 0;

    private int score =0;

    private  boolean gameOver =false;

    private ImageIcon snaketitle = new ImageIcon(("C:\\Users\\Amol\\IdeaProjects\\Snake 2D Game\\src\\SnakeGame\\snaketitle.jpg"));
    private ImageIcon leftmouth = new ImageIcon(("C:\\Users\\Amol\\IdeaProjects\\Snake 2D Game\\src\\SnakeGame\\leftmouth.png"));
    private ImageIcon rightmouth = new ImageIcon(("C:\\Users\\Amol\\IdeaProjects\\Snake 2D Game\\src\\SnakeGame\\rightmouth.png"));
    private ImageIcon upmouth = new ImageIcon(("C:\\Users\\Amol\\IdeaProjects\\Snake 2D Game\\src\\SnakeGame\\upmouth.png"));
    private ImageIcon downmouth = new ImageIcon(("C:\\Users\\Amol\\IdeaProjects\\Snake 2D Game\\src\\SnakeGame\\downmouth.png"));
    private ImageIcon snakeimage = new ImageIcon(("C:\\Users\\Amol\\IdeaProjects\\Snake 2D Game\\src\\SnakeGame\\snakeimage.png"));
    private ImageIcon enemy = new ImageIcon(("C:\\Users\\Amol\\IdeaProjects\\Snake 2D Game\\src\\SnakeGame\\enemy.png"));

    private Timer timer;
    private int delay = 100;

    private int xPos[] ={25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625,650,675,700,725,750,800,825,850};
    private int yPos[] ={75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600};

    private Random random = new Random();
    private int enemyX, enemyY;
    GamePanel(){
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);

        timer = new Timer(delay,this);
        timer.start();

        newEnemy();
    }

    @Override
    public void paint(Graphics g){
        super.paint(g); // To change body of generated methods, choose Tools: Templates

        g.setColor(Color.WHITE);
        g.drawRect(24,10,851,55);
        g.drawRect(24,70,851,560);

        snaketitle.paintIcon(this,g,25,11);

        g.setColor(Color.BLACK);
        g.fillRect(25,73,850,555);

        if(moves==0)
        {
            snakexlength [0] = 100;
            snakexlength [1] = 75;
            snakexlength [2] = 50;

            snakeylength [0] = 100;
            snakeylength [1] = 100;
            snakeylength [2] = 100;

        }
        if(left){
            leftmouth.paintIcon(this,g,snakexlength[0],snakeylength[0]);
        }
        if(right){
            rightmouth.paintIcon(this,g,snakexlength[0],snakeylength[0]);
        }
        if(up){
            upmouth.paintIcon(this,g,snakexlength[0],snakeylength[0]);
        }
        if(down){
            downmouth.paintIcon(this,g,snakexlength[0],snakeylength[0]);
        }

        for(int i=1; i<lengthOfSnake; i++){
            snakeimage.paintIcon(this,g,snakexlength[i],snakeylength[i]);
        }
        enemy.paintIcon(this,g,enemyX,enemyY);

        if(gameOver){
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial",Font.BOLD,50));
            g.drawString("Game Over",300,300);

            g.setFont(new Font("Arial",Font.PLAIN,20));
            g.drawString("Press SPACE to Restart",300,350);

        }

        g.setColor(Color.WHITE);
        g.setFont((new Font("Arial",Font.PLAIN,14)));
        g.drawString("Score : "+ score ,750,30);
        g.drawString("Length : "+lengthOfSnake,750,50);


        g.dispose();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for(int i=lengthOfSnake-1; i>0;i--){
            snakexlength[i] =snakexlength[i-1];
            snakeylength[i] =snakeylength[i-1];
        }
        if(left){
            snakexlength[0] = snakexlength[0]-25;
        }
        if(right){
            snakexlength[0] = snakexlength[0]+25;
        }
        if(up){
            snakeylength[0] = snakeylength[0]-25;
        }
        if(down){
            snakeylength[0] = snakeylength[0]+25;
        }

        if(snakexlength[0]>850)snakexlength[0]=25;
        if(snakexlength[0]<25)snakexlength[0]=850;

        if(snakeylength[0]>600)snakeylength[0]=75;
        if(snakeylength[0]<75)snakeylength[0]=600;

        colliedWithEnemy();
        colliedWithBody();
         repaint();

    }


    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE){

            restart();

        }

       if( e.getKeyCode() == KeyEvent.VK_LEFT  && (!right) ){
           left = true;
           right = false;
           up = false;
           down= false;
           moves++;
       }

        if( e.getKeyCode() == KeyEvent.VK_RIGHT  && (!left)){
            left = false;
            right = true;
            up = false;
            down= false;
            moves++;
        }

        if( e.getKeyCode() == KeyEvent.VK_UP  && (!down)){
            left = false;
            right = false;
            up = true;
            down= false;
            moves++;
        }
        if( e.getKeyCode() == KeyEvent.VK_DOWN  && (!up)){
            left = false;
            right = false;
            up = false;
            down= true;
            moves++;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    @Override
    public void keyTyped(KeyEvent e) {

    }
    private void newEnemy() {
        enemyX=xPos[random.nextInt(32)];
        enemyY=yPos[random.nextInt(22)];

        for(int i=lengthOfSnake-1; i>=0; i--){
            if(snakexlength[i] ==enemyX && snakeylength[i] ==enemyY){
                newEnemy();
            }

        }
    }
    private void colliedWithEnemy()
    {
        if(snakexlength[0] ==enemyX && snakeylength[0] ==enemyY){
            newEnemy();
            lengthOfSnake++;
            score++;
        }
    }
    private void colliedWithBody(){
        for(int i=lengthOfSnake-1; i>0; i--){
            if(snakexlength[i] == snakexlength[0] && snakeylength[i] == snakeylength[0] ){
                timer.stop();
                gameOver = true;
            }
        }
    }
    private void restart(){
        gameOver = false;
        moves =0;
        score = 0;
        lengthOfSnake =1;
        left=false;
        right = false;
        up = false;
        down=false;
        timer.start();

        repaint();

    }
}
