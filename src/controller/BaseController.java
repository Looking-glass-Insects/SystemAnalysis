package controller;

import event.EventCallBack;
import event.IEvent;
import filter.Filter;

/**
 * 负责权限的控制，消息的控制，反馈
 */
public abstract class BaseController implements ISubscriber {
    protected Filter filter;

    public BaseController() {
        filter = new Filter();
    }

    @Override
    public void getEvent(IEvent event, EventCallBack callBack) {
        if (filter.consume(event)) {
            if (callBack != null)
                callBack.isAccept(false);
            return;
        }
        onGetEvent(event);
        acceptEvent(callBack);
    }

    public void acceptEvent(EventCallBack callBack){
        if (callBack != null)
            callBack.isAccept(true);
    }

    public abstract void onGetEvent(IEvent event);
}
