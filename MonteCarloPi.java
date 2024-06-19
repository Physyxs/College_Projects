package montecarlopi;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.awt.*;
import javax.swing.*;

/**
 * This Program will use multiple threads to apply the Monte Carlo method for
 * approximating Pi. It then will display colored points to represent the points
 * that fall within the square and its incircle.
 * 
 * @author Brandon Logan
 */
public class MonteCarloPi {
    //Variables for handling threads
    private static final int TOTAL_POINTS = 1000000;
    private static final int THREAD_COUNT = 4;
    private static final Lock lock = new ReentrantLock();
    private static int pointsInCircle = 0;
    //Variables for drawing points
    private static final double[] xCoords = new double[TOTAL_POINTS / THREAD_COUNT];
    private static final double[] yCoords = new double[TOTAL_POINTS / THREAD_COUNT];
    
    public static void main(String[] args) {
        //Establish number of threads to be used
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);
        
        //Run an executorService for each thread
        for (int i = 0; i < THREAD_COUNT; i++) {
            executorService.execute(new MonteCarloTask());
        }
        
        //Close executorService
        executorService.shutdown();
        
        //Ensure that executors complete tasks
        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        //Approximate Pi
        double piApprox = 4.0 * pointsInCircle / TOTAL_POINTS;
        
        //Create Monte Carlo Drawing
        SwingUtilities.invokeLater(() -> new MonteCarloDrawing());
        
        //Print result
        System.out.println("Estimated value of pi: " + piApprox);
    }

    static class MonteCarloTask implements Runnable {
        @Override
        public void run() {
            int localPointsInCircle = 0;
            
            //Create points for each thread using TOTAL_POINTS/THREAD_COUNT
            for (int i = 0; i < TOTAL_POINTS / THREAD_COUNT; i++) {
                //Create random coordinates between 0 and 1 with a random chance
                //of being negative
                double x = Math.random() * Math.pow(-1, (int) (Math.random()*2 +1));
                double y = Math.random() * Math.pow(-1, (int) (Math.random()*2 +1));
                
                //Pass values to drawing variables
                xCoords[i] = x;
                yCoords[i] = y;
                
                //Check to see if point falls inside the circle
                if (x * x + y * y <= 1.0) {
                    localPointsInCircle++;
                }
                
            }

            // Update the global count using a lock to prevent race conditions
            lock.lock();
            try {
                pointsInCircle += localPointsInCircle;
            } finally {
                lock.unlock();
            }
        }
    }
    
    public static class MonteCarloDrawing extends JFrame {

        public MonteCarloDrawing() {
            //Create jframe
            setTitle("Monte Carlo Drawing");
            setSize(1000, 1000);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            setVisible(true);
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);

            //Draw a square and circle using the same side length
            final int SQUARE_SIZE = 800;
            int x = (getWidth() - SQUARE_SIZE) / 2;
            int y = (getHeight() - SQUARE_SIZE) / 2;
            /* 
            Establishing the coordinates this way ensures that the shapes'
            placements are always consistent
            */

            g.drawRect(x, y, SQUARE_SIZE, SQUARE_SIZE);
            g.drawOval(x, y, SQUARE_SIZE, SQUARE_SIZE);
            
            //Draw points using consistent point side length
            int pointSize = 1;
            //Run loop to draw all points
            for(int i = 0; i < xCoords.length; i++){
                /*
                First half of the equations place the points at the origin of 
                the circle. Adding the (int)... portion moves the coordinates to 
                their correct location relative to the origin and defined size.
                */
                int pointX = x + (SQUARE_SIZE) / 2 + (int) (xCoords[i] * SQUARE_SIZE/2);
                int pointY = y + (SQUARE_SIZE) / 2 + (int) (yCoords[i] * SQUARE_SIZE/2);
                //If inside the incircle, color blue
                if(xCoords[i] * xCoords[i] + yCoords[i] * yCoords[i] <= 1){
                    g.setColor(Color.BLUE);
                }//Else color red
                else g.setColor(Color.RED);
                //Draw point
                g.fillOval(pointX, pointY, pointSize, pointSize);
            }
        }
    }
}

