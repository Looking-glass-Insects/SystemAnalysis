package filter;

import event.IEvent;

/**
 * Created by hy on 2017/10/13.
 */
public interface IFilter {
    boolean consume(IEvent event);
}
