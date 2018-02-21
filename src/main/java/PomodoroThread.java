import javax.swing.*;
import java.awt.*;

/**
 * @author Nguyen Le
 * @since February 21, 2:15 PM
 */
public class PomodoroThread implements Runnable {

    private int time = 26;
    private int longBreak = 0;

    public void run() {
        JFrame frame = new JFrame("Pomodoro Timer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Toolkit.getDefaultToolkit().getScreenSize().width/4,Toolkit.getDefaultToolkit().getScreenSize().height/2);
        frame.setVisible(true);
        while (true) {
            if (!Solution.running) {
                return;
            }

            if (!Solution.paused) {
                // Every time this runs...
                --time;
                System.out.println("Mins: " + this.time/60 + " Seconds: " + this.time%60);
            }

            if(this.time == 0 && longBreak  != 4){
                System.out.println("break time for 3 mins");
                this.time = 180;
                longBreak++;

            }
            else if(this.time == 0){
                longBreak =0;
                System.out.println("break time for 15 mins");
                this.time = 900;
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
    }

}
