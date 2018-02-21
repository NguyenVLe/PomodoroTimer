/**
 * @author Nguyen Le
 * @since February 21, 1:52 PM
 */

import javax.swing.*;
import java.awt.*;
import java.lang.Thread;
public class Solution extends Thread {

    static boolean running = false;
    static boolean paused = false;

    public static void main(String[] args) {
        running = true;
        JFrame frame = new JFrame("Pomodoro Timer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Toolkit.getDefaultToolkit().getScreenSize().width/4,Toolkit.getDefaultToolkit().getScreenSize().height/2);
        frame.setVisible(true);
        Thread t1= new Thread(new PomodoroThread());
        t1.run();

//        abc.interrupt();
    }
}
