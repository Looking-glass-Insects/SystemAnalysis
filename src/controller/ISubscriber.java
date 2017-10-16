package controller;

import event.EventCallBack;
import event.IEvent;
import view.IPublisher;

/**
 * Created by hy on 2017/10/13.
 */
public interface ISubscriber{
    void getEvent(IEvent event, EventCallBack callBack);
}

