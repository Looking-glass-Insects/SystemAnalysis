package controller;
import bean.OperateBean;
import bean.ResBean;
import bean.RoleBean;
import dao.DBUtil;
import event.EventCallBack;
import event.IEvent;
import event.ManageEvent;
import filter.Filter;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by hy on 2017/10/14.
 */
public class AuthController implements ISubscriber{

    private Filter filter ;

    private AuthController() {
        filter = new Filter();
    }

    @Override
    public void getEvent(IEvent event, EventCallBack callBack) {
        if (filter.consume(event)) {
            if (callBack != null)
                callBack.isAccept(false);
            return;
        }
        ManageEvent manageEvent = (ManageEvent) event;
        ManageEvent.EventOP op = manageEvent.getEventOP();
        int roleId =  manageEvent.getRoleId();
        int resId = manageEvent.getResId();
        int opId = manageEvent.getOpId();
        if (op == ManageEvent.EventOP.ADD){
            try {
                DBUtil.writeAuth(roleId,resId,opId);
                JOptionPane.showConfirmDialog(null, "成功", "提示", JOptionPane.YES_NO_OPTION);
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showConfirmDialog(null, "失败", "提示", JOptionPane.YES_NO_OPTION);
            }
        }else if (op == ManageEvent.EventOP.DEL){
            try {
                DBUtil.delAuth(roleId,resId,opId);
                JOptionPane.showConfirmDialog(null, "成功", "提示", JOptionPane.YES_NO_OPTION);
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showConfirmDialog(null, "失败", "提示", JOptionPane.YES_NO_OPTION);
            }
        }
        if (callBack != null)
            callBack.isAccept(true);
    }

    public static AuthController getController() {
        return AuthController.Factory.controller;
    }

    public List<RoleBean> getRoles() {
        return DBUtil.getRoles();
    }

    public List<ResBean> getRes() {
        return DBUtil.getRes();
    }

    public List<OperateBean> getOps() {
        return DBUtil.getOps();
    }

    private static class Factory {
        private static final AuthController controller = new AuthController();
    }
}
