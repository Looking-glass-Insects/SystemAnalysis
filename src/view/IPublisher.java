package view;

import controller.ISubscriber;
import event.EventCallBack;
import event.IEvent;

/**
 * Created by hy on 2017/10/13.
 */
public interface IPublisher  {
    void registerSubscriber(ISubscriber subscriber);

    void sendEvent(IEvent event, EventCallBack callBack);
}
