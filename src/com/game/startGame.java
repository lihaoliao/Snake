package com.game;

import javax.swing.*;
import java.awt.*;

public class startGame {
    //main方法，程序的入口
    public static void main(String[] args) {
        //1.创建一个窗体
        JFrame jf = new JFrame("GluttonousSnake");
        int windowWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int windowHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        jf.setBounds((windowWidth-800)/2,(windowHeight-800)/2,800,800);
        //2.窗体大小固定
        jf.setResizable(false);
        //3.窗体关闭，程序结束
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //4.创建面板
        gamePanel gp = new gamePanel();
        //5.面板放入窗体
        jf.add(gp);


        //.默认情况下窗体是隐藏的，需要显示窗体,显示放置于最后
        jf.setVisible(true);
    }
}

