package controller;

import event.EventCallBack;
import event.IEvent;
import filter.Filter;

/**
 * Created by hy on 2017/10/14.
 */
public class FunctionController implements ISubscriber {

    private Filter filter;

    @Override
    public void getEvent(IEvent event, EventCallBack callBack) {
        if (filter.consume(event)) {
            if (callBack != null)
                callBack.isAccept(false);
            return;
        }
        if (callBack != null)
            callBack.isAccept(true);
    }

    private FunctionController() {
        filter = new Filter();
    }

    private static class Factory{
        private static FunctionController controller = new FunctionController();
    }

    public static FunctionController getFunctionController(){
        return Factory.controller;
    }

}
