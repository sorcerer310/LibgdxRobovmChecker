/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ugame.libgdxrobovmcheck;

import com.ugame.libgdxrobovmcheck.data.MainData;
import com.ugame.libgdxrobovmcheck.parse.IOSProjectParser;
import com.ugame.libgdxrobovmcheck.tools.ImageHelper;
import com.ugame.libgdxrobovmcheck.tools.PropertiesHelper;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author ugame
 */
public class MainFrame extends javax.swing.JFrame {
    
    private MainData bean = new MainData();
    private PropertiesHelper properties = null;
    
    private DocumentBuilderFactory dbf = null;
    private DocumentBuilder db = null;
    private Document document = null;
    private NodeList plist = null;
    private NodeList dict = null;
    private NodeList dictInfo = null;
    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
        
        //获得配置文件
        properties = new PropertiesHelper("./main.properties");
        //将radiobutton加入到组中
        buttonGroup.add(rb_none);
        buttonGroup.add(rb_portrait);
        buttonGroup.add(rb_landscapeLeft);
        
        
        
        //项目路径控件属性值改变
        l_projectPath.addPropertyChangeListener("text", new PropertyChangeListener(){
            public void propertyChange(PropertyChangeEvent evt) {

                //当l_projectPath的text属性改变了，设置bean的值为该属性值
                bean.setProjectPath(evt.getNewValue().toString());
                //当获得打开路径后讲值设置道配置文件中
                properties.setValue("project.path", bean.getProjectPath());
                properties.saveFile("./main.properties","main properties");

                //解析robovm.properties文件
                try {
                    //获得app.id的值
                    String s_appid = IOSProjectParser.getRobovmPropertiesContent("app.id");
                    if(s_appid.equals("")){
                        bt_setIcon.setEnabled(false);
                        JOptionPane.showMessageDialog(MainFrame.this, "您设置的项目路径有误，请重新设置");
                        return;
                    }
                    String v_appid = s_appid.split("=")[1].trim();
                    tf_appid.setText(v_appid);
                    //获得app.name的值
                    String s_appname = IOSProjectParser.getRobovmPropertiesContent("app.name");
                    String v_appname = s_appname.split("=")[1].trim();
                    tf_appname.setText(v_appname);
                } catch (Exception ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                    
                }

                //解析info.plist.xml文件
                try {
                    dbf = DocumentBuilderFactory.newInstance();
                    db = dbf.newDocumentBuilder();
                    document = db.parse(bean.getInfoPlistPath());
                    plist = document.getChildNodes();
                    dict = plist.item(1).getChildNodes();
                    dictInfo = dict.item(1).getChildNodes();
                    
                    for(int i=0;i<dictInfo.getLength();i++){
                        Node node = dictInfo.item(i);
                        if(node.getNodeName().equals("key") 
                                && node.getTextContent().equals("UISupportedInterfaceOrientations")){
                            System.out.println(node.getTextContent());
                            String value = dictInfo.item(i+2).getTextContent();
                            if(value.equals("UIInterfaceOrientationPortrait")){
                                rb_portrait.setSelected(true);
                            }else if(value.equals("UIInterfaceOrientationLandscapeLeft")){
                                rb_landscapeLeft.setSelected(true);
                            }
                        }
                    }
                    
                } catch (ParserConfigurationException | SAXException | IOException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                //解析应用中使用的各图标文件
                bt_icon57.setIcon(new ImageIcon(bean.getIconPath()));
                bt_icon114.setIcon(new ImageIcon(bean.getIcon2xPath()));
                bt_icon72.setIcon(new ImageIcon(bean.getIcon72Path()));
                bt_icon144.setIcon(new ImageIcon(bean.getIcon72_2xPath()));
                
                //设置图标按钮可用
                bt_setIcon.setEnabled(true);
            }
        });
        
        //tf_appid控件属性值改变
        tf_appid.addPropertyChangeListener("text", new PropertyChangeListener(){
            public void propertyChange(PropertyChangeEvent evt) {
                bean.setAppid(evt.getNewValue().toString());
            }
        });
        
        //tf_appname控件属性值改变
        tf_appname.addPropertyChangeListener("text", new PropertyChangeListener(){
            public void propertyChange(PropertyChangeEvent evt) {
                bean.setAppname(evt.getNewValue().toString());
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        buttonGroup = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tf_appid = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tf_appname = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        rb_landscapeLeft = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        rb_portrait = new javax.swing.JRadioButton();
        rb_none = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        bt_icon114 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        bt_icon144 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        bt_icon72 = new javax.swing.JButton();
        bt_icon57 = new javax.swing.JButton();
        bt_setIcon = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        l_projectPath = new javax.swing.JLabel();
        bt_setProject = new javax.swing.JButton();

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("jCheckBoxMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel1.setText("robovm.properties:");

        jLabel2.setText("app.id:");

        tf_appid.setSize(new java.awt.Dimension(100, 28));

        jLabel3.setText("app.name:");

        tf_appname.setPreferredSize(new java.awt.Dimension(130, 28));
        tf_appname.setSize(new java.awt.Dimension(130, 28));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_appid, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tf_appname, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tf_appid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(tf_appname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        rb_landscapeLeft.setText("横向");

        jLabel4.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel4.setText("info.plist.xml程序方向:");

        rb_portrait.setText("纵向");

        rb_none.setSelected(true);
        rb_none.setText("无");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(rb_none)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rb_portrait)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rb_landscapeLeft)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rb_none)
                    .addComponent(rb_portrait)
                    .addComponent(rb_landscapeLeft))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel9.setText("icon");

        jLabel8.setText("icon@2x");

        bt_icon114.setToolTipText("");
        bt_icon114.setPreferredSize(new java.awt.Dimension(100, 100));

        jLabel7.setText("icon-72@2x");

        bt_icon144.setToolTipText("144*144");
        bt_icon144.setPreferredSize(new java.awt.Dimension(100, 100));

        jLabel6.setText("icon-72");

        jLabel5.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel5.setText("图标:");

        bt_icon72.setToolTipText("72*72");
        bt_icon72.setPreferredSize(new java.awt.Dimension(100, 100));

        bt_icon57.setToolTipText("57*57");
        bt_icon57.setPreferredSize(new java.awt.Dimension(100, 100));

        bt_setIcon.setText("<html>设置新图标<br/>&nbsp(512*512)\n</html>");
        bt_setIcon.setEnabled(false);
        bt_setIcon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_setIconActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(bt_setIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(bt_icon57, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(bt_icon114, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(bt_icon72, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(bt_icon144, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 22, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bt_icon114, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bt_icon72, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bt_icon144, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(bt_icon57, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(bt_setIcon, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE))))
                .addContainerGap())
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel10.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel10.setText("项目目录:");

        l_projectPath.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        l_projectPath.setPreferredSize(new java.awt.Dimension(279, 28));
        l_projectPath.setSize(new java.awt.Dimension(279, 28));

        bt_setProject.setText("设置目录");
        bt_setProject.setName(""); // NOI18N
        bt_setProject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_setProjectActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(l_projectPath, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bt_setProject, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(bt_setProject)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(l_projectPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(7, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    /**
     * 设置项目路径按钮按下
     * @param evt  事件对象
     */
    private void bt_setProjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_setProjectActionPerformed
        JFileChooser jc_project = new JFileChooser();
        jc_project.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        String path = properties.getValue("project.path");

        if(path!=null && !path.equals("")){
            jc_project.setCurrentDirectory(new File(path));
            int index = jc_project.showDialog(null, "打开项目文件夹");
            if(index==JFileChooser.APPROVE_OPTION){
                //获得打开的目录
                l_projectPath.setText(jc_project.getSelectedFile().getPath());

            }
        }
    }//GEN-LAST:event_bt_setProjectActionPerformed
    /**
     * 设置图标按钮按下
     * @param evt  事件对象
     */
    private void bt_setIconActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_setIconActionPerformed
        JFileChooser jc_icon = new JFileChooser();              
        jc_icon.setFileSelectionMode(JFileChooser.FILES_ONLY);                  //只选择文件
        
        String path = properties.getValue("project.path");
        if(path!=null && !path.equals("")){
            jc_icon.setCurrentDirectory(new File(path));

            jc_icon.setFileFilter(new FileFilter() {                                //设置文件过滤

                @Override
                public boolean accept(File f) {                                     //过滤png文件和目录
                    String tmp = f.getName().toLowerCase();
                    if(tmp.endsWith(".png") || f.isDirectory())
                        return true;
                    return false;
                }

                @Override
                public String getDescription() {
                    return "png文件";
                }
            });
            int index = jc_icon.showDialog(null, "设置");
            if(index==JFileChooser.APPROVE_OPTION){
                System.out.println(jc_icon.getSelectedFile().getPath());
                File png = new File(jc_icon.getSelectedFile().getPath());
                int[] size = ImageHelper.getPNGSize(png);
                if(size[0]!=512 || size[1]!=512){
                    JOptionPane.showMessageDialog(MainFrame.this, "您选择的图标尺寸不合适，请选择512*512尺寸的图标");
                    return;
                }
                ImageHelper.resizePNG(png, bean.getIconPath(), 57, 57, false);       //输出57*57的图标
                ImageHelper.resizePNG(png, bean.getIcon2xPath(), 114, 114, false);   //输出114*114的图标
                ImageHelper.resizePNG(png, bean.getIcon72Path(), 72, 72, false);     //输出72*72的图标
                ImageHelper.resizePNG(png, bean.getIcon72_2xPath(), 144, 144, false);//输出144*144的图标
            }
        }
    }//GEN-LAST:event_bt_setIconActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_icon114;
    private javax.swing.JButton bt_icon144;
    private javax.swing.JButton bt_icon57;
    private javax.swing.JButton bt_icon72;
    private javax.swing.JButton bt_setIcon;
    private javax.swing.JButton bt_setProject;
    private javax.swing.ButtonGroup buttonGroup;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel l_projectPath;
    private javax.swing.JRadioButton rb_landscapeLeft;
    private javax.swing.JRadioButton rb_none;
    private javax.swing.JRadioButton rb_portrait;
    private javax.swing.JTextField tf_appid;
    private javax.swing.JTextField tf_appname;
    // End of variables declaration//GEN-END:variables
}
