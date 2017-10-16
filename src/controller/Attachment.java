package controller;

import dao.DataBaseHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 该系统当前使用者信息
 */
public class Attachment {

    private List<OnRoleChanged> listeners = new ArrayList<>();

    private int roleId;
    private Attachment() {
        roleId = 4;//默认为未知
    }

    public void addListener(OnRoleChanged listener){
        listeners.add(listener);
    }

    public int getRoleId() {
        return roleId;
    }
    public void setRoleId(int roleId) {
        if (this.roleId == roleId)
            return;
        this.roleId = roleId;
        for (OnRoleChanged listener:listeners){
            listener.onRoleChanged();
        }
    }
    private static class Factory {
        private static final Attachment attachment = new Attachment();
    }
    public static Attachment getAttachment(){
        return Factory.attachment;
    }
}
