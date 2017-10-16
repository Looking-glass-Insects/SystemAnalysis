package view;

import bean.OperateBean;
import bean.ResBean;
import bean.RoleBean;
import bean.RoleResOpBean;
import controller.*;
import dao.DBUtil;
import event.EventCallBack;
import event.IEvent;
import event.ManageEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by hy on 2017/10/14.
 */
public class AuthManageWindow implements IPublisher, OnRoleChanged {

    private AuthController controller;

    private List<RoleBean> roleBeans;
    private List<ResBean> resBeans;
    private List<OperateBean> operateBeans;


    public AuthManageWindow() {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManageEvent event = new ManageEvent();
                event.setEventOP(ManageEvent.EventOP.ADD);
                event.setUseOpId(Config.OP.WRITE);

                int roleId = roleBeans.get(roleBox.getSelectedIndex()).getId();
                int resId = resBeans.get(resBox.getSelectedIndex()).getId();
                int opId = operateBeans.get(opBox.getSelectedIndex()).getId();

                event.setRoleId(roleId);
                event.setResId(resId);
                event.setOpId(opId);

                sendEvent(event, new EventCallBack() {
                    @Override
                    public void isAccept(boolean isAccept) {
                        if (isAccept)
                            setLabelText();
                        else {
                            JOptionPane.showConfirmDialog(null, "权限不足", "提示", JOptionPane.YES_NO_OPTION);
                        }
                    }
                });
            }
        });
        delButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManageEvent event = new ManageEvent();
                event.setEventOP(ManageEvent.EventOP.DEL);
                event.setUseOpId(Config.OP.WRITE);

                int roleId = roleBeans.get(roleBox.getSelectedIndex()).getId();
                int resId = resBeans.get(resBox.getSelectedIndex()).getId();
                int opId = operateBeans.get(opBox.getSelectedIndex()).getId();

                event.setRoleId(roleId);
                event.setResId(resId);
                event.setOpId(opId);
                System.out.println("-->"+roleId+","+resId+","+opId);
                sendEvent(event, new EventCallBack() {
                    @Override
                    public void isAccept(boolean isAccept) {
                        if (isAccept)
                            setLabelText();
                        else {
                            JOptionPane.showConfirmDialog(null, "权限不足", "提示", JOptionPane.YES_NO_OPTION);
                        }
                    }
                });
            }
        });
    }

    public static AuthManageWindow getManageWindow() {
        AuthManageWindow window = new AuthManageWindow();
        window.registerSubscriber(AuthController.getController());
        window.init();
        Attachment.getAttachment().addListener(window);
        return window;
    }


    public void init() {
        roleBox.removeAllItems();
        resBox.removeAllItems();
        opBox.removeAllItems();

        roleBeans = controller.getRoles();
        for (RoleBean bean : roleBeans) {
            roleBox.addItem(bean.getName());
            System.out.println(bean.getName());
        }
        resBeans = controller.getRes();
        for (ResBean bean : resBeans) {
            resBox.addItem(bean.getName());
            System.out.println(bean.getName());
        }
        operateBeans = controller.getOps();
        for (OperateBean bean : operateBeans) {
            opBox.addItem(bean.getName());
            System.out.println(bean.getName());
        }

        setLabelText();
        sendReadableEvent();
    }

    private void sendReadableEvent() {
        ManageEvent event = new ManageEvent();
        event.setOperateId(Config.OP.READ);
        sendEvent(event, new EventCallBack() {
            @Override
            public void isAccept(boolean isAccept) {
                roleBox.setVisible(isAccept);
                opBox.setVisible(isAccept);
                resBox.setVisible(isAccept);
                addButton.setVisible(isAccept);
                label.setVisible(isAccept);
                delButton.setVisible(isAccept);
            }
        });
    }

    private void setLabelText() {
        List<RoleResOpBean> beans = DBUtil.getAuths();
        StringBuilder builder = new StringBuilder();
        builder.append("<html>");
        for (RoleResOpBean bean : beans) {
            String roleName = DBUtil.getRoleNameById(bean.getRoleId());
            String resName = Config.Res.getResNameById(bean.getResId());
            String opName = Config.OP.getOpNameById(bean.getOpId());

            builder.append(roleName + ",").append(resName + ",").append(opName + "<br>");
        }
        builder.append("</html>");
        label.setText(builder.toString());
    }

    @Override
    public void onRoleChanged() {
        sendReadableEvent();
    }

    @Override
    public void registerSubscriber(ISubscriber subscriber) {
        this.controller = (AuthController) subscriber;
    }

    @Override
    public void sendEvent(IEvent event, EventCallBack callBack) {
        this.controller.getEvent(event, callBack);
    }


    private JPanel panel1;
    private JComboBox roleBox;
    private JComboBox opBox;
    private JComboBox resBox;
    private JButton addButton;
    private JButton delButton;
    private JLabel label;


    public Component getPanel() {
        return panel1;
    }

}
