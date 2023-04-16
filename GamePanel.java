import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color;

import components.Ground;
import components.Eagle;
import components.Obstacles;

class GamePanel extends JPanel implements KeyListener, Runnable {
  
  public static int WIDTH;
  public static int HEIGHT;
  private Thread animator;
  
  private boolean running = false;
  private boolean gameOver = false;
  
  Ground ground;
  Eagle eagle;
  Obstacles obstacles;

  private float score;
  private float time;
  private static float highScore = 0;
  
  public GamePanel() {
    WIDTH = UserInterface.WIDTH;
    HEIGHT = UserInterface.HEIGHT;
    
    ground = new Ground(HEIGHT);
    eagle = new Eagle();
    obstacles = new Obstacles((int)(WIDTH * 1.5));

    score = 0;
    time = 50;
    
    setSize(WIDTH, HEIGHT);
    setVisible(true);
  }
  
  public void paint(Graphics g) {
    super.paint(g);
    g.setColor(Color.white);
    g.fillRect(0, 0, 800, 500);
    g.setColor(Color.black);
    g.setFont(new Font("Courier New", Font.BOLD, 20));
    g.drawString("Score: "+ String.format("%.1f", score), 500, 50);
    g.drawString("Time left:" + String.format("%.1f", time), 500, 20);
    g.setFont(new Font("Times New Roman", Font.BOLD, 25));
    g.drawString("High Score: "+ String.format("%.1f", highScore), 50, 20);
    ground.create(g);
    eagle.create(g);
    obstacles.create(g);
  }
  
  public void run() {
    running = true;
    while(running) {
      updateGame();
      repaint();      
      try {
        Thread.sleep(50);
      } catch(InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
  
  public void updateGame() {
    ground.update();
    obstacles.update();
    if (time <= 0.05 ) {
    	time = 0;
    	eagle.pass();
    	if(score > highScore) highScore = score;
    }
    else {
    	score += obstacles.getScore();
    	time -= 0.1;
    }
    // Game Over
    if (eagle.startOver()) {
    	repaint();
  	    running = false;
 	    gameOver = true;
    }
  }

  public void reset() {
    score = 0;
    time = 50;
    obstacles.resume();
    gameOver = false;
    eagle.still();
  }
  
  public void keyTyped(KeyEvent e) {
    // System.out.println(e);
    if(e.getKeyChar() == ' ') {   
      if(gameOver) {
    	  reset();
      }
      if (animator == null || !running) {
        System.out.println("Begin");        
        animator = new Thread(this);
        animator.start();     
        eagle.startFlying(); 
      } else {
        eagle.dive();
      }
    }
  }
  
  public void keyPressed(KeyEvent e) {
    // System.out.println("keyPressed: "+e);
  }
  
  public void keyReleased(KeyEvent e) {
    // System.out.println("keyReleased: "+e);
  }
}