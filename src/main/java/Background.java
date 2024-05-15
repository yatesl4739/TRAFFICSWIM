import java.util.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.net.URL;

public class Background
{
  int x = 0;
  int y = 0; 
  int speed = 2;
  Image image; 


  public Background(){
    try {

      URL url = getClass().getResource("background.png");
      image = ImageIO.read(url);
    } catch (Exception e) {
      System.out.println("PROBLEM WITH BACKGROUND IMAGE");
      //feel free to do something here
    }
    
  }

  public Background(Image img){
    try {

      
      image = img;
    } catch (Exception e) {
      System.out.println("PROBLEM WITH BACKGROUND IMAGE");
      //feel free to do something here
    }

  }
  public Background(int x, int y){
    this();
    this.x = x;
    this.y = y;
  }

  public Background(int x, int y, int s){
    this(x,y);
    speed = s;
  }


  public void setX(int x){
    this.x = x;
    
  }
  public void setY(int y){
    this.y = y;
  }
  public void setPos(int x, int y){
    this.setX(x);
    this.setY(y);
  }

  public void setSpeed(int s){
    speed = s;
  }


  //basically a move method
  public void increment(){
    this.setY(this.getY()+speed);
  }
  public void move(){
    increment();
  }

  public void draw( Graphics window )
    {

      window.drawImage(image,getX(),getY(),null);
    }


  //GETTESR

  public int getY(){
    return y;
  }
  public int getX(){
    return x;
  }

  public int getSpeed(){
    return speed;
  }
  
}