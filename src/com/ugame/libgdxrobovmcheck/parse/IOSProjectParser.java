/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ugame.libgdxrobovmcheck.parse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 用来解析iOS项目的目录
 * @author ugame
 */
public class IOSProjectParser {
    private static String PROJECT_PATH="";                                       //项目路径
    /**
     * 获得制定文件名的文件
     * @param fn    文件名
     * @return      返回文件名对应的File对象
     * @throws Exception 
     */
    private static File getFile(String fn) throws Exception{
        isInit();
        StringBuilder sb = new StringBuilder();
        sb.append(getPROJECT_PATH()).append("/").append(fn);
        File file = new File(sb.toString());
        return file;
    }
    
    /**
     * 获得robovm.xml文件
     * @param p 要解析的属性，目前为app.id和app.name
     * @return  
     * @throws Exception 当项目目录未初始化时抛出此异常
     */
    public static String getRobovmPropertiesContent(String p) throws Exception{
        File f = getFile("robovm.properties");
        if(!f.exists())
            return "";
        String content = readFile(f.getAbsolutePath());
        Matcher m = null;
        Pattern r = null;
        if(p.equals("app.id")){
            r = Pattern.compile("app.id=[a-zA-Z0-9\\.]+");
        }else if(p.equals("app.name")){
            r = Pattern.compile("app.name=[a-zA-Z0-9\\.\\s]+");
        }
        
        if(r!=null){
            m = r.matcher(content);
            if(m.find())
                return m.group();
        }
        return "";
    }
    
    public static String getInfoPlistsxml(String key){
        return "";
    }
    
    /**
     * 用于获得文件的初始化工作，判断是否设置了项目目录
     * @throws Exception 当项目目录未初始化时抛出此异常
     */
    private static void isInit() throws Exception{
        if(getPROJECT_PATH()==null || getPROJECT_PATH().equals(""))
            throw new Exception("请先初始化项目路径");

    }

    /**
     * @return the PROJECT_PATH
     */
    public static String getPROJECT_PATH() {
        return PROJECT_PATH;
    }

    /**
     * @param aPROJECT_PATH the PROJECT_PATH to set
     */
    public static void setPROJECT_PATH(String aPROJECT_PATH) {
        PROJECT_PATH = aPROJECT_PATH;
    }
    
        /**
     * 将文本文件中的内容读入到buffer中
     * @param buffer buffer
     * @param filePath 文件路径
     * @throws IOException 异常
     * @author cn.outofmemory
     * @date 2013-1-7
     */
    private static void readToBuffer(StringBuffer buffer, String filePath) throws IOException {
        InputStream is = new FileInputStream(filePath);
        String line; // 用来保存每行读取的内容
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        line = reader.readLine(); // 读取第一行
        while (line != null) { // 如果 line 为空说明读完了
            buffer.append(line); // 将读到的内容添加到 buffer 中
            buffer.append("\n"); // 添加换行符
            line = reader.readLine(); // 读取下一行
        }
        reader.close();
        is.close();
    }

    /**
     * 读取文本文件内容
     * @param filePath 文件所在路径
     * @return 文本内容
     * @throws IOException 异常
     * @author cn.outofmemory
     * @date 2013-1-7
     */
    private static String readFile(String filePath) throws IOException {
        StringBuffer sb = new StringBuffer();
        readToBuffer(sb, filePath);
        return sb.toString();
    }
}
