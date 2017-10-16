package bean;

/**
 * Created by hy on 2017/10/13.
 */
public class OperateBean implements IBean {
    private int id;
    private String name;

    public OperateBean(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public OperateBean() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
