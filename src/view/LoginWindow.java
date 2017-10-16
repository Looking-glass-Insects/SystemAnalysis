package view;

import controller.*;
import event.EventCallBack;
import event.IEvent;
import event.LoginEvent;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by hy on 2017/10/13.
 */
public class LoginWindow implements IPublisher, OnRoleChanged{

    private LoginController controller;

    private LoginWindow() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = username.getText();
                String pass = password.getText();

                LoginEvent event = new LoginEvent();
                event.setOperateId(Config.OP.WRITE);
                event.setUserName(userName);
                event.setPassword(pass);

                sendEvent(event, new EventCallBack() {
                    @Override
                    public void isAccept(boolean isAccept) {
                        if (isAccept){
                            JOptionPane.showConfirmDialog(null, "登录成功 ", "提示", JOptionPane.YES_NO_OPTION);
                        }else {
                            JOptionPane.showConfirmDialog(null, "登录失败", "提示", JOptionPane.YES_NO_OPTION);
                        }
                    }
                });

            }
        });

    }
    
    public static LoginWindow getLoginWindow(){
        LoginWindow window = new LoginWindow();
        window.init();
        Attachment.getAttachment().addListener(window);
        return window;
    }

    public void init() {
        this.controller = LoginController.getController();
        sendReadableEvent();
    }

    private void sendReadableEvent() {
        LoginEvent event = new LoginEvent();
        event.setOperateId(Config.OP.READ);
        sendEvent(event, new EventCallBack() {
            @Override
            public void isAccept(boolean isAccept) {
                username.setVisible(isAccept);
                password.setVisible(isAccept);
                loginButton.setVisible(isAccept);
                l2.setVisible(isAccept);
                l1.setVisible(isAccept);
            }
        });
    }

    @Override
    public void registerSubscriber(ISubscriber subscriber) {
        this.controller = (LoginController) subscriber;
    }

    @Override
    public void sendEvent(IEvent event, EventCallBack callBack) {
        this.controller.getEvent(event,callBack);
    }

    @Override
    public void onRoleChanged() {
        sendReadableEvent();
    }

    public JPanel getPanel() {
        return panel1;
    }

    private JPanel panel1;
    private JTextField username;
    private JButton loginButton;
    private JLabel l2;
    private JLabel l1;
    private JPasswordField password;

}

