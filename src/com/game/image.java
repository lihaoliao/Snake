package com.game;

import javax.swing.*;
import java.net.URL;

/**
 * 读取游戏中需要读取的图片
 */
public class image {
    /**
     * 面向对象：
     * 封装图片成为一个对象
     * 在程序中通过操作该对象来实现对图片的操作
     */
    //1.封装图片路径
    //解释：相对路径定位到out/production/GluttonousSnake+("/images/body.png"),注意不是电脑中存放位置而是out中
    public static URL bodyURL = image.class.getResource("/images/body.png");
    public static URL upURL = image.class.getResource("/images/up.png");
    public static URL downURL = image.class.getResource("/images/down.png");
    public static URL leftURL = image.class.getResource("/images/left.png");
    public static URL rightURL = image.class.getResource("/images/right.png");
    public static URL foodURL = image.class.getResource("/images/food.png");
    public static URL welcomeURL = image.class.getResource("/images/Welcome.png");
    //2.图片封装为具体对象，ImageIcon类
    public static ImageIcon bodyImg = new ImageIcon(bodyURL);
    public static ImageIcon upImg = new ImageIcon(upURL);
    public static ImageIcon downImg = new ImageIcon(downURL);
    public static ImageIcon leftImg = new ImageIcon(leftURL);
    public static ImageIcon rightImg = new ImageIcon(rightURL);
    public static ImageIcon foodImg = new ImageIcon(foodURL);
    public static ImageIcon welcomeImg = new ImageIcon(welcomeURL);


}
