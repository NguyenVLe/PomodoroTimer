

/**
 * @author Nguyen Le
 * @since February 21, 2:15 PM
 */
public class PomodoroThread implements Runnable {

//    protected int time = 1500;
    private int time = 25;
    private int longBreak = 0;
    private boolean currentBreak;
    private boolean timeFinish;


    public void run() {
        while (true) {
            while(!TimerPanel.paused && TimerPanel.begin){
                timeFinish= false;
                --time;
                TimerPanel.displayTimerText(currentBreak,timeStringNotation());

                if(this.time == 0){
                    timeFinish = true;
                    TimerPanel.breakText(longBreak,currentBreak,timeStringNotation());

                    breakChangeTime();
                    currentBreak = !currentBreak;
                }
                sleepThread();
            }

            if(timeFinish){
                TimerPanel.musicStart();
            }

            sleepThread();
        }
    }

    //sleep thread for 1 second
    private void sleepThread(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {}
    }


    //Returns current time in Mins/Secs string format
    private String timeStringNotation(){
        return Integer.toString(this.time/60) + " Mins & " + Integer.toString(this.time%60) + " Seconds ";
    }

    /*
    * Determines if on break or work session has ended when the time is 0
    * If on break session is to begins, determines the length of the break period
    * Else work session simply displays time
    *
    * */
    private void breakChangeTime(){
        //is NOT the 5th break and NOT already on break
        if(longBreak  != 4 && !currentBreak){
//            this.time = 180;
            this.time = 30;
            longBreak++;
        }
        //IS the 5th break while NOT being on break
        else if(!currentBreak){
            longBreak =0;
//          this.time = 900;
            this.time = 50;
        }
        //NOT on break
        else{
//            this.time = 1500;
            this.time = 25;
        }
    }

}
