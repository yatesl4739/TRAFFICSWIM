import java.util.*;
import java.awt.*;

public class Car implements Collideable
{
  public int xPos;
  public int yPos;
  public int speed;
  public int width;
  public int height;


  
  //default constructors
  public Car(){
    xPos = 10;
    yPos = 10;
    width = 120;
    height = 150;
    speed = 3;
  }

  public Car(int x, int y){
    this();
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
    System.out.println("ERROR CAR MOVE METHOD: INCORRECT INPUT");
    
    return false;
    
  }

  @Override 
  public boolean didCollide(Car other){
    if(this.getX() + this.getWidth() < other.getX() || this.getX() > other.getX() + other.getWidth()) {
      return false;
    }
    if(this.getY() + this.getHeight() < other.getY() || this.getY() > other.getY() + other.getHeight()) {
      return false;
    }
    return true;
  }



  
}