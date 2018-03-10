/**
 * @author Nguyen Le
 * @since February 21, 1:52 PM
 */


import java.lang.Thread;
import java.util.Timer;

public class Solution extends Thread {



    public static void main(String[] args) {
        new TimerPanel();
        PomodoroThread p = new PomodoroThread();
        Thread t1= new Thread(p);
        t1.run();
    }

}
