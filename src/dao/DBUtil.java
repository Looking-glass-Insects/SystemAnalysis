package dao;

import bean.OperateBean;
import bean.ResBean;
import bean.RoleBean;
import bean.RoleResOpBean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hy on 2017/10/14.
 */
public class DBUtil {

    public static boolean hasAuth(int opID, int resID, int roleID) {
        String sql = "select * from table_authority as a,role_authority as r where "
                + "a.authority_id = r.authority_id and "
                + "a.operate_id = " + opID
                + " and a.res_id = " + resID
                + " and r.role_id = " + roleID;
        DataBaseHelper helper = DataBaseHelper.getHelper();
        try {
            ResultSet set = helper.executeQuery(sql);
            if (set.next()) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static List<RoleBean> getRoles() {
        List<RoleBean> l = new ArrayList<>();
        try {
            ResultSet set = DataBaseHelper.getHelper().executeQuery("select * from table_role");
            while (set.next()) {
                int id = set.getInt(1);
                String name = set.getString(2);
                l.add(new RoleBean(id, name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return l;
    }

    public static List<ResBean> getRes() {
        List<ResBean> l = new ArrayList<>();
        try {
            ResultSet set = DataBaseHelper.getHelper().executeQuery("select * from table_res");
            while (set.next()) {
                int id = set.getInt(1);
                String name = set.getString(2);
                l.add(new ResBean(id, name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return l;
    }

    public static List<OperateBean> getOps() {
        List<OperateBean> l = new ArrayList<>();
        try {
            ResultSet set = DataBaseHelper.getHelper().executeQuery("select * from table_operate");
            while (set.next()) {
                int id = set.getInt(1);
                String name = set.getString(2);
                l.add(new OperateBean(id, name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return l;
    }

    public static List<RoleResOpBean> getAuths() {
        List<RoleResOpBean> beans = new ArrayList<>();
        String sql = "select role_id,res_id,operate_id from table_authority as a,role_authority as r "
                + "where a.authority_id = r.authority_id";
        try {
            ResultSet set = DataBaseHelper.getHelper().executeQuery(sql);
            while (set.next()) {
                int roleId = set.getInt(1);
                int resId = set.getInt(2);
                int opId = set.getInt(3);
                RoleResOpBean bean = new RoleResOpBean(roleId, resId, opId);
                beans.add(bean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return beans;
    }

    public static String getRoleNameById(int id) {
        String sql = "select name from table_role where role_id = " + id;
        try {
            ResultSet set = DataBaseHelper.getHelper().executeQuery(sql);
            if (set.next()) {
                String name = set.getString(1);
                return name;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void main(String[] args) {
        String l = getRoleNameById(4);
    }

    public static void registerUser(String userName, String password, int roleId) throws SQLException {
        DataBaseHelper.getHelper().executeUpdate("insert into table_user(name,password,role_id) values('" + userName + "','" + password + "'," + roleId + ");");
    }


    public static ResultSet doLogin(String userName, String password) throws SQLException {
        String sql = "select * from table_user where name ='" + userName + "' and password ='" + password + "';";
        return DataBaseHelper.getHelper().executeQuery(sql);
    }

    public static void writeAuth(int roleId, int resId, int opId) throws SQLException {
        String sql = "insert into table_authority(operate_id,res_id) values(" + opId + "," + resId + ");";
        String sql2 = "select authority_id from table_authority where operate_id = " + opId + " and res_id = " + resId;

        DataBaseHelper helper = DataBaseHelper.getHelper();
        try {
            helper.executeUpdate(sql);
        }catch (Exception e){

        }
        ResultSet set = helper.executeQuery(sql2);
        if (set.next()) {
            int authId = set.getInt(1);
            String sql3 = "insert into role_authority(role_id,authority_id) values(" + roleId + "," + authId + ")";
            helper.executeUpdate(sql3);
        }
    }


    public static void delAuth(int roleId, int resId, int opId) throws SQLException {
        String sql = "select authority_id from table_authority where operate_id = " + opId + " and res_id = " + resId;
        DataBaseHelper helper = DataBaseHelper.getHelper();
        ResultSet set = helper.executeQuery(sql);
        if (set.next()){
            int authId = set.getInt(1);
            String sql2 = "delete from role_authority where role_id = " + roleId + " and authority_id = " + authId;
            helper.executeUpdate(sql2);
        }
    }

}
