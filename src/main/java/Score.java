import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Manages and saves score system of the game
 * @version 5/21/24
 */
public class Score{
  private File ScoreDat= new File("src/main/java/Score.txt");
  private Scanner scan = new Scanner(System.in);
  private int highScore;
  private int currentScore;
  public Score(){
    scanHighScore();

    //highScore = scan.nextInt();
  }
  public int scanHighScore(){

    try {

        Scanner sc = new Scanner(ScoreDat);

        while (sc.hasNextLine()) {
            highScore = sc.nextInt();
            System.out.println(highScore);
        }
        sc.close();
    } 
    catch (FileNotFoundException e) {
        e.printStackTrace();
    }

    return highScore;
  }

  public Score(int highscore){
    this.highScore = highscore;
  }

  public void increment(int speed){
    currentScore+= speed;

    if(currentScore >= highScore){
      highScore = currentScore;
      writeHighScore(highScore);
    }

  }
  public void setHighScore(int score){


    highScore = score;

    writeHighScore(highScore);



  }

  public void writeHighScore(int highScoreToWrite){
    try {
      String cur = "" + highScoreToWrite;
      FileWriter myWriter = new FileWriter("src/main/java/Score.txt");
      myWriter.write(cur);
      myWriter.close();
      //System.out.println("Successfully wrote to the file.");
    } catch (IOException e) {
      System.out.println("\n");
      System.out.println("An error occurred writing highScore to file Score.txt.");
      System.out.println("\n");
      e.printStackTrace();
    }
  }



  public void setCurScore(int score){
    currentScore = score;
  }

  public int getCurScore(){
    return currentScore;
  }
  public int getHighScore(){
    return highScore;
  }


}