package view;

import controller.Attachment;
import controller.OnRoleChanged;
import dao.DBUtil;
import dao.DataBaseHelper;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hy on 2017/10/13.
 */
public class MainWindow extends JFrame implements OnRoleChanged {

    private RegisterWindow registerWindow;
    private LoginWindow loginWindow;
    private AuthManageWindow authManageWindow;
    private FunctionWindow functionWindow;

    public MainWindow() throws HeadlessException {
        Attachment.getAttachment().addListener(this);
        registerWindow = RegisterWindow.getRegisterWindow();
        loginWindow = LoginWindow.getLoginWindow();
        authManageWindow = AuthManageWindow.getManageWindow();
        functionWindow = FunctionWindow.getFunctionWindow();
        JTabbedPane tp = new JTabbedPane();
        tp.addTab("注册", registerWindow.getPanel());
        tp.add("登录",loginWindow.getPanel());
        tp.add("权限管理 ",authManageWindow.getPanel());
        tp.add("功能",functionWindow.getPanel());

        tp.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int index = tp.getSelectedIndex();
                switch (index){
                    case 0:
                        registerWindow.init();
                        break;
                    case 1:
                        loginWindow.init();
                        break;
                    case 2:
                        authManageWindow.init();
                        break;
                    case 3:
                        functionWindow.init();
                        break;
                }
            }
        });
        getContentPane().add(tp, BorderLayout.CENTER);
    }

    public static void main(String args[]) {
        JFrame f = new MainWindow();
        f.setSize(600, 450);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                DataBaseHelper.getHelper().closeConn();
                f.setDefaultCloseOperation(EXIT_ON_CLOSE);
            }
        });
        f.setTitle("登录用户：未知");

    }

    @Override
    public void onRoleChanged() {
        String name = DBUtil.getRoleNameById(Attachment.getAttachment().getRoleId());
        this.setTitle("登录用户："+name);
    }
}
