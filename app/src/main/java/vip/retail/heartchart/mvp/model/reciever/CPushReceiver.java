package vip.retail.heartchart.mvp.model.reciever;

import android.content.Context;
import android.util.Log;

import com.alibaba.sdk.android.ams.common.util.StringUtil;
import com.alibaba.sdk.android.push.MessageReceiver;
import com.alibaba.sdk.android.push.notification.CPushMessage;
import com.google.gson.Gson;

import org.simple.eventbus.EventBus;

import java.util.Map;

import vip.retail.kotlin.app.utils.ToastUtils;


/**
 * Created by sunny on 2018/4/3.
 */

public class CPushReceiver extends MessageReceiver {


    public static final String TAG = "cPush";
    private static final String DING_CHAT = "DING_CHAT";
    private static final String MSG_TYPE_EVENT = "event";
    public static final String VIP_NOTICE = "VIP_NOTICE";
    public static final String FREQUENT_NOTICE = "FREQUENT_NOTICE";
    public static final String RECOMMEND_NOTICE = "RECOMMEND_NOTICE";

    @Override
    public void onNotification(Context context, String title, String summary, Map<String, String> extraMap) {
        // TODO 处理推送通知
        Log.e(TAG, "Receive notification, title: " + title + ", summary: " + summary + ", extraMap: " + extraMap);
    }

    @Override
    public void onMessage(Context context, CPushMessage cPushMessage) {
        Log.e(TAG, "onMessage, messageId: " + cPushMessage.getMessageId() + ", title: " + cPushMessage.getTitle() + ", content:" + cPushMessage.getContent());
        String title = cPushMessage.getTitle();
        String content = cPushMessage.getContent();
        if (StringUtil.isEmpty(title) || StringUtil.isEmpty(content)) {
            return;
        }
        if (VIP_NOTICE.equals(title) || FREQUENT_NOTICE.equals(title) || RECOMMEND_NOTICE.equals(title)) {

        }
        if (DING_CHAT.equals(title)) {

            Log.e("xiao", "服务端消息: ");
            //收到服务端的回复(通知)
        }
    }



    @Override
    public void onNotificationOpened(Context context, String title, String summary, String extraMap) {
        Log.e(TAG, "onNotificationOpened, title: " + title + ", summary: " + summary + ", extraMap:" + extraMap);
    }

    @Override
    protected void onNotificationClickedWithNoAction(Context context, String title, String summary, String extraMap) {
        Log.e(TAG, "onNotificationClickedWithNoAction, title: " + title + ", summary: " + summary + ", extraMap:" + extraMap);
    }

    @Override
    protected void onNotificationReceivedInApp(Context context, String title, String summary, Map<String, String> extraMap, int openType, String openActivity, String openUrl) {
        Log.e(TAG, "onNotificationReceivedInApp, title: " + title + ", summary: " + summary + ", extraMap:" + extraMap + ", openType:" + openType + ", openActivity:" + openActivity + ", openUrl:" + openUrl);
    }

    @Override
    protected void onNotificationRemoved(Context context, String messageId) {
        Log.e(TAG, "onNotificationRemoved");
    }
}
