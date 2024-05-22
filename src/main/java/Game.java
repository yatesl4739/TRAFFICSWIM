import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.lang.Thread;
import java.io.*;





public class Game extends Canvas implements KeyListener, Runnable {

  // DEFINING VARIABLES
  private Player player;
  // private Background desertBackground1;
  // private Background desertBackground2;
  // private Background currentBackground;
  // private Background nextBackground;

  //play status
  private boolean playing = false;
  private boolean lost = false;
  private boolean hitWall = false;
  
  private Score score = new Score();
  private Traffic traffic = new Traffic();
  private boolean[] keys;
  private BufferedImage back;
  private long tick = 0;
  private Font pricedown;

  private Background background1;
  private Background background2;

  // GAME THING
  public Game() {

    // SETTING THE VARAIBLES
    setBackground(Color.WHITE);
    keys = new boolean[9];

    player = new Player(344, 524);
    this.addKeyListener(this);

    background1 = new Background("src/main/java/background.png", 0, 1300);
    background2 = new Background("src/main/java/background.png", 0, 2900);

    // desertBackground1 = new Background("src/main/java/background.png");
    // desertBackground2 = new Background("src/main/java/background2.png", 0,
    // -desertBackground1.getImageHeight());
    // currentBackground = desertBackground1;

    // nextBackground = desertBackground2;

    // CREATE AN INITALIZE FONT:
    try {
      //create the font to use. Specify the size!
      pricedown = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/java/pricedown.otf")).deriveFont(50f);
      GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
      //register the font
      ge.registerFont(pricedown);
    } catch (IOException e) {
      e.printStackTrace();
    } catch(FontFormatException e) {
      e.printStackTrace();
    }

    new Thread(this).start();
    setVisible(true);
  }

  public void update(Graphics window) {
    paint(window);
  }

  // PAINT
  boolean new1 = false;

  public void paint(Graphics window) {


    

    // SMOOTHER GRAPHICS THING

    Graphics2D twoDGraph = (Graphics2D) window;
    if (back == null)
      back = (BufferedImage) (createImage(getWidth(), getHeight()));
    Graphics graphToBack = back.createGraphics();

    
    // TICK INCREMENT
    tick++;

    // TICK BACKGROUND RAMP UP

    if ((background1.getSpeed() == 0 && keys[8]) || playing) {
      
      //set play status
      playing = true;
      lost = false;
      hitWall = false;
      
      if (tick % 100 == 50 && background1.getSpeed() < 2) {
        background1.setSpeed(background1.getSpeed() + 1);
        background2.setSpeed(background2.getSpeed() + 1);
      } else if (tick % 300 == 100 && background1.getSpeed() < 3) {
        background1.setSpeed(background1.getSpeed() + 1);
        background2.setSpeed(background2.getSpeed() + 1);
      }
    }



    //CHECK FOR COLISIONS:
    if(traffic.didCollideWithPlayer(player)){
      player.setPos(344,524);
      playing = false;
      lost = true;
      
      
      background1.setSpeed(0);
      background2.setSpeed(0);
      
      
      traffic.removeTraffic();
    }


    //INFINATE BACKGROUND

    
    // boolean new1 = false;
    background1.draw(graphToBack);
    background1.increment();

    background2.draw(graphToBack);
    background2.increment();

    if (background1.getY() > 900) {
      background1.setY(background2.getY() - 1600);
      // 1600 is height of background
    }
    if (background2.getY() > 900) {
      background2.setY(background1.getY() - 1600);
      // 1600 is height of background
    }




    //DRAW PLAYER
    player.draw(graphToBack);

    
    // INCREMENTS SCORE
    score.increment(background1.getSpeed());

    
    // BOUNDARIES
    if (player.getX() < 92 || player.getX() + player.getWidth() > 698) {
      player.setPos(344, 524);
      background1.setSpeed(0);
      background2.setSpeed(0);
      playing = false;
      hitWall = true;
      traffic.removeTraffic();
    } else if (keys[0] && player.getX() > 90 && playing) {
      // 119 IS HITTING THE SIDE
      player.move("left");
      player.isTurning = true;
    }

    else if (keys[1] && player.getX() + player.getWidth() < 700 && playing) {
      // 673 IS HITTING THE SIDE
      player.move("right");
      player.isTurning = true;

    }

    else {
      player.move("straight");
      player.isTurning = false;
      //makes player stop rotating when not turning
    }

    // TRAFFIC LOGIC AND CODE
    if (playing) {
      traffic.setSpeed(background1.getSpeed());
      int lane = traffic.randomLanePicker();
      if (lane > 0) {
        traffic.addCar(lane);
        System.out.println("CAR SPAWNED");
      }
      traffic.drawTraffic(graphToBack);
      // System.out.println("PRINTING WORKS");
      traffic.moveTraffic("down");
    }

    // SCORE BOARD
    graphToBack.setColor(Color.BLACK);
    graphToBack.setFont(pricedown);

    graphToBack.drawString("Score: \n" + (int) (score.getCurScore()) / 10, 20, 50);

    
    graphToBack.drawString("High Score: \n" + score.getHighScore() / 10, 20, 100);


    if(!playing && !lost && !hitWall){
       graphToBack.setColor(Color.RED);
       graphToBack.drawString("THE SWIMULATOR", 210, 300);
      graphToBack.drawString("press SPACE to start playing", 70, 400);
    }
    if(!playing && lost){
      graphToBack.setColor(Color.RED);
      graphToBack.drawString("YOU LOST", 300,300);
      graphToBack.drawString("Score: \n" + (int) (score.getCurScore()) / 10, 280, 450);
      graphToBack.drawString("press SPACE to play again",110,400);
    }
    if(!playing && hitWall){
      graphToBack.setColor(Color.RED);
      graphToBack.drawString("YOU HIT A WALL", 250,300);
      graphToBack.drawString("press SPACE to continue play",70,400);
      graphToBack.drawString("your score has not been reset",60,450);
    }

    
    twoDGraph.drawImage(back, null, 0, 0);
  }
  
  // END OF PAINT METHOD

  // KEYS STUFF
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
    if (e.getKeyCode() == 32) {
      keys[8] = true;
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
    if (e.getKeyCode() == 32) {
      score.setCurScore(0);
      keys[8] = false;
    }
    if (e.getKeyCode() == 27) {
      background1.setSpeed(0);
      background2.setSpeed(0);
      playing = false;
      player.setPos(344, 524);
      traffic.removeTraffic();
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