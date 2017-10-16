package filter;

import controller.Attachment;
import dao.DBUtil;
import event.IEvent;

/**
 * Created by hy on 2017/10/14.
 */
public class Filter implements IFilter {
    @Override
    public boolean consume(IEvent event) {
        int resID = event.getUseResId();
        int opID = event.getUseOpId();
        int roleID = Attachment.getAttachment().getRoleId();
        return DBUtil.hasAuth(opID,resID,roleID);
    }
}
