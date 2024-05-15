import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Canvas;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import static java.lang.Character.*;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.lang.Thread;

public class Game extends Canvas implements KeyListener, Runnable
{

  //defining variables
  private Player player;
  private Background desertTest = new Background();
  private Background backgroundUP;
  int ticker = 0;
  


  private Traffic traffic = new Traffic();
  private boolean[] keys;

  private BufferedImage back;

  public Game(){
    setBackground(Color.WHITE);

    keys = new boolean[8];

    
    player = new Player(344,424);
    this.addKeyListener(this);
   
    new Thread(this).start();
    setVisible(true);
    
  }

  public void update(Graphics window){
    paint(window);
  }



  //INFINATE BACKGROUND::
  boolean new1 = false;
  public void paint(Graphics window){
    Graphics2D twoDGraph = (Graphics2D) window;


    if (back == null)
      back = (BufferedImage) (createImage(getWidth(), getHeight()));

    Graphics graphToBack = back.createGraphics();

    desertTest.draw(graphToBack);
    desertTest.increment();
    if(desertTest.getY()>-300){
      if(!new1){
        backgroundUP = new Background(0,-1900);
        new1 = !new1;
      }
      backgroundUP.draw(graphToBack);
      backgroundUP.increment();
    }
    if(desertTest.getY()>600){
      desertTest = backgroundUP;
      new1 = false;
    }
    

    // DONE INFINATE BACKGROUND

    player.draw(graphToBack);

    if(keys[0]){
      if(player.getX()>119){
        player.move("left");
      }
    }
    if(keys[1]){
      if(player.getX()+player.getWidth()<673){
        
      player.move("right");
      }
    }
   // System.out.println(player.getX()+player.getWidth());
    
    //PUT EVERYTHING HERE!!!



    twoDGraph.drawImage(back, null, 0, 0);


    
    
  }



  public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_LEFT) {
      keys[0] = true;
    }
    if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
      keys[1] = true;
    }
    if (e.getKeyCode() == KeyEvent.VK_UP) {
      keys[2] = true;
    }
    if (e.getKeyCode() == KeyEvent.VK_DOWN) {
      keys[3] = true;
    }

    
    if (e.getKeyCode() == 65) {
      keys[0] = true;
    }
    if (e.getKeyCode() == 68) {
      keys[1] = true;
    }
    if (e.getKeyCode() == 87) {
      keys[2] = true;
    }
    if (e.getKeyCode() == 83) {
      keys[3] = true;
    }
    

    
    repaint();
  }


  public void keyReleased(KeyEvent e) {
  if (e.getKeyCode() == KeyEvent.VK_LEFT) {
    keys[0] = false;
  }
  if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
    keys[1] = false;
  }
  if (e.getKeyCode() == KeyEvent.VK_UP) {
    keys[2] = false;
  }
  if (e.getKeyCode() == KeyEvent.VK_DOWN) {
    keys[3] = false;
  }



    if (e.getKeyCode() == 65) {
      keys[0] = false;
    }
    if (e.getKeyCode() == 68) {
      keys[1] = false;
    }
    if (e.getKeyCode() == 87) {
      keys[2] = false;
    }
    if (e.getKeyCode() == 83) {
      keys[3] = false;
    }


    repaint();
    
    

  }

  public void run() {
    try {
      while (true) {
        Thread.currentThread().sleep(5);
        repaint();
      }
    } catch (Exception e) {

    }
  }
  
@Override
public void keyTyped(KeyEvent e) {

}
  
}