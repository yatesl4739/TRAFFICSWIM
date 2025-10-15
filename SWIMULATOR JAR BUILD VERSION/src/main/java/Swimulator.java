import javax.swing.JFrame;

import java.awt.Component;

/**
 * Manages window. Creates window and runs the game within the JFrame
 *
 * @version 5/22/24
 */
public class Swimulator extends JFrame
{
  public static final int WIDTH = 800;
  public static final int HEIGHT = 900;

  public Swimulator()
  {
    super("SWIMULATOR");
    setSize(WIDTH,HEIGHT);

    Game theGame = new Game();
    ((Component)theGame).setFocusable(true);

    getContentPane().add(theGame);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }

  public static void main( String args[] )
  {
    Swimulator run = new Swimulator();
  }
}
