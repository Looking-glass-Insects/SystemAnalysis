package bean;

/**
 * Created by hy on 2017/10/14.
 */
public class RoleResOpBean {
    private int roleId;
    private int resId;
    private int opId;

    public RoleResOpBean() {

    }

    public RoleResOpBean(int roleId, int resId, int opId) {
        this.roleId = roleId;
        this.resId = resId;
        this.opId = opId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public int getOpId() {
        return opId;
    }

    public void setOpId(int opId) {
        this.opId = opId;
    }

}
