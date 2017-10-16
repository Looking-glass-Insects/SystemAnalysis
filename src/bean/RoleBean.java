package bean;

/**
 * Created by hy on 2017/10/13.
 */
public class RoleBean implements IBean{
    private int id;
    private String name;

    public RoleBean(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public RoleBean() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
