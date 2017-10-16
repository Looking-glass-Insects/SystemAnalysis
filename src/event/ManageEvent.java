package event;

import controller.Config;

/**
 * Created by hy on 2017/10/14.
 */
public class ManageEvent implements IEvent {

    private final int USE_RES_ID = Config.Res.MANAGE;
    private int operateId;

    private int roleId;
    private int resId;
    private int opId;
    private EventOP eventOP;

    @Override
    public int getUseResId() {
        return USE_RES_ID;
    }

    @Override
    public int getUseOpId() {
        return operateId;
    }

    public void setUseOpId(int useOpId) {
        this.operateId = useOpId;
    }

    public static enum EventOP {
        ADD,
        DEL;
    }

    public int getRoleId() {
        return roleId;
    }

    public int getResId() {
        return resId;
    }

    public int getOpId() {
        return opId;
    }

    public EventOP getEventOP() {
        return eventOP;
    }

    public void setOperateId(int operateId) {
        this.operateId = operateId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public void setOpId(int opId) {
        this.opId = opId;
    }

    public void setEventOP(EventOP eventOP) {
        this.eventOP = eventOP;
    }
}
