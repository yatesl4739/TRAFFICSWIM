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
  public Player(int x, int y){
    this(x,y);
    
  }
  try
  {
    //coltons face
    URL url = getClass().getResource("WHITECAR.png");
    image = ImageIO.read(url);
  }
  catch(Exception e)
  {
    //feel free to do something here
  }

  
}