package view;

import bean.RoleBean;
import controller.*;
import event.EventCallBack;
import event.IEvent;
import event.RegisterEvent;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by hy on 2017/10/13.
 */
public class RegisterWindow implements IPublisher, OnRoleChanged {

    private RegisterController controller;
    private List<RoleBean> roleBeans;

    @Override
    public void registerSubscriber(ISubscriber subscriber) {
        this.controller = (RegisterController) subscriber;
    }

    @Override
    public void sendEvent(IEvent event, EventCallBack callBack) {
        controller.getEvent(event, callBack);
    }


    public RegisterWindow() {
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterEvent event = new RegisterEvent();
                event.setUserName(username.getText());
                event.setPassword(password.getText());
                int index = role.getSelectedIndex();
                event.setRoleID(roleBeans.get(index).getId());
                event.setOperateId(Config.OP.WRITE);
                sendEvent(event, new EventCallBack() {
                    @Override
                    public void isAccept(boolean isAccept) {
                        if (!isAccept)
                            JOptionPane.showConfirmDialog(null, "权限不足", "提示", JOptionPane.YES_NO_OPTION);
                    }
                });
            }
        });
    }

    public static RegisterWindow getRegisterWindow() {
        RegisterWindow window = new RegisterWindow();
        window.registerSubscriber(RegisterController.getController());
        window.init();
        Attachment.getAttachment().addListener(window);
        return window;
    }

    public void init() {
        role.removeAllItems();

        roleBeans = controller.getRoles();
        for (RoleBean bean : roleBeans) {
            role.addItem(bean.getName());
            System.out.println(bean.getName());
        }
       sendReadableEvent();
    }

    private void sendReadableEvent() {
        RegisterEvent event = new RegisterEvent();
        event.setOperateId(Config.OP.READ);
        sendEvent(event, new EventCallBack() {
            @Override
            public void isAccept(boolean isAccept) {
                username.setVisible(isAccept);
                password.setVisible(isAccept);
                role.setVisible(isAccept);
                btn.setVisible(isAccept);
                l1.setVisible(isAccept);
                l2.setVisible(isAccept);
                l3.setVisible(isAccept);
            }
        });
    }

    @Override
    public void onRoleChanged() {
        sendReadableEvent();
    }
    public static void main(String[] args) {

    }

    public JPanel getPanel() {
        return panel1;
    }

    private JPanel panel1;
    private JTextField username;
    private JComboBox role;
    private JButton btn;
    private JLabel l1;
    private JLabel l2;
    private JLabel l3;
    private JPasswordField password;


}
