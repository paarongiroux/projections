
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// 0 --> width / 2
// 1 --> width /2
// * width / 2


// X * 175, X * 175,
//X + 350, Y +350

public class Contents extends JPanel implements ActionListener, KeyListener
{
  private Timer t;
  private int width;
  private final int weight = 10;
  private double[][] cube = { {-1.0,  1.0, -1.0},
                              { 1.0, -1.0, -1.0},
                              { 1.0,  1.0, -1.0},
                              {-1.0, -1.0, -1.0},
                              {-1.0,  1.0,  1.0},
                              { 1.0, -1.0,  1.0},
                              { 1.0,  1.0,  1.0},
                              {-1.0, -1.0,  1.0}};
  private int[][] edges = { {0, 3},
                            {0, 2},
                            {1, 3},
                            {1, 2},
                            {4, 6},
                            {4, 7},
                            {5, 6},
                            {5, 7},
                            {5, 1},
                            {7, 3},
                            {4, 0},
                            {6, 2}};

private int[][] faces = { {0, 1, 2, 3},
                          {4, 5, 6, 7},

  }

  private double[][] projected;
  private double[][] rotated;
  private Mops m;
  private int count = 0;
  private boolean autoSpin = false;

  public Contents(int width)
  {
    m = new Mops();
    this.width = width;
    super.setDoubleBuffered(true);
    t = new Timer(7, this);
    t.start();
    rotated = m.rotateY(cube, 0);
    addKeyListener(this);
    setFocusable(true);
    setFocusTraversalKeysEnabled(false);
  }

  public void paintComponent(Graphics g)
  {
    count++;
    int outer;
    int inner;
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) (Graphics) g;

    g2d.setColor(Color.BLACK);
    g2d.fillRect(0, 0, width, width);
    g2d.setColor(Color.GREEN);


    if (autoSpin)
    {
      rotated = m.rotateY(rotated, 0.4);
      rotated = m.rotateX(rotated, 0.4);
      rotated = m.rotateZ(rotated, 0.4);
    }

    //scale down
    // rotated = m.scale(rotated, 0.999);





    projected = m.project2D(rotated);

    for (outer = 0; outer < projected.length; outer++)
    {
      g2d.fillOval((int) (projected[outer][0] * width/4 + width/2 - weight/2),
      (int)(projected[outer][1] * width/4 + width/2 - weight/2), weight, weight);
    }
    for (int i = 0; i < edges.length; i++)
    {
      g2d.drawLine(
        (int) (projected[edges[i][0]][0] * width/4 + width/2),
        (int) (projected[edges[i][0]][1] * width/4 + width/2),
        (int) (projected[edges[i][1]][0] * width/4 + width/2),
        (int) (projected[edges[i][1]][1] * width/4 + width/2));
    }

  }

  @Override
  public void keyTyped ( KeyEvent e )
  {
      // TODO Auto-generated method stub

  }
  @Override
  public void keyPressed ( KeyEvent e )
  {
      if (e.getKeyCode() == KeyEvent.VK_S)
      {
        rotated = m.scale(rotated, 1.02);
      }
      else if (e.getKeyCode() == KeyEvent.VK_W)
      {
          rotated = m.scale(rotated, 0.98);
      }
      // else if (e.getKeyCode() == KeyEvent.VK_A)
      // {
      //     rotated = m.shiftX(rotated, -0.1);
      //
      // }
      // else if (e.getKeyCode() == KeyEvent.VK_D)
      // {
      //     rotated = m.shiftX(rotated, 0.1);
      //
      // }
      else if (e.getKeyCode() == KeyEvent.VK_DOWN)
      {
          rotated = m.rotateX(rotated, 3);
      }
      else if (e.getKeyCode() == KeyEvent.VK_UP)
      {
          rotated = m.rotateX(rotated, -3);
      }
      else if (e.getKeyCode() == KeyEvent.VK_LEFT)
      {
          rotated = m.rotateY(rotated, 3);
      }
      else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
      {
          rotated = m.rotateY(rotated, -3);
      }
      else if (e.getKeyCode() == KeyEvent.VK_ENTER)
      {
          rotated = m.rotateZ(rotated, -3);
      }
      else if (e.getKeyCode() == KeyEvent.VK_BACK_SLASH)
      {
          rotated = m.rotateZ(rotated, 3);
      }
      else if (e.getKeyCode() == KeyEvent.VK_SPACE)
      {
        autoSpin = !(autoSpin);
      }

  }
  @Override
  public void keyReleased ( KeyEvent e )
  {
      // im1 = d1.getImage();
      // moving = false;
      // repaint();

  }

  public void actionPerformed ( ActionEvent e )
  {
    repaint();
  }

}
