/*
 * Copyright (C) 2018 PomodoroTimer - All Rights Reserved
 *
 * Unauthorized copying of this file, via any median is strictly prohibited
 * proprietary and confidential. For more information, please contact me at
 * nvl.nguyenle@gmail.com
 *
 * Written by Nguyen Le <nvl.nguyenle@gmail.com>, March 2018
 */

import sun.audio.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author Nguyen Le
 * @since Mar 09, 4:10 PM
 */
public class TimerPanel {

    private static JTextArea changeText =new JTextArea("25 Mins & 00 Seconds");
    private static JButton button = new JButton("Begin Timer!");

    static AudioDataStream asd;
    static AudioPlayer MGP = AudioPlayer.player;
    static ContinuousAudioDataStream loop = null;

    static boolean paused = true;
    static boolean begin = false;

    public TimerPanel(){

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
    }

    //change the UI button text
    protected static void changeButtonText(String text){
        button.setText(text);
    }

    //change the UI text box
    protected static void changeTextArea(String text){
        changeText.setText(text);
    }

    //display the current time remaining whether on break or working
    protected static void displayTimerText(boolean currentBreak,String time){
        if(currentBreak){
            changeTextArea("BREAK TIME" + ": " + time);
        }
        else{
            changeTextArea(time);
        }
    }

    /*
    * Determines which text to display when time has ended or has been paused.
    * */
    protected static void breakText(int longBreak,boolean currentBreak,String time){

        if(longBreak  != 4 && !currentBreak){
            changeButtonText("Begin Break");
            changeTextArea("3 Mins & 0 Seconds");
        }
        //IS the 5th break while NOT being on break
        else if(!currentBreak){
            changeButtonText("Begin Break");
            changeTextArea("15 Mins & 0 Seconds");
        }
        //NOT on break
        else{
            changeButtonText("Resume Work");
            changeTextArea(time);
        }
        paused =true;

    }

    protected static void musicStart(){
        MGP.start(loop);
    }
}


