/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ugame.libgdxrobovmcheck.data;

import com.ugame.libgdxrobovmcheck.parse.IOSProjectParser;

/**
 * 程序界面所使用的所有数据
 * @author fc
 */
public class MainData {
    private String projectPath = "";
    private String appid = "";
    private String appname = "";
    private String robovmXmlPath = "";
    private String robovmPropertiesPath = "";
    private String infoPlistPath = "";
    private String iconPath = "";
    private String icon2xPath = "";
    private String icon72Path = "";
    private String icon72_2xPath = "";
    
    
    /**
     * @return the projectPath
     */
    public String getProjectPath() {
        return projectPath;
    }

    /**
     * @param projectPath the projectPath to set
     */
    public void setProjectPath(String projectPath) {
        this.projectPath = projectPath;
        IOSProjectParser.setPROJECT_PATH(projectPath);
        robovmXmlPath = projectPath+"/robovm.xml";
        robovmPropertiesPath = projectPath+"/robovm.properties";
        infoPlistPath = projectPath+"/info.plist.xml";
        
        iconPath = projectPath+"/data/icon.png";
        icon2xPath = projectPath+"/data/icon@2x.png";
        icon72Path = projectPath+"/data/icon-72.png";
        icon72_2xPath = projectPath+"/data/icon-72@2x.png";
    }

    /**
     * @return the appid
     */
    public String getAppid() {
        return appid;
    }

    /**
     * @param appid the appid to set
     */
    public void setAppid(String appid) {
        this.appid = appid;
    }

    /**
     * @return the appname
     */
    public String getAppname() {
        return appname;
    }

    /**
     * @param appname the appname to set
     */
    public void setAppname(String appname) {
        this.appname = appname;
    }

    /**
     * @return the robovmXmlPath
     */
    public String getRobovmXmlPath() {
        return robovmXmlPath;
    }

    /**
     * @return the robovmPropertiesPath
     */
    public String getRobovmPropertiesPath() {
        return robovmPropertiesPath;
    }

    /**
     * @return the infoPlistPath
     */
    public String getInfoPlistPath() {
        return infoPlistPath;
    }

    /**
     * @return the iconPath
     */
    public String getIconPath() {
        return iconPath;
    }

    /**
     * @return the icon2xPath
     */
    public String getIcon2xPath() {
        return icon2xPath;
    }

    /**
     * @return the icon72Path
     */
    public String getIcon72Path() {
        return icon72Path;
    }

    /**
     * @return the icon72_2xPath
     */
    public String getIcon72_2xPath() {
        return icon72_2xPath;
    }

    
}
