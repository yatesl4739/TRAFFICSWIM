import java.util.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.net.URL;


public class Player extends Car
{

  private Image image;

  public Player(){
    this(344,464);
    
  }
  public Player(int x, int y) {
    super(x, y);


    try {

      URL url = getClass().getResource("WHITECAR.png");
      image = ImageIO.read(url);
    } catch (Exception e) {
      System.out.println("PROBLEM WITH PLAYER IMAGE");
      //feel free to do something here
    }
  }
  
}