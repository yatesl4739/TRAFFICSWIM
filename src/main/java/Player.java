import java.util.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.net.URL;
import java.awt.geom.AffineTransform;

public class Player extends Car {
  // VARIABLES
  private Image image;
  private double rotationAngle;
  public boolean isTurning = false;

  // DEFAULT CONSTRUCTORS
  public Player() {
    this(344, 464);

  }

  public Player(int x, int y) {
    super(x, y);

    try {

      URL url = getClass().getResource("/src/main/java/WHITECAR.png");
      image = ImageIO.read(url);
    } catch (Exception e) {
      System.out.println("PROBLEM WITH PLAYER IMAGE");

      // feel free to do something here :)

    }
  }

  // DRAW METHOD
  public void draw(Graphics window) {
    Graphics2D g2d = (Graphics2D) window;

    double centerX = getX() + getWidth() / 2;
    double centerY = getY() + getHeight() / 2;

    g2d.rotate(rotationAngle, centerX, centerY);

    g2d.drawImage(image, (int) (getX()), (int) (getY()), getWidth(), getHeight(), null);

    g2d.rotate(-rotationAngle, centerX, centerY);

  }

  public void setCarColor(int newColor) {
    String pathOfCarColor = "/src/main/java/";
    newColor = newColor;
    if (newColor == 0) {
      pathOfCarColor += "WHITECAR.png";

    } else if (newColor == 1) {
      pathOfCarColor += "ORANGECAR.png";
    } else if (newColor == 2) {
      pathOfCarColor += "BLUECAR.png";
    } else{
      pathOfCarColor += "REDCAR.png";
    }


    try {

      URL url = getClass().getResource(pathOfCarColor);
      image = ImageIO.read(url);
    } catch (Exception e) {
      System.out.println("PROBLEM WITH PLAYER IMAGE");

      // feel free to do something here :)

    }
    

    

  }

  // MOVE METHOD THAT TILTS CAR WHEN GOING LEFT OR RIGHT
  @Override
  public boolean move(String direction) {
    boolean moved = super.move(direction);

    if (direction.equals("left")) {
      rotationAngle = Math.toRadians(-7);
    } else if (direction.equals("right")) {
      rotationAngle = Math.toRadians(7);
    } else if (direction.equals("straight")) {
      rotationAngle = 0;
    }

    

    return moved;
  }

}