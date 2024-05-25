import java.util.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.net.URL;


public class Car implements Collideable
{
  public int xPos;
  public int yPos;
  public int speed;
  public int width;
  public int height;
  public Image mainImage;


  
  //default constructors
  public Car(){
    xPos = 10;
    yPos = 10;
    width = 80;
    height = 150;
    speed = 3;
    try{
    URL url = getClass().getResource("/src/main/java/COPCAR.png");
    mainImage = ImageIO.read(url);
    }
    catch (Exception e) {
      System.out.println("PROBLEM WITH CAR IMAGE");
      //feel free to do something here
    }

    
  }

  public Car(int x, int y){
    this();
    xPos = x;
    yPos = y;
    
    
  }

  public Car(int x, int y, Image imgParm){
    this();
    try {

     
      mainImage = imgParm;
      
    } catch (Exception e) {
      System.out.println("PROBLEM WITH CAR IMAGE");
      //feel free to do something here
    }
    xPos = x;
    yPos = y;
  }

  public Car(int x, int y, int w, int h){
    this(x,y);
    width = w;
    height = h;
  }

  public Car(int x, int y, int w, int h, int s){
    xPos = x;
    yPos = y;
    width = w;
    height = h;
    speed = s;
  }




  
  
  public void setWidth(int w){
    width = w;
  }

  public void setHeight(int h){
    height = h;
  }
  
  public int getWidth(){
    return width;
  }

  public int getHeight(){
    return height;
  }





  
// Set/Get position constructors
  public void setPos(int x, int y){
    xPos = x;
    yPos = y;
  }
  
  public void setX(int x){
    xPos = x;
  }

  public void setY(int y){
    yPos = y;
  }

  public int getX(){
    return xPos;
  }

  public int getY(){
    return yPos;
  }





  
  // Set/Get speed constructors
  public void setSpeed(int s){
    speed = s;
  }

  public int getSpeed(){
    return speed;
  }

  public boolean move(String direction){
    direction = direction.toLowerCase();
    if(direction.equals("down")){
      yPos += speed;
      return true;
    }
    else if(direction.equals("up")){
      yPos -= speed;
      return true;
    }
    else if (direction.equals("right")){
      xPos += speed;
      return true;
    }
    else if(direction.equals("left")){
      xPos -= speed;
      return true;
    }

    //throw error if no matching move string is passed
    //System.out.println("ERROR CAR MOVE METHOD: INCORRECT INPUT");
    
    return false;
    
  }

  @Override 
  public boolean didCollide(Player other){
    //the 20s make the hit boxes slightly too small cause otherwise its too hard. We allow for a tiny bit of collision.

    //when the car is turning, adjust for the turning by making hitboxes vertically smaller
    if(other.isTurning){
     
      if(this.getY()+40>other.getY()+other.getHeight() || this.getX()+this.getWidth()-40 < other.getX() || this.getX()+40>other.getX()+other.getWidth() || this.getY()+this.getHeight()-40<other.getY()){
      return false;
      }
      return true;
    }
    else{
      
      if(this.getY()-20>other.getY()+other.getHeight() || this.getX()+this.getWidth()-20 < other.getX() || this.getX()+20>other.getX()+other.getWidth() || this.getY()+this.getHeight()-20<other.getY()){
        return false;
      }
      return true;
    }
  }


  //THIS IS NOT YET IMPLEMENTED BUT IS JUST A COLLISION CHECKER WITHOUT THE 20, WHEN THIS IS TRIGGERED THE CARS HIT BUT ITS NOT GAME OVER
  public boolean nearMiss(Car other){
    if(this.getY()>other.getY()+other.getHeight() || this.getX()+this.getWidth() < other.getX() || this.getX()>other.getX()+other.getWidth() || this.getY()+this.getHeight()<other.getY()){
      return false;
    }
    return true;
  }

  

  public void draw( Graphics window )
    {

      window.drawImage(mainImage,(int)(getX()),(int)(getY()),getWidth(),getHeight(), null);
    }

  @Override
  public String toString(){
    String sendThisOut = "";
    sendThisOut += "POS: " + xPos + ", " + yPos + " "+ speed + " "+width + " " + height + " ";
    return sendThisOut;
  }


  
}