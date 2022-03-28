package com.game;

import javafx.scene.input.KeyCode;
import sun.security.mscapi.CPublicKey;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

/**
 * 将图片放置入面板后加入窗体，而不是直接将图片加入窗体
 * 继承JPanel中面板功能
 */
public class gamePanel extends JPanel {
    //蛇长度
    int snakeLength;
    //数组存放蛇的x与y轴坐标
    int[] snakeX = new int[500];
    int[] snakeY = new int[500];
    //蛇头方向
    String direction;
    //游戏状态
    Boolean isStarted = false;
    //定时器
    Timer timer;
    //食物坐标数组
    int[] foodX = new int[1];
    int[] foodY = new int[1];
    //积分
    int score = 0;
    //state
    Boolean isLived;
    //初始化蛇,蛇头加两节身体，也就是数组的0，1，2
    public void initSnake(){
        snakeLength=3;
        snakeX[0] = 175;
        snakeY[0] = 275;
        snakeX[1] = 150;
        snakeY[1] = 275;
        snakeX[2] = 125;
        snakeY[2] = 275;
        direction = "R";
        foodX[0] = 300;
        foodY[0] = 300;
        isLived = true;
    }
    //构造器
    public gamePanel(){
        initSnake();
        //将焦点定位在当前面板
        this.setFocusable(true);
        //加入键盘监听,源代码public abstract class KeyAdapter implements KeyListener.
        //抽象类KeyAdapter实现接口KeyListener,这样就不用重写所有方法而是有选择的进行重写
        //适配器模式
        //匿名内部类使用
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                int keyCode = e.getKeyCode();
                if(keyCode == KeyEvent.VK_SPACE){//空格
                    if(isLived){
                        isStarted = !isStarted;
                        //此方法调用paintComponent
                        repaint();}
                    else {
                        initSnake();
                        repaint();
                    }
                }
                if(keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP){
                    direction = "U";
                };
                if(keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT){
                    direction = "R";
                };
                if(keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN){
                    direction = "D";
                };
                if(keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT){
                    direction = "L";
                };
            }
        });
        //初始化定时器
        timer = new Timer(100, new ActionListener() {
            /**
             *
             * 定时器，每100msActionListener监听一次是否发生了一个动作（调用一次actionPerformed）】=
             * 具体动作放入actionPerformed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                //向右
                if(isStarted && isLived){
                    for(int i=snakeLength-1;i>0;i--){
                        snakeX[i] = snakeX[i-1];
                        snakeY[i] = snakeY[i-1];
                    }
                    if(direction.equals("R")) {
                        snakeX[0] = snakeX[0] + 25;
                    }
                    if(direction.equals("L")) {
                        snakeX[0] = snakeX[0] - 25;
                    }
                    if(direction.equals("U")) {
                        snakeY[0] = snakeY[0] - 25;
                    }
                    if(direction.equals("D")) {
                        snakeY[0] = snakeY[0] + 25;
                    }
                    if(snakeX[0]>800){
                        snakeX[0] = 0;
                    }
                    if(snakeX[0]<0){
                        snakeX[0] = 800;
                    }
                    if(snakeY[0]<0){
                        snakeY[0] = 800;
                    }
                    if(snakeY[0]>800){
                        snakeY[0] = 0;
                    }
                    if(snakeX[0] == foodX[0] && snakeY[0] == foodY[0]){
                        snakeLength++;
                        Random random = new Random();
                        int n = random.nextInt(31)+2;
                        if(n==0){
                            n = 15;
                        }
                        foodX[0] = n*25;
                        foodY[0] = n*25;
                        score+=10;
                    }
                    for(int i = 1;i<snakeLength;i++){
                        if(snakeX[0] == snakeX[i] && snakeY[0] == snakeY[i]){
                            isLived = !isLived;
                        }
                    }
                    repaint();
                }

            }
        });
        timer.start();
    }
    /**
     * paintComponent属于图像化的main方法，可以自动调用（参数）
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //定义数组存放蛇的x与y轴坐标
        //1.填充面板颜色
        this.setBackground(new Color(219, 245, 233));
        //2.放入header
        //image.welcomeImg.paintIcon(this,g,50,0);
        //3.调节画笔颜色
        g.setColor(new Color(179, 217, 210));
        //4.画出游戏界面主体，通过画一个矩形
        g.fillRect(0,0,800,800);
        //5.画蛇
        if(isStarted) {
            if (direction.equals("R")) {
                image.rightImg.paintIcon(this, g, snakeX[0], snakeY[0]);
            }
            if (direction.equals("L")) {
                image.leftImg.paintIcon(this, g, snakeX[0], snakeY[0]);
            }
            if (direction.equals("U")) {
                image.upImg.paintIcon(this, g, snakeX[0], snakeY[0]);
            }
            if (direction.equals("D")) {
                image.downImg.paintIcon(this, g, snakeX[0], snakeY[0]);
            }
            for (int i = 1; i < snakeLength; i++) {
                image.bodyImg.paintIcon(this, g, snakeX[i], snakeY[i]);
            }
            image.foodImg.paintIcon(this,g,foodX[0],foodY[0]);
        }
        if(isStarted==false){
            g.setColor(new Color(1,1,1));
            g.setFont(new Font("微软雅黑",Font.BOLD,40));
            g.drawString("按下空格开始游戏",200,200);
        }
        g.setColor(new Color(1,1,1));
        g.setFont(new Font("微软雅黑",Font.BOLD,30));
        g.drawString("积分:"+score,640,40);
        //死亡判断
        if(isLived==false){
            g.setColor(new Color(1,1,1));
            g.setFont(new Font("微软雅黑",Font.BOLD,30));
            g.drawString("游戏结束,积分："+score+",按下空格重新开始",160,200);
        }

    }
}