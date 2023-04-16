package components;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import utility.Resource;

public class Obstacles {
  private class Obstacle {
    BufferedImage image;
    int x;
    int y;
    boolean gotScore = false;

    Rectangle getObstacle() {
      Rectangle obstacle = new Rectangle();
      obstacle.x = x;
      obstacle.y = y;
      obstacle.width = image.getWidth();
      obstacle.height = image.getHeight();

      return obstacle;
    }
  }
  
  private int firstX;
  private int obstacleInterval;
  private int movementSpeed;
  private int rand;
  
  private ArrayList<BufferedImage> imageList;
  private ArrayList<Obstacle> obList;

  public Obstacles(int firstPos) {
	Random random = new Random();
    obList = new ArrayList<Obstacle>();
    imageList = new ArrayList<BufferedImage>();
    
    firstX = firstPos;
    obstacleInterval = 150;
    movementSpeed = 10;
    for(int i = 0; i < 50; i++) {
    	rand = (int) random.nextInt(10);
    	switch(rand) {
	    	case 1:
	    		imageList.add(new Resource().getResourceImage("../images/Cactus-1.png"));
	    		break;
	    	case 2:
	    		imageList.add(new Resource().getResourceImage("../images/Cactus-2.png"));
	    		break;
	    	case 3:
	    		imageList.add(new Resource().getResourceImage("../images/Cactus-1.png"));
	    		break;
	    	case 4:
	    		imageList.add(new Resource().getResourceImage("../images/fivepoint.png"));
	    		break;
	    	case 5:
	    		imageList.add(new Resource().getResourceImage("../images/Cactus-3.png"));
	    		break;
	    	case 6:
	    		imageList.add(new Resource().getResourceImage("../images/onepoint.jpg"));
	    		break;
	    	case 7:
	    		imageList.add(new Resource().getResourceImage("../images/fivepoint.png"));
	    		break;
	    	case 8:
	    		imageList.add(new Resource().getResourceImage("../images/tenpoint.png"));
	    		break;
	    	case 9:
	    		imageList.add(new Resource().getResourceImage("../images/twentypoint.png"));
	    		break;
	    	default:
	    		imageList.add(new Resource().getResourceImage("../images/onepoint.jpg"));
	    		break;
	    		
    	}
    }
    
    int x = 300;
    
    for(BufferedImage bi : imageList) {
      
      Obstacle ob = new Obstacle();
      
      ob.image = bi;
      ob.x = x;
      ob.y = Ground.GROUND_Y - bi.getHeight() + 5;
      x += obstacleInterval;
      
      obList.add(ob);
    }
  }
  
  public void update() {
    Iterator<Obstacle> looper = obList.iterator();
    
    Obstacle firstOb = looper.next();
    firstOb.x -= movementSpeed;
    
    while(looper.hasNext()) {
      Obstacle ob = looper.next();
      ob.x -= movementSpeed;
    }
    
    obList.get(obList.size() - 1);
    
    if(firstOb.x < -firstOb.image.getWidth()) {
      obList.remove(firstOb);
      firstOb.x = obList.get(obList.size() - 1).x + obstacleInterval;
      firstOb.gotScore = false;
      obList.add(firstOb);
    }
  }
  
  public void create(Graphics g) {
    for(Obstacle ob : obList) {
      // g.drawRect(ob.getObstacle().x, ob.getObstacle().y, ob.getObstacle().width, ob.getObstacle().height);
      if(!ob.gotScore) 
    	  g.drawImage(ob.image, ob.x, ob.y, null);
    }
  }
  
  public int getScore() {
	  int score = 0;
	  for(Obstacle ob : obList) {
	      if(Eagle.getEagle().intersects(ob.getObstacle()) && !ob.gotScore) {
	        // System.out.println("Eagle = " + Eagle.getEagle() + "\nObstacle = " + ob.getObstacle() + "\n\n");
	        if (ob.getObstacle().width == 24) {
		    	ob.gotScore = true;
		    	score  = 20;
		    }
		    else if (ob.getObstacle().width == 26){
		    	ob.gotScore = true;
		    	score  = 10;
		    }
		    else if (ob.getObstacle().width == 35){
		    	ob.gotScore = true;
		    	score  = 5;
		    }
		    else if (ob.getObstacle().width == 44){
		    	ob.gotScore = true;
		    	score  = 1;
		    } 
		    else {
		    	ob.gotScore = true;
		    	score = -5;
		    }
	      }
	  }
	return score;	
  }
  
  

  public void resume() {
	Random random = new Random();
    int x = firstX/2;   
    obList = new ArrayList<Obstacle>();
    imageList = new ArrayList<BufferedImage>();
    for(int i = 0; i < 50; i++) {
    	rand = (int) random.nextInt(10);
    	switch(rand) {
	    	case 1:
	    		imageList.add(new Resource().getResourceImage("../images/Cactus-1.png"));
	    		break;
	    	case 2:
	    		imageList.add(new Resource().getResourceImage("../images/Cactus-2.png"));
	    		break;
	    	case 3:
	    		imageList.add(new Resource().getResourceImage("../images/Cactus-1.png"));
	    		break;
	    	case 4:
	    		imageList.add(new Resource().getResourceImage("../images/fivepoint.png"));
	    		break;
	    	case 5:
	    		imageList.add(new Resource().getResourceImage("../images/Cactus-3.png"));
	    		break;
	    	case 6:
	    		imageList.add(new Resource().getResourceImage("../images/onepoint.jpg"));
	    		break;
	    	case 7:
	    		imageList.add(new Resource().getResourceImage("../images/fivepoint.png"));
	    		break;
	    	case 8:
	    		imageList.add(new Resource().getResourceImage("../images/tenpoint.png"));
	    		break;
	    	case 9:
	    		imageList.add(new Resource().getResourceImage("../images/twentypoint.png"));
	    		break;
	    	default:
	    		imageList.add(new Resource().getResourceImage("../images/onepoint.jpg"));
	    		break;
	    		
    	}
    }
    for(BufferedImage bi : imageList) {
      
      Obstacle ob = new Obstacle();
      
      ob.image = bi;
      ob.x = x;
      ob.y = Ground.GROUND_Y - bi.getHeight() + 5;
      x += obstacleInterval;
      
      obList.add(ob);
    }
  }
  
}