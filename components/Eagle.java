package components;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import utility.Resource;

public class Eagle {
  private static int eagleY, eagleX;
  private static final int eagleDiveY = Ground.GROUND_Y - 50, eagleFlyY = Ground.GROUND_Y - 200;
  private static final int diveSpeed = 15;
  private int flap = 0;
  
  private static boolean reached = false;
  public static boolean tryAgain = false;

  public static final int STILL = 1, FLYING = 2, DIVING = 3, PASS = 4;
  
  
  private static int state;

  static BufferedImage eagleImg = new Resource().getResourceImage("../images/fly_up.png");;
  static BufferedImage eagleSwoop = new Resource().getResourceImage("../images/fly_down.png");;

  public Eagle() {
    eagleX = 100;
    eagleY = eagleFlyY;
    state = 1;
  }

  public void create(Graphics g) {

    switch(state) {

      case STILL:
    	  g.drawImage(eagleImg, eagleX, eagleFlyY, null);
          break;

      case FLYING:
    	  if (flap <= 5) {
    		  g.drawImage(eagleImg, eagleX, eagleFlyY, null);
    	  }
    	  else {
    		  g.drawImage(eagleSwoop, eagleX, eagleFlyY, null);
    		  if(flap == 10) flap = 0;
    	  }
    	  flap += 1;
          break;

      case DIVING:
        if(eagleY < eagleDiveY && !reached) {
          g.drawImage(eagleSwoop, eagleX, eagleY += diveSpeed, null);
        } 
        else if(eagleY >= eagleDiveY && !reached) {
          reached = true;
          g.drawImage(eagleSwoop, eagleX, eagleDiveY, null);
        }         
        else if(reached) {      
          if(eagleY == eagleFlyY) {
        	  g.drawImage(eagleSwoop, eagleX, eagleFlyY, null);
              state = FLYING;
              reached = false;
          }    
          else
        	  g.drawImage(eagleImg, eagleX, eagleY -= diveSpeed, null);          
        }
        break;
      case PASS: 
        if(eagleX <= 800) {
        	if (flap <= 5) {
      		g.drawImage(eagleImg, eagleX += 10, eagleY, null);
	      	}
	      	else {
	      		g.drawImage(eagleSwoop, eagleX += 10, eagleY, null);
	      		if(flap == 10) flap = 0;
	      	}
	      	flap += 1;  
        }
        else {
        	g.setColor(Color.black);
            g.setFont(new Font("Courier New", Font.BOLD, 40));
            g.drawString("Game Over!", 250, 200);
            g.setFont(new Font("Courier New", Font.BOLD, 15));
            g.drawString("Press Space to play again", 255, 220);
    	    tryAgain = true;
        }
        break;     
    }
  }

  public void pass() {
    state = PASS;
  }

  public static Rectangle getEagle() {
    Rectangle eagle = new Rectangle();
    eagle.x = eagleX;
    eagle.y = eagleY;
    eagle.width = eagleImg.getWidth();
    eagle.height = eagleImg.getHeight();
    return eagle;
  }

  public void startFlying() {
	eagleX = 100;
	eagleY = eagleFlyY;
	reached = false;
	tryAgain = false;
	flap = 0;
    state = FLYING;
  }

  public void dive() {
    state = DIVING;
  }
  
  public void still() {
	state = STILL;
  }
  
  public boolean startOver() {
	    return tryAgain;
	  }

}