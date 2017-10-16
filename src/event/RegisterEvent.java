package event;

import controller.Config;

/**
 * Created by hy on 2017/10/13.
 */
public class RegisterEvent implements IEvent {

    private final int resID = Config.Res.REGISTER;
    private int operateId;

    //该资源携带的数据
    private String userName;
    private String password;
    private int roleID;

    public RegisterEvent(String userName, String password, int roleID) {
        this.userName = userName;
        this.password = password;
        this.roleID = roleID;
    }

    public RegisterEvent() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public void setOperateId(int operateId) {
        this.operateId = operateId;
    }

    @Override
    public int getUseResId() {
        return resID;
    }

    @Override
    public int getUseOpId() {
        return operateId;
    }
}
