package event;

import controller.Config;

/**
 * Created by hy on 2017/10/14.
 */
public class LoginEvent implements IEvent {

    private final int resID = Config.Res.LOGIN;
    private int operateId;


    private String userName;
    private String password;


    public LoginEvent() {
    }

    public int getUseResId() {
        return resID;
    }
    public int getUseOpId() {
        return operateId;
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



    public void setOperateId(int operateId) {
        this.operateId = operateId;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
