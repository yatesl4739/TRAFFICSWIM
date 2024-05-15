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

public class Game extends Canvas implements KeyListener
{

  //defining variables
  private Player player;

  int ticker = 0;
  


  private Traffic traffic = new Traffic();
  private boolean[] keys;

  private BufferedImage back;

  public Game(){
    setBackground(Color.WHITE);

    keys = new boolean[8];

    
    player = new Player(344,464);
    this.addKeyListener(this);
    new Thread(this).start();

    setVisible(true);
    
  }

  public void update(Graphics window){
    paint(window);
  }

  public void paint(Graphics window){
    Graphics2D twoDGraph = (Graphics2D) window;


    if (back == null)
      back = (BufferedImage) (createImage(getWidth(), getHeight()));



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

@Override
public void keyTyped(KeyEvent e) {

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
}