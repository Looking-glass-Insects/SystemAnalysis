package controller;

import event.EventCallBack;
import event.IEvent;
import filter.Filter;

/**
 * Created by hy on 2017/10/14.
 */
public class FunctionController extends BaseController {


    private FunctionController() {

    }

    @Override
    public void onGetEvent(IEvent event) {

    }

    private static class Factory{
        private static FunctionController controller = new FunctionController();
    }

    public static FunctionController getFunctionController(){
        return Factory.controller;
    }

}
