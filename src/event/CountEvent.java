package event;

import controller.Config;

/**
 * Created by hy on 2017/10/14.
 */
public class CountEvent implements IEvent {
    private int opId;
    @Override
    public int getUseResId() {
        return Config.Res.WORK_COUNT;
    }

    @Override
    public int getUseOpId() {
        return opId;
    }

    public void setOpId(int opId) {
        this.opId = opId;
    }
}
