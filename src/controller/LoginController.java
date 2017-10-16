package controller;

import dao.DBUtil;
import event.EventCallBack;
import event.IEvent;
import event.LoginEvent;
import filter.Filter;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by hy on 2017/10/14.
 */
public class LoginController extends BaseController {



    private LoginController() {

    }

    @Override
    public void onGetEvent(IEvent event) {
        LoginEvent loginEvent =(LoginEvent) event;
        if (loginEvent.getUseOpId() == Config.OP.WRITE){
            doLogin(loginEvent);
        }
    }

    private static class Factory {
        private static final LoginController controller = new LoginController();
    }

    public static LoginController getController(){
        return Factory.controller;
    }




    private void doLogin(LoginEvent loginEvent) {
        String userName = loginEvent.getUserName();
        String password = loginEvent.getPassword();
        //String sql = "select * from table_user where name ='"+userName+"' and password ='"+password+"';";
        try {
           ResultSet set = DBUtil.doLogin(userName,password);
           if (set.next()){
                int roleID =  set.getInt(4);
                System.out.println("roleID-->"+roleID);
                Attachment.getAttachment().setRoleId(roleID);
           }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
