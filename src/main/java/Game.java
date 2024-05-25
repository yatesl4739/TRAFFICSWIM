import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.lang.Thread;
import java.io.*;
import java.net.URL;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;





public class Game extends Canvas implements KeyListener, Runnable,MouseListener {

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


  private carColor carColor = new carColor();

  private Difficulty difficulty = new Difficulty();
  private Score score = new Score();
  private Traffic traffic = new Traffic();
  private boolean[] keys;
  private BufferedImage back;
  private long tick = 0;
  private Font pricedown;


  private int difficultyNum;
  

  
  private Image blurryImage;
  private long startTick = 0;

  private boolean displayBoost = false;
  private long endTick;

  private Background background1;
  private Background background2;

  // GAME THING
  public Game() {
    

    // SETTING THE VARAIBLES
    setBackground(Color.WHITE);

    
    addMouseListener(this);
    
    

    
    keys = new boolean[9];

    player = new Player(354, 524);
    carColor.setPlayer(player);
    this.addKeyListener(this);

    background1 = new Background("src/main/java/background.png", 0, 1300);
    background2 = new Background("src/main/java/background.png", 0, 2900);

    try {
      URL url = getClass().getResource("/src/main/java/Blurry.png");
      blurryImage = ImageIO.read(url);

    }
    catch(Exception e){
      System.out.println("PROBLEM WITH start IMAGE");
     //feel free to do something here 
    }

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
    Graphics2D twoDGraph = (Graphics2D) window;
      if (back == null)
        back = (BufferedImage) (createImage(getWidth(), getHeight()));
      Graphics graphToBack = back.createGraphics();




    if(keys[8]){
      playing = true;
      lost = false;
      hitWall = false;
    }
    


    //THIS IS THE START MENU
    if(!playing && !lost && !hitWall){
      carColor.getColor();
      difficultyNum = difficulty.scanDifficulty();
      graphToBack.drawImage(blurryImage,(int)(getX()), (int)(getY()), 800,900, null);
      graphToBack.setFont(pricedown);
       graphToBack.setColor(Color.BLACK);
       graphToBack.drawString("THE SWIMULATOR", 210, 300);
      graphToBack.drawString("press SPACE to start playing", 70, 400);

      
      if(difficultyNum == 0){
        graphToBack.setColor(Color.GREEN);
        graphToBack.drawString("EASY", 70, 500);
        traffic.changeSpawnChance(.008);
      }
      else{
        graphToBack.setColor(Color.RED);
        graphToBack.drawString("EASY", 70, 500);
      }

      if(difficultyNum == 1){
        graphToBack.setColor(Color.GREEN);
        graphToBack.drawString("MEDIUM", 310, 500);
        traffic.changeSpawnChance(.01);
      }
      else{
        graphToBack.setColor(Color.RED);
        graphToBack.drawString("MEDIUM", 310, 500);
        
      }

      if(difficultyNum == 2){
        graphToBack.setColor(Color.GREEN);
        graphToBack.drawString("HARD", 610, 500);
        traffic.changeSpawnChance(.1);
      }
      else{
        graphToBack.setColor(Color.RED);
        graphToBack.drawString("HARD", 610, 500);
      }


    



      //START OF CAR COLOR SELECTOR
      if(carColor.getColor()==0){
        graphToBack.setColor(Color.WHITE);
        graphToBack.drawString("CAR COLOR", 280, 575);
      }
      else if(carColor.getColor()==1){
        graphToBack.setColor(Color.ORANGE);
        graphToBack.drawString("CAR COLOR", 280, 575);
      }
      else if(carColor.getColor()==2){
        graphToBack.setColor(Color.BLUE);
        graphToBack.drawString("CAR COLOR", 280, 575);
      }
      else if(carColor.getColor()==3){
        graphToBack.setColor(Color.RED);
        graphToBack.drawString("CAR COLOR", 280, 575);
      }

      
      
      
    }
    else if(!playing &&( lost || hitWall)){
      graphToBack.drawImage(blurryImage,(int)(getX()), (int)(getY()), 800,900, null);
      graphToBack.setFont(pricedown);
      graphToBack.setColor(Color.WHITE);
      graphToBack.drawString("YOU LOST", 300,300);
      graphToBack.drawString("Score: \n" + (int) (score.getCurScore()) / 10, 280, 450);
      graphToBack.drawString("press SPACE to play again",110,400);
    }
    else{


    

    // SMOOTHER GRAPHICS THING

  
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
      player.setPos(354,524);
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
      player.setPos(354, 524);
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


    endTick=  tick;
    //DETECT NEAR HITS:
    if(traffic.nearHitDetect(player) && endTick>startTick +100 ){
      startTick = tick;
      score.setCurScore(score.getCurScore()  + 2000);
      displayBoost = true;
    }
    
    if(displayBoost && endTick<startTick+100){
      graphToBack.setColor(Color.GREEN);
      graphToBack.setFont(pricedown);
      graphToBack.drawString("NEAR MISS + 200: \n", 290, 50);
    }
    else{
      displayBoost = false;
    }
    // SCORE BOARD
    graphToBack.setColor(Color.BLACK);
    graphToBack.setFont(pricedown);

    graphToBack.drawString("Score: \n" + (int) (score.getCurScore()) / 10, 20, 50);

    
    graphToBack.drawString("High Score: \n" + score.getHighScore() / 10, 20, 100);


   
    // if(!playing && hitWall){
    //   graphToBack.setColor(Color.RED);
    //   graphToBack.drawString("YOU HIT A WALL", 250,300);
    //   graphToBack.drawString("press SPACE to continue play",70,400);
    //   graphToBack.drawString("your score has not been reset",60,450);
    // }
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
      player.setPos(354, 524);
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

  public void mouseClicked(MouseEvent e)
    {
    if(!playing){
      if(e.getY()>300&&e.getY()<500){
      if(e.getX()>50&&e.getX()<190){
        difficulty.setDifficulty(0);
       
      }
      else if(e.getX()>200 && e.getX()<550){
        difficulty.setDifficulty(1);
       
      }
      else if(e.getX()>560 && e.getX()<750){
        difficulty.setDifficulty(2);
        
        
      }
      
     
      repaint();
    }
      if(e.getX()>275 && e.getX()<450&&e.getY()>550&&e.getY()<650){
        carColor.incrementColor();

      }
      repaint();
    }

    }
  
  public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }
    public void mousePressed(MouseEvent e) { }
    public void mouseReleased(MouseEvent e) { }

}