import java.util.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.net.URL;


public class Traffic
{
  double spawnChance = .1;
  ArrayList<Car> trafficList;

  public Traffic(){
    trafficList = new ArrayList<Car>();
  }
  
  public Traffic(int cars){
    trafficList = new ArrayList<Car>();
    for(int i =0; i<cars; i++){
      Car basic = new Car(0,0,0,0);
      trafficList.add(basic);
    }
  }

  public void addCar(int pix){
    Car newCar = new Car(pix,-15);
    trafficList.add(newCar);
  }
  
  public void addCar(Car cur){
    trafficList.add(cur);
  }
  
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
    return -10000;
    //spawns car in the middle of nowhere
  }


  //THIS NEEDS TO BE DONE
  public void drawTraffic(){
    
  }

  public void moveTraffic(String direction){
    for(Car cur : trafficList){
      cur.move(direction);
    }
  }
  
  
}