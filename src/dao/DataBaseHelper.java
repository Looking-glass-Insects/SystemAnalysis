package dao;

import java.sql.*;

/**
 * Created by hy on 2017/10/13.
 */
public class DataBaseHelper {

    private Connection connection;

    private DataBaseHelper() {
        init();
    }

    private static class Factory {
        private static final DataBaseHelper helper = new DataBaseHelper();
    }

    public static DataBaseHelper getHelper() {
        return Factory.helper;
    }

    /**
     * 与数据库连接
     */
    private void init() {
        String url = "jdbc:mysql://localhost:"+DBConfig.PORT+"/"+DBConfig.DB_NAME+"?"
                + "user="+DBConfig.USER+"&password="+DBConfig.PASSWORD+"&characterEncoding="+DBConfig.CHAR_SET;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭数据库连接
     */
    public void closeConn() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void executeUpdate(String sql) throws SQLException {
        Statement stmt =  connection.createStatement();
        stmt.executeUpdate(sql);
        stmt.close();
    }

    public ResultSet executeQuery(String sql) throws SQLException {
        Statement stmt =  connection.createStatement();
        ResultSet set = stmt.executeQuery(sql);
        return set;
    }


    public static void main(String[] args) throws SQLException {
        Connection conn = null;
        String sql;
        String url = "jdbc:mysql://localhost:3306/mydb?"
                + "user=root&password=123456&characterEncoding=UTF8";

        try {
            Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
            System.out.println("成功加载MySQL驱动程序");
            // 一个Connection代表一个数据库连接
            conn = DriverManager.getConnection(url);
            // Statement里面带有很多方法，比如executeUpdate可以实现插入，更新和删除等
            Statement stmt = conn.createStatement();
            sql = "select * from table_role";
            ResultSet result = stmt.executeQuery(sql);// executeUpdate语句会返回一个受影响的行数，如果返回-1就没有成功
//            if (result != -1) {
//                System.out.println("创建数据表成功");
//                sql = "insert into student(NO,name) values('2012001','陶伟基')";
//                result = stmt.executeUpdate(sql);
//                sql = "insert into student(NO,name) values('2012002','周小俊')";
//                result = stmt.executeUpdate(sql);
//                sql = "select * from student";
//                ResultSet rs = stmt.executeQuery(sql);// executeQuery会返回结果的集合，否则返回空值
//                System.out.println("学号\t姓名");
            while (result.next()) {
                System.out
                        .println(result.getString(1) + "\t" + result.getString(2));// 入如果返回的是int类型可以用getInt()
            }
//            }
        } catch (SQLException e) {
            System.out.println("MySQL操作错误");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
    }
}

