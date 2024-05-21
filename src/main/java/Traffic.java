import java.util.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.net.URL;


public class Traffic
{

  //VARIABLES
  double spawnChance = .01;
  ArrayList<Car> trafficList;




  
  //DEFAULT CONSTRUCTORS
  public Traffic(){
    trafficList = new ArrayList<Car>();
  }


  




  //GETS A RANDOM COLOR CAR
  private Image randomCar(){
    int randomInt = (int)(Math.random()*3);
    Image returnThis = null;

    try {

      URL url = getClass().getResource("src/main/java/ORANGECAR.png");
      returnThis = ImageIO.read(url);
    } catch (Exception e) {
      System.out.println("PROBLEM WITH TRAFFIC IMAGE");
    }

    if(randomInt==0){
      try {

        URL url = getClass().getResource("/src/main/java/BLUECAR.png");
        returnThis = ImageIO.read(url);
      } catch (Exception e) {
        System.out.println("PROBLEM WITH TRAFFIC IMAGE");
      }


    }



    if(randomInt == 1){
      try {

        URL url = getClass().getResource("/src/main/java/REDCAR.png");
        returnThis = ImageIO.read(url);
      } catch (Exception e) {
        System.out.println("PROBLEM WITH TRAFFIC IMAGE");
      }
    }



    
    return returnThis;
  }







  
  //ADDS CAR TO LIST METHODS
  public void addCar(int pix){

    Car newCar = new Car(pix,-100,randomCar());
    //oriongally a y of -15
    trafficList.add(newCar);
    //System.out.println(trafficList);
  }

  public void setSpeed(int s){
    for(int i = 0; i<trafficList.size(); i++){
      trafficList.get(i).setSpeed(s);
    }
  }

  public void addCar(Car cur){
    trafficList.add(cur);
  }







  
  //RANDOM LANE PICKING METHOD
  public int randomLanePicker(){

    double randomDec = Math.random();
    int lane = (int)(Math.random()*4);
    if(randomDec<spawnChance){
      if(lane == 0){
        return 136;
        //return REPLACE THIS WITH THE LOCATION OF FIRST LANE
      }
      else if(lane ==1){
        return 272;
        //return REPLACE THIS WITH THE LOCATION OF LANE TWO
      }
      else if(lane ==2){
        return 408;
        //reuturn REPLACE THIS WITH LOCATION OF LANE THREE
      }
      else if(lane ==3){

        return 544;
      }

    }
    return -10;
    //spawns car in the middle of nowhere
  }







  
  //THIS NEEDS TO BE DONE
  public void drawTraffic(Graphics window){
    
      
    for(int i = 0; i<trafficList.size(); i++ ){
      trafficList.get(i).draw(window);
      if(trafficList.get(i).getY()>800){
        trafficList.remove(i);
        System.gc();
      }
      //System.out.println(trafficList.get(i));
    
      
    
    
    }
  }

  public void moveTraffic(String direction){
    for(Car cur : trafficList){
      cur.move(direction);
    }
  }


}