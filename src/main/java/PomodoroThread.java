import javax.swing.*;
import java.awt.*;

/**
 * @author Nguyen Le
 * @since February 21, 2:15 PM
 */
public class PomodoroThread implements Runnable {

//    protected int time = 1500;
    protected int time = 5;
    private int longBreak = 0;
    private boolean currentBreak = false;
    private boolean timeFinish = false;

    public void run() {
        while (true) {
            while(!Solution.paused && Solution.begin == true){
                timeFinish= false;
                --time;
                if(currentBreak){
                    Solution.testArea2.setText("BREAK " + Integer.toString(longBreak) +": "
                            + Integer.toString( this.time/60) +
                            " Mins & "  + Integer.toString(this.time%60) + " Seconds " );
                }
                else{
                    Solution.testArea2.setText( Integer.toString( this.time/60)
                            +  " Mins & "  + Integer.toString(this.time%60) + " Seconds "  );
                }

                //if time is 0
                if(this.time == 0){
                    timeFinish = true;
                    //is NOT the 5th break and NOT already on break
                    if(longBreak  != 4 && !currentBreak){
                        //                    this.time = 180;
                        this.time = 10;
                        longBreak++;
                        currentBreak = true;
                        Solution.paused = true;
                        Solution.button.setText("Begin Break");
                        Solution.testArea2.setText("3 Mins & 0 Seconds");
                    }
                    //IS the 5th break while NOT being on break
                    else if(!currentBreak){
                        longBreak =0;
//                    this.time = 900;
                        this.time = 15;
                        currentBreak = true;
                        Solution.paused = true;
                        Solution.button.setText("Begin Break");
                        Solution.testArea2.setText("15 Mins & 0 Seconds");
                    }
                    //NOT on break
                    else{
                        currentBreak = false;
                        Solution.paused = true;
//                    this.time = 1500;
                        this.time = 25;
                        Solution.button.setText("Resume Work");
                        Solution.testArea2.setText(Integer.toString( this.time/60)
                                +  " Mins & "  + Integer.toString(this.time%60) + " Seconds ");
                    }
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {}
            }
            if(timeFinish){
                Solution.MGP.start(Solution.loop);
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
    }

}
