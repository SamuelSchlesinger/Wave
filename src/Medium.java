import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.geom.*;
import java.util.Random;

public class Medium {
    
    double[][] states;
    final int HEIGHT, WIDTH;
    static private Random gen = new Random();
    static final int RESOLUTION = 10;

    public Medium(int H, int W) {
        this.HEIGHT = H;
        this.WIDTH = W;
        this.states = new double[H/RESOLUTION][W/RESOLUTION];
        for (int i = 0; i < H/RESOLUTION; i++) {
            for (int j = 0; j < W/RESOLUTION; j++) {
                states[i][j] = gen.nextDouble() * 220; 
            }
        }
    }

    public void update() {
         double[][] new_states = new double[HEIGHT/RESOLUTION][WIDTH/RESOLUTION];
        for (int i = 0; i < states.length; i++) {
            for (int j = 0; j < states[i].length; j++) {
                int n = 0;
                double sum = 0;
                for (int x = -1; x < 2; x++) {
                    for (int y = -1; y < 2; y++) {
                        if (j + x  > -1 && j + x < states[i].length && i + y > -1 && i + y < states.length && (x != 0 && y != 0)) {
                            sum+=states[i+y][j+x]; n++;
                        }
                    }
                }
                double average = sum / n;
                new_states[i][j] = average * 0.999;
            }
        }               
        states = new_states; 
    }

    public void ripple(int x, int y) {
        int bound = 3;
        for (int i = - bound; i < bound + 1; i++) {
            for (int j = - bound; j < bound + 1; j++) {
                if (y + j < states.length && x + i < states[0].length && y + j > 0 && x + i > 0)
                    states[y+j][x+i] = gen.nextDouble() * 100 + 155;
            }
        }
    }

    public void ripple() {
        for (int i = - 5; i < 6; i++) {
            for (int j = - 5; j < 6; j++) {
                    states[states.length/2 + j][states[0].length/2 + i] = 255;
            }
        }
    }

    public void display(Graphics2D graphic) {
        for (int i = 0; i < states.length; i++) {
            for (int j = 0; j < states[i].length; j++) {
                Color color = new Color(20, 20, Math.min((int)states[i][j], 255));
                graphic.setPaint(color);
                graphic.fill(new Rectangle2D.Double((double)i * RESOLUTION, (double)j * RESOLUTION, HEIGHT/RESOLUTION, WIDTH/RESOLUTION));
            }
        }
    }
}
