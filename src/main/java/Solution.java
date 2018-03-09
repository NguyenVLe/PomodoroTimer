/**
 * @author Nguyen Le
 * @since February 21, 1:52 PM
 */

import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.Thread;
import sun.audio.*;
public class Solution extends Thread {

    static boolean paused = true;
    static boolean begin = false;
    static JTextArea changeText =new JTextArea("25 Mins & 00 Seconds");
    static JButton button = new JButton("Begin Timer!");
    static AudioDataStream asd;
    static AudioPlayer MGP = AudioPlayer.player;
    static ContinuousAudioDataStream loop = null;

    public static void main(String[] args) {

        AudioStream BGM;
        AudioData MD;


        try {
            BGM = new AudioStream(new FileInputStream("sound.wav"));
            MD = BGM.getData();
            loop = new ContinuousAudioDataStream(MD);
            asd = new AudioDataStream(MD);


        } catch (IOException e) {
            e.printStackTrace();
        }

        JFrame frame = new JFrame("Pomodoro Timer");
        JPanel panel = new JPanel();
        JTextArea titleText = new JTextArea("Pomodoro Timer");

        // Add button to JPanel

        panel.setLayout(null);

        int xScreen = Toolkit.getDefaultToolkit().getScreenSize().width/4;
        int yScreen = Toolkit.getDefaultToolkit().getScreenSize().height/4;
        // And JPanel needs to be added to the JFrame itself!
        frame.getContentPane().add(panel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(xScreen, yScreen);


        titleText.setBounds(xScreen/3,0,200,20);
        changeText.setBounds(xScreen/3,yScreen/8,200,20);
        button.setBounds(xScreen/3,yScreen/4,200,30);
        panel.add(titleText);
        panel.add(changeText);
        panel.add(button);
        PomodoroThread p = new PomodoroThread();
        Thread t1= new Thread(p);


        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               MGP.stop(loop);

                if(!begin){
                    begin = true;
                }
                if(paused){
                    button.setText("Pause");
                    paused  = false;
                }
                else{
                    button.setText("Resume");
                    paused = true;
                }
            }
        });

        t1.run();
    }


}
