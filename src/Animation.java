import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Timer;
import javax.swing.JFrame;
import java.awt.Graphics2D;
import java.awt.geom.*;
import java.awt.Dimension;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class Animation extends JPanel implements ActionListener, MouseListener { 
    
    private Timer timer = new Timer(5, this);
    private Medium medium;
    private JFrame window;

    public Animation() {
        super(true);
        addMouseListener(this);
        medium = new Medium(1800, 900);
    }
 
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        /* Rubric for animation */

        // Background 
        /*----------------------------*/
        g2d.setColor(Color.BLACK);
        Dimension d = getSize();
        int width = (int) d.getWidth();
        int height = (int) d.getHeight();
        g2d.fillRect(0,0,width,height);
        /*-----------------------------*/
        medium.display(g2d);    
        timer.start(); 
    }

    // All time based things happen here
    public void actionPerformed(ActionEvent e) {        
        medium.update();
        repaint();    
    }

    public void mousePressed(MouseEvent e) {
        medium.ripple(e.getY()/Medium.RESOLUTION, e.getX()/Medium.RESOLUTION);
    }

    public void mouseClicked(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}

    public static void main(String[] args) {
        Animation animation = new Animation();
        JFrame window = new JFrame("Push Wave");
        window.add(animation);
        window.setSize(1800, 900);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }
}
