package controller;

/**
 * Created by hy on 2017/10/13.
 */
public class Config {
    public static class OP{
        public static final int WRITE = 1;//可编辑
        public static final int READ = 2;//可读

        public static String getOpNameById(int id){
            if (id == WRITE)
                return "可编辑";
            if (id == READ)
                return "可读";
             return "";
        }
    }

    public static class Res{
        public static final int REGISTER = 1;//注册组件
        public static final int LOGIN = 2;//登录组件
        public static final int  MANAGE = 3;//权限管理组件
        public static final int WORK_INFO = 4;//作业信息，名称，内容
        public static final int ASSESSMENT = 5;//作业点评
        public static final int WORK_SUBMIT = 6;//作业提交
        public static final int WORK_GRADE = 7;//作业成绩
        public static final int WORK_COUNT = 8;//作业统计

        public static String getResNameById(int id){
            if (id == REGISTER)
                return "注册组件";
            if (id == LOGIN)
                return "登录组件";
            if (id == MANAGE)
                return "权限管理组件";
            if (id == WORK_INFO)
                return "作业信息";
            if (id == ASSESSMENT )
                return "作业点评";
            if (id == WORK_SUBMIT)
                return "作业提交";
            if (id ==WORK_GRADE)
                return "作业成绩";
            if (id == WORK_COUNT)
                return "作业统计";
            return "";
        }
    }
}
