/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ugame.libgdxrobovmcheck.tools;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;
import javax.imageio.ImageIO;

/**
 * 用来处理图片
 *
 * @author fc
 */
public class ImageHelper {

    public static void resizePNG(String fromFile, String toFile, int outputWidth, int outputHeight, boolean proportion) {
        File f2 = new File(fromFile);
        resizePNG(f2, toFile, outputWidth, outputHeight, proportion);
    }
    /**
     * 获得PNG图片的尺寸
     * @param f 图片对应的File对象
     * @return  返回尺寸数组，第一个为宽度，第二个为高度
     */
    public static int[] getPNGSize(File f){
        int[] retvalue = null;
        try{
            BufferedImage bi = ImageIO.read(f);
            retvalue = new int[2];
            retvalue[0] = bi.getWidth();
            retvalue[1] = bi.getHeight();
        }catch(Exception e){
            e.printStackTrace();
        }
        return retvalue;
    }
    /**
     * 重设PNG尺寸
     * @param f2            PNG文件
     * @param toFile        输出到指定文件
     * @param outputWidth   输出尺寸宽度
     * @param outputHeight  输出尺寸高度
     * @param proportion    是否等比缩放
     */
    public static void resizePNG(File f2, String toFile, int outputWidth, int outputHeight, boolean proportion) {
        try {

            BufferedImage bi2 = ImageIO.read(f2);
            int newWidth;
            int newHeight;
            // 判断是否是等比缩放
            if (proportion == true) {
            // 为等比缩放计算输出的图片宽度及高度
                double rate1 = ((double) bi2.getWidth(null))
                        / (double) outputWidth + 0.1;
                double rate2 = ((double) bi2.getHeight(null))
                        / (double) outputHeight + 0.1;
            // 根据缩放比率大的进行缩放控制
                double rate = rate1 > rate2 ? rate1 : rate2;
                newWidth = (int) (((double) bi2.getWidth(null)) / rate);
                newHeight = (int) (((double) bi2.getHeight(null)) / rate);
                
            } else {
                newWidth = outputWidth; // 输出的图片宽度
                newHeight = outputHeight; // 输出的图片高度
            }
            
            
            BufferedImage to = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);

            Graphics2D g2d = to.createGraphics();
            
            to = g2d.getDeviceConfiguration().createCompatibleImage(newWidth, newHeight, Transparency.TRANSLUCENT);
            g2d.dispose();
            g2d = to.createGraphics();
            

            Image from = bi2.getScaledInstance(newWidth, newHeight, bi2.SCALE_AREA_AVERAGING);
            g2d.drawImage(from, 0, 0, null);
            g2d.dispose();

            ImageIO.write(to, "png", new File(toFile));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
