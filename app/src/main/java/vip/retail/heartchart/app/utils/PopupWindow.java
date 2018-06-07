package vip.retail.heartchart.app.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AppOpsManager;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jess.arms.integration.AppManager;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;



public class PopupWindow {

    public static android.widget.PopupWindow window;
    static PopupWindow popupWindowList;

    public static PopupWindow getManager() {
        if (popupWindowList == null) {
            popupWindowList = new PopupWindow();
        }
        return popupWindowList;
    }

/*    public ListView showPopupWindowDiaLogRight(View v, final TextView please_choice, Context context, final List<CItem> cItemList) {
        // 利用layoutInflater获得View
        View view = UIUtils.inflate(R.layout.popupwindow_dialog_item);
        TextView tvTitle = (TextView) view.findViewById(R.id.all_title);
        tvTitle.setText("专业类别");
// 下面是两种方法得到宽度和高度 getWindow().getDecorView().getWidth()
        window = new android.widget.PopupWindow(view,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT
        );

        // 设置popWindow弹出窗体可点击，这句话必须添加，并且是true
        // 设置popWindow的显示和消失动画
        window.setAnimationStyle(R.style.mypopwindow_anim_style);
        window.setFocusable(true);
        window.setOutsideTouchable(true);
        int[] location = new int[2];
        v.getLocationOnScreen(location);
        window.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffffff")));// 这是响应返回键让弹出消失
        window.showAtLocation(v, Gravity.NO_GRAVITY, location[0] + v.getWidth(), location[1]);


        final ListView lv_specialty_type = (ListView) view.findViewById(R.id.lv_specialty_type);
        lv_specialty_type.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                window.dismiss();
                please_choice.setText(cItemList.get(position).getValue());
                UpDataBean.getInstance().setType(cItemList.get(position).getID());

            }
        });

        return lv_specialty_type;
    }

    public Void showPopupWindow(final TextView please_choice, String title, final List<CItem> list, Context context) {

        // 利用layoutInflater获得View
        View view = UIUtils.inflate(R.layout.popupwindow_item);
        view.findViewById(R.id.into_download).setVisibility(View.GONE);
        TextView all_title = (TextView) view.findViewById(R.id.all_title);
        all_title.setText(title);

        // 下面是两种方法得到宽度和高度 getWindow().getDecorView().getWidth()
        window = new android.widget.PopupWindow(view,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT
        );


        // 设置popWindow弹出窗体可点击，这句话必须添加，并且是true
        window.setFocusable(true);
        window.setOutsideTouchable(true);
        window.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffffff")));// 这是响应返回键让弹出消失


        // 设置popWindow的显示和消失动画
        window.setAnimationStyle(R.style.mypopwindow_anim_style);
        window.showAtLocation(please_choice, Gravity.BOTTOM, 0, 0);
        final ListView lv_specialty_type = (ListView) view.findViewById(R.id.lv_specialty_type);
        CItemListAdapter adapter = new CItemListAdapter(list, context);
        lv_specialty_type.setAdapter(adapter);
        lv_specialty_type.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                window.dismiss();
                please_choice.setText(list.get(position).getValue());
                EventManager.setTableId(list.get(position).getID());
                UpDataBean.getInstance().setTableId(list.get(position).getID());
            }
        });

        return null;
    }

    *//**
     * 用于显示选择 地市 区县 街道 的窗体
     *
     * @param relyView 依赖那个View显示
     * @param title    窗体标题名称
     * @return List<RelativeLayout>
     *//*
    public static List<RelativeLayout> show3LineList(View relyView, String title) {
        View view = UIUtils.inflate(R.layout.layout_pop_3line_item);
        ((TextView) view.findViewById(R.id.all_title)).setText(title);//设置title
        Button mRightBtn = (Button) view.findViewById(R.id.into_download);//).setText("完成");//设置头部按钮
        mRightBtn.setText("完成");
        mRightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onPopListSuccessListener != null) {
                    onPopListSuccessListener.onSuccess();
                }
                close();
            }
        });
        // 下面是两种方法得到宽度和高度 getWindow().getDecorView().getWidth()
        window = new android.widget.PopupWindow(view,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT);

        // 设置popWindow弹出窗体可点击，这句话必须添加，并且是true
        window.setFocusable(true);
        window.setOutsideTouchable(true);
        window.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffffff")));// 这是响应返回键让弹出消失

        // 设置popWindow的显示和消失动画
        window.setAnimationStyle(R.style.mypopwindow_anim_style);
        window.showAtLocation(relyView, Gravity.BOTTOM, 0, 0);
        List<RelativeLayout> mRlList = new ArrayList<>();
        mRlList.add((RelativeLayout) view.findViewById(R.id.layout_pop_3ling_left_rl));
        mRlList.add((RelativeLayout) view.findViewById(R.id.layout_pop_3ling_mid_rl));
        mRlList.add((RelativeLayout) view.findViewById(R.id.layout_pop_3ling_right_rl));
        return mRlList;
    }

    public static void showSelectTime(View v, final TextView relyView, String title, Context context) {
        View view = UIUtils.inflate(R.layout.layout_pop_select_time);
        final TextView startTimeEt = (TextView) view.findViewById(R.id.layout_pop_start_time_et);//设置title
        final TextView endTimeEt = (TextView) view.findViewById(R.id.layout_pop_end_time_et);//设置title


        final Button mRightBtn = (Button) view.findViewById(R.id.into_download);//设置title
        TextView tvTitle = (TextView) view.findViewById(R.id.all_title);//设置title
        tvTitle.setText("设置时间");
        mRightBtn.setText("确定");
        mRightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("设置开始时间".equals(startTimeEt.getText().toString())) {
                    ToastUitl.showShort("请选择开始时间");
                    return;
                } else if ("设置结束时间".equals(endTimeEt.getText().toString())) {
                    ToastUitl.showShort("请选择结束时间");
                    return;
                }
                relyView.setText(startTimeEt.getText() + " - " + endTimeEt.getText());
                UpDataBean.getInstance().setStartDate(startTimeEt.getText().toString());
                UpDataBean.getInstance().setEndDate(endTimeEt.getText().toString());
                close();
            }
        });

        endTimeEt.setOnTouchListener(new OnSetTimeDateListener(endTimeEt, context));
        startTimeEt.setOnTouchListener(new OnSetTimeDateListener(startTimeEt, context));

        // 下面是两种方法得到宽度和高度 getWindow().getDecorView().getWidth()
        window = new android.widget.PopupWindow(view,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT
        );

        // 设置popWindow弹出窗体可点击，这句话必须添加，并且是true
        window.setAnimationStyle(R.style.mypopwindow_anim_style);
        window.setFocusable(true);
        window.setOutsideTouchable(true);
        int[] location = new int[2];
        v.getLocationOnScreen(location);
        window.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffffff")));// 这是响应返回键让弹出消失
        window.showAtLocation(v, Gravity.NO_GRAVITY, location[0] + v.getWidth(), location[1]);

    }


    private static OnPopListSuccessListener onPopListSuccessListener;

    public void setOnPopListSuccessListener(OnPopListSuccessListener onPopListSuccessListener) {
        PopupWindow.onPopListSuccessListener = onPopListSuccessListener;
    }

    private static class OnSetTimeDateListener implements View.OnTouchListener {
        TextView mTimeEt;
        Context context;

        OnSetTimeDateListener(TextView mTimeEt, Context context) {//
            this.mTimeEt = mTimeEt;
            this.context = context;
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                Calendar c = Calendar.getInstance();
                new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        mTimeEt.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
            }
            return true;
        }
    }

    public static void close() {
        if (window != null) {
            window.dismiss();
            window = null;
        }
    }*/

    /**
     * 获取状态通知栏高度
     *
     * @param activity
     * @return
     */
    public static int getStatusBarHeight(Activity activity) {
        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        return frame.top;
    }

    /**
     * 提示用户无法进入Dialog
     */
    public static void showInfoDialog(Context context, String title, String content) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title)
                .setMessage(content)
                .setCancelable(false)
                .setPositiveButton("确定",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
//                                AppManager.getAppManager().finishAllActivity();
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(0);
                            }
                        }).show();

    }
}
