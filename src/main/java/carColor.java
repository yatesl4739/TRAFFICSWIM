import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Loads car color save file and loads all car color pictures
 * assigns and manages the colors of each car
 *
 * @version 5/25/24
 */
public class carColor{
  private File carDat= new File("src/main/java/carColor.txt");
  private Scanner scan = new Scanner(System.in);
  private int color;
  private Player player;
  public carColor(){
    scanCarColor();

    //highScore = scan.nextInt();
  }
  public int scanCarColor(){

    try {

        Scanner sc = new Scanner(carDat);

        while (sc.hasNextLine()) {
            color = sc.nextInt();
           // System.out.println(color);
        }
        sc.close();
    } 
    catch (FileNotFoundException e) {
        e.printStackTrace();
    }

    return color;
  }

  public carColor(int newColor){
    this.color = newColor;
  }

  public carColor(Player player){
    this.player = player;
  }

  public void setPlayer(Player player){
    this.player = player;
  }

  public Player getPlayer(){
    return player;
  }

  
  public void setColor(int newColor){


    color = newColor;
    writeColor(color);



  }

  public void writeColor(int colorToWrite){
    try {
      String cur = "" + colorToWrite;
      FileWriter myWriter = new FileWriter("src/main/java/carColor.txt");
      myWriter.write(cur);
      myWriter.close();
      //System.out.println("Successfully wrote to the file.");
    } catch (IOException e) {
      System.out.println("\n");
      System.out.println("An error occurred writing highScore to file carColor.txt.");
      System.out.println("\n");
      e.printStackTrace();
    }
  }





  public int getColor(){

    player.setCarColor(color);
    
    return color;
  }

  public void incrementColor(){
    if(color!=3){
      color++;
      setColor(color);
    }
    else{
      setColor(0);
    }
  }



}