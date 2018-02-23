/**
 * @author Nguyen Le
 * @since February 21, 1:52 PM
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.Thread;
public class Solution extends Thread {

    static boolean running = false;
    static boolean paused = true;
    static boolean begin = false;

    public static void main(String[] args) {
        running = true;
        JFrame frame = new JFrame("Pomodoro Timer");

        JButton button = new JButton("Begin Timer!");

        JPanel panel = new JPanel();
        JTextArea testArea = new JTextArea("Pomodoro Timer");
        JTextArea testArea2 = new JTextArea("25 Mins & 00 Seconds");

        // Add button to JPanel
        panel.add(testArea);
        panel.add(testArea2);
        panel.add(button);
        // And JPanel needs to be added to the JFrame itself!
        frame.getContentPane().add(panel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Toolkit.getDefaultToolkit().getScreenSize().width/4,Toolkit.getDefaultToolkit().getScreenSize().height/2);

        PomodoroThread p = new PomodoroThread();
//        Thread t1= new Thread(new PomodoroThread());
        Thread t1= new Thread(p);




        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!begin){
                    begin = true;
                }
                if(paused){
                    System.out.println("Timer resumed with " + p.time/60 + " mins " +  p.time%60 + " seconds left");
                    button.setText("Pause");
                    paused  = false;
                }
                else{
                    System.out.println("Now paused with "  + p.time/60 + " mins " +  p.time%60 + " seconds remaining");
                    button.setText("Resume");
                    paused = true;
                }
            }
        });

        t1.run();


//        abc.interrupt();
    }


}
