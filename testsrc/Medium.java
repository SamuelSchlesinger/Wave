import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.geom.*;
import java.util.Random;

public class Medium {
    
    RingBuffer[] states;
    final int HEIGHT, WIDTH;
    static private Random gen = new Random();
    static final int RESOLUTION = 10;

    public Medium(int H, int W) {
        this.HEIGHT = H;
        this.WIDTH = W;
        this.states = new RingBuffer[H/RESOLUTION];
        for (int i = 0; i < H/RESOLUTION; i++) {
            states[i] = new RingBuffer(W/RESOLUTION);
            for (int j = 0; j < W/RESOLUTION; j++) {
                 states[i].push(new Random().nextDouble() * 100 - 50);
            }
        }
    }

    public void update() {
         double[][] new_states = new double[HEIGHT/RESOLUTION][WIDTH/RESOLUTION];
        for (int i = 0; i < states.length; i++) {
            
        }
            for (int j = 0; j < states[i].length; j++) {
                int n = 0;
                double sum = 0;
                
                double average = sum / n;
                new_states[i][j] = average * 0.999;
            }
        }               
        states = new_states; 
    }

    public void ripple(int x, int y) {
        for (int i = - 3; i < 4; i++) {
            for (int j = - 3; j < 3; j++) {
                if (y + j < states.length && x + i < states[0].length && y + j > 0 && x + i > 0)
                    states[y+j][x+i] = 50;
            }
        }
    }

    public void ripple() {
        for (int i = - 5; i < 6; i++) {
            for (int j = - 5; j < 6; j++) {
                    states[states.length/2 + j][states[0].length/2 + i] = 50;
            }
        }
    }

    public void display(Graphics2D graphic) {
        for (int i = 0; i < states.length; i++) {
            for (int j = 0; j < states[i].capacity; j++) {
                Color color = new Color(states[i].p, 20, 20);
                graphic.setPaint(color);
                graphic.fill(new Rectangle2D.Double((double)i * RESOLUTION, (double)j * RESOLUTION, HEIGHT/RESOLUTION, WIDTH/RESOLUTION));
            }
        }
    }
}
