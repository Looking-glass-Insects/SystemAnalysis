package event;

import controller.Config;

/**
 * Created by hy on 2017/10/14.
 */
public class AssessmentEvent implements IEvent {
    private int opId;
    @Override
    public int getUseResId() {
        return Config.Res.ASSESSMENT;
    }

    @Override
    public int getUseOpId() {
        return opId;
    }

    public void setOpId(int opId) {
        this.opId = opId;
    }
}
