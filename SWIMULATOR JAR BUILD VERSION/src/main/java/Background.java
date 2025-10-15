import java.awt.*;
import javax.imageio.ImageIO;
import java.net.URL;

/**
 * Creates the image of the background and scrolls it forever
 */
public class Background {

  //VARIABLES
    private int x = 0;
    private int y = -600;
    private int speed = 0; //original speed = 6
    private Image image;



  
  //DEFAULT CONSTRUCTORS
    public Background(){
        try {
            URL url = getClass().getResource("/background.png");
            image = ImageIO.read(url);
            
        }
        catch(Exception e){
            System.out.println("PROBLEM WITH BACKGROUND IMAGE");
           //feel free to do something here 
        }
    }
    
    public Background(String imageName) {
        try {
            URL url = getClass().getResource(imageName);
            image = ImageIO.read(url);
        } catch (Exception e) {
            System.out.println("PROBLEM WITH BACKGROUND IMAGE");
            e.printStackTrace();
        }
    }

    public Background(String imageName, int x, int y) {
        this(imageName);
        this.x = x;
        this.y = y;
    }

    public Background(String imageName, int x, int y, int s) {
        this(imageName, x, y);
        speed = s;
    }





  
  //SETTERS
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setPos(int x, int y) {
        this.setX(x);
        this.setY(y);
    }

    public void setSpeed(int s) {
        speed = s;
    }




  
  //MOVE METHODS AND DRAW METHODS
    public void increment() {
        this.setY(this.getY() + speed);
    }

    public void draw(Graphics window) {
        window.drawImage(image, (int)(getX()), (int)(getY()), null);
    }






  
  //GETTERS
    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public int getSpeed() {
        return speed;
    }

    public int getImageHeight() {
        return image.getHeight(null);
    }
}