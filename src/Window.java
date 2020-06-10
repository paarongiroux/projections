import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Window extends JFrame
{
    Contents contents;
    public Window()
    {
        contents = new Contents(700);
        super.setTitle("Graphics");
        super.setSize(700, 700);
        super.setLocation(100, 100);
        super.setResizable(true);
        super.add(contents);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setVisible(true);
    }
}
