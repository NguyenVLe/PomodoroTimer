import javax.swing.*;
import java.awt.*;

/**
 * @author Nguyen Le
 * @since February 21, 2:15 PM
 */
public class PomodoroThread implements Runnable {

    protected int time = 1500;
    private int longBreak = 0;
    private boolean currentBreak = false;

    public void run() {
//        JFrame frame = new JFrame("Pomodoro Timer");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(Toolkit.getDefaultToolkit().getScreenSize().width/4,Toolkit.getDefaultToolkit().getScreenSize().height/2);
//        frame.setVisible(true);
        while (true) {
            while(!Solution.paused && Solution.begin == true){
                if (!Solution.running) {
                    return;
                }

                if (!Solution.paused) {
                    // Every time this runs...
                    --time;
                    if(currentBreak){
                        System.out.println("CURRENTLY ON BREAK: Mins: " + this.time/60 + " Seconds: " + this.time%60 + " Remaining");
                    }
                    else{
                        System.out.println("Mins: " + this.time/60 + " Seconds: " + this.time%60 + " Remaining");
                    }
                }
                else{
                    System.out.println("Currently paused at with ");
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
                    System.out.println("GOT IN HERE");
                    Solution.paused = true;
                }
            }
        }
    }
    public void suspend(){

    }

}
