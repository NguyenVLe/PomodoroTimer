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
            while(!Solution.paused && Solution.begin){
                timeFinish= false;
                --time;
                displayTimerText();

                if(this.time == 0){
                    timeFinish = true;

                    checkCurrentBreak();

                    Solution.paused = true;
                }
                sleepThread();
            }

            if(timeFinish){
                Solution.MGP.start(Solution.loop);
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

    //change the UI button text
    private void changeButtonText(String text){
        Solution.button.setText(text);
    }

    //change the UI text box
    private void changeTextArea(String text){
        Solution.changeText.setText(text);
    }

    //Returns current time in Mins/Secs string format
    private String timeStringNotation(){
        return Integer.toString(this.time/60) + " Mins & " + Integer.toString(this.time%60) + " Seconds ";
    }

    //display the current time remaining whether on break or working
    private void displayTimerText(){
        if(currentBreak){
            changeTextArea("BREAK " + Integer.toString(longBreak) +": " + timeStringNotation());
        }
        else{
            changeTextArea(timeStringNotation());
        }
    }


    /*
    * Determines if on break or work session has ended when the time is 0
    *
    * If on break session is to begins, determines the length of the break period
    * Else work session simply displays time
    * */
    private void checkCurrentBreak(){
        //is NOT the 5th break and NOT already on break
        if(longBreak  != 4 && !currentBreak){
//            this.time = 180;
            this.time = 30;
            longBreak++;
            currentBreak = true;
            changeButtonText("Begin Break");
            changeTextArea("3 Mins & 0 Seconds");
        }
        //IS the 5th break while NOT being on break
        else if(!currentBreak){
            longBreak =0;
//          this.time = 900;
            this.time = 50;
            currentBreak = true;
            changeButtonText("Begin Break");
            changeTextArea("15 Mins & 0 Seconds");
        }
        //NOT on break
        else{
            currentBreak = false;
//            this.time = 1500;
            this.time = 25;
            changeButtonText("Resume Work");
            changeTextArea(timeStringNotation());
        }

    }


}
