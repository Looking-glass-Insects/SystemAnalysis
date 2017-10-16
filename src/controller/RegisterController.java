package controller;

import bean.RoleBean;
import dao.DBUtil;
import event.EventCallBack;
import event.IEvent;
import event.RegisterEvent;
import filter.Filter;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by hy on 2017/10/13.
 */
public class RegisterController extends BaseController {




    private RegisterController() {

    }


    private static class Factory {
        private static final RegisterController controller = new RegisterController();
    }

    public static RegisterController getController() {
        return Factory.controller;
    }


    @Override
    public void onGetEvent(IEvent event) {
        RegisterEvent registerEvent = (RegisterEvent) event;
        if (registerEvent.getUseOpId() == Config.OP.WRITE) {
            addUser(registerEvent);
        }
    }

    private void addUser(RegisterEvent registerEvent) {
        String userName = registerEvent.getUserName().trim();
        String password = registerEvent.getPassword().trim();
        int roleId = registerEvent.getRoleID();
        assert !userName.equals("");
        assert !password.equals("");
        try {
            DBUtil.registerUser(userName,password,roleId);
            JOptionPane.showConfirmDialog(null, "注册成功", "提示", JOptionPane.YES_NO_OPTION);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showConfirmDialog(null, "注册失败", "提示", JOptionPane.YES_NO_OPTION);
        }
    }

    public List<RoleBean> getRoles() {
        return DBUtil.getRoles();
    }

}

