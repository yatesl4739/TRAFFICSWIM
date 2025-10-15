import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Manages and changes the difficulty of the game as time goes on
 * saves and loads the diffculty from a save file
 *
 * @version 5/25/24
 */
public class Difficulty{
  private File diffDat= new File("src/main/java/difficulty.txt");
  private Scanner scan = new Scanner(System.in);
  private int diff;
  public Difficulty(){
    scanDifficulty();

    //highScore = scan.nextInt();
  }
  public int scanDifficulty(){

    try {

        Scanner sc = new Scanner(diffDat);

        while (sc.hasNextLine()) {
            diff = sc.nextInt();
           // System.out.println(diff);
        }
        sc.close();
    } 
    catch (FileNotFoundException e) {
        e.printStackTrace();
    }

    return diff;
  }

  public Difficulty(int newDif){
    this.diff = newDif;
  }

  
  public void setDifficulty(int newDiff){


    diff = newDiff;

    writeDifficulty(diff);



  }

  public void writeDifficulty(int diffToWrite){
    try {
      String cur = "" + diffToWrite;
      FileWriter myWriter = new FileWriter("src/main/java/difficulty.txt");
      myWriter.write(cur);
      myWriter.close();
      //System.out.println("Successfully wrote to the file.");
    } catch (IOException e) {
      System.out.println("\n");
      System.out.println("An error occurred writing highScore to file difficulty.txt.");
      System.out.println("\n");
      e.printStackTrace();
    }
  }



 

  public int getDifficulty(){
    return diff;
  }



}