import javax.swing.*;
import java.awt.*;

class UserInterface {
  JFrame mainWindow = new JFrame("Eagle Dive");
  
  public static int WIDTH = 800;
  public static int HEIGHT = 500;
  
  public void createAndShowGUI() {
    mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    GamePanel gamePanel = new GamePanel();
    Container container = mainWindow.getContentPane();
    
    gamePanel.addKeyListener(gamePanel);
    gamePanel.setFocusable(true);
    
    container.setLayout(new BorderLayout());
    container.add(gamePanel, BorderLayout.CENTER);
    
    mainWindow.setSize(WIDTH, HEIGHT);
    mainWindow.setResizable(false);
    mainWindow.setVisible(true);
  }
  
  public static void main(String[] args) {
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        new UserInterface().createAndShowGUI();
      }
    });
  }
}