package com.reggie.au.autohome.utils;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.edmodo.rangebar.RangeBar;
import com.reggie.au.autohome.R;
import com.reggie.au.autohome.model.AutohomeItem;
import com.reggie.au.autohome.utils.SlideSwitchView.OnSwitchChangedListener;
import com.reggie.au.autohome.view.SmtechDeviceView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by michaelchen on 2015/3/30.
 */
public class AutohomeWidgetAdapter extends ArrayAdapter<SmtechDeviceView> {
    public static final int TYPE_GENERICITEM = 0;
    public static final int TYPE_FRAME = 1;
    public static final int TYPE_GROUP = 2;
    public static final int TYPE_SWITCH = 3;
    public static final int TYPE_TEXT = 4;
    public static final int TYPE_SLIDER = 5;
    public static final int TYPE_IMAGE = 6;
    public static final int TYPE_SELECTION = 7;
    public static final int TYPE_SECTIONSWITCH = 8;
    public static final int TYPE_ROLLERSHUTTER = 9;
    public static final int TYPE_SETPOINT = 10;
    public static final int TYPE_CHART = 11;
    public static final int TYPE_VIDEO = 12;
    public static final int TYPE_WEB = 13;
    public static final int TYPES_COUNT = 14;

    public AutohomeWidgetAdapter(Context context, int resource, List deviceList) {
        super(context, resource, deviceList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RelativeLayout widgetView;
        TextView labelTextView;
        int widgetLayout = 0;
        final SmtechDeviceView deviceView = getItem(position);
        /*根据读取xml来判断加载布局文件*/
        String type = deviceView.getType();
        if("switch".equals(type)){
            widgetLayout = R.layout.autohomewidgetlist_switchitem;
        }else if("slider".equals(type)){
            widgetLayout = R.layout.autohomewidgetlist_slideritem;
        }else if("conditioning".equals(type)){
            widgetLayout = R.layout.autohomewidgetlist_setpointitem;
        }else if("rollershutter".equals(type)){

        }else if("text".equals(type)){

        }else if("setpoint".equals(type)){

        }else if("genericitem".equals(type)){

        }
        if (convertView == null) {
            widgetView = new RelativeLayout(getContext());
            String inflater = Context.LAYOUT_INFLATER_SERVICE;
            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(inflater);
            vi.inflate(widgetLayout, widgetView, true);
        } else {
            widgetView = (RelativeLayout) convertView;
        }
        final String machinecode = deviceView.getMachinecode();
        final String function = deviceView.getFunction();
        final String widgetname = deviceView.getWidgetname();
        final Map<String, String> state = deviceView.getItem();//state
        final String address = deviceView.getAddress();
        /*根据布局文件来生成各控件属性*/
        if("switch".equals(type)){
            labelTextView = (TextView) widgetView.findViewById(R.id.switchlabel);
            if (labelTextView != null)
                labelTextView.setText(widgetname);
            SlideSwitchView switchSwitch = (SlideSwitchView) widgetView
                    .findViewById(R.id.switchswitch);
            switchSwitch.setOnChangeListener(new OnSwitchChangedListener() {
                @Override
                public void onSwitchChange(SlideSwitchView switchView, boolean isChecked) {
                    if (isChecked) {
                        String onCommand = machinecode+function+address+state.get("on");
                        Log.i("AutohomeWidgetAdapter",onCommand);//开
                    } else {
                        String offCommand = machinecode+function+address+state.get("off");
                        Log.i("AutohomeWidgetAdapter",offCommand);//关
                    }
                }
            });
        }else if("slider".equals(type)){
            labelTextView = (TextView) widgetView
                    .findViewById(R.id.sliderlabel);
            if (labelTextView != null)
                labelTextView.setText(deviceView.getWidgetname());
            RangeBar rangebar = (RangeBar) widgetView.findViewById(R.id.light_rangebar);
            rangebar.setTickCount(deviceView.getItem().size());
            rangebar.setTickHeight(25);
            rangebar.setBarWeight(6);
            rangebar.setBarColor(229999999);
            if (deviceView.getState() != null) {
                rangebar.setOnRangeBarChangeListener(new RangeBar.OnRangeBarChangeListener() {
                    @Override
                    public void onIndexChangeListener(RangeBar rangeBar, int leftThumbIndex, int rightThumbIndex) {
                        //Code using the leftThumbIndex and rightThumbIndex to update the index values.
                        Log.i("RangeBarChangeLinstener","leftIndex : "+leftThumbIndex);
                        Log.i("RangeBarChangeLinstener","rightIndex : "+rightThumbIndex);
                        String lightCommand="";
                        if(leftThumbIndex==0){
                            switch (rightThumbIndex){
                                case 0:
                                    lightCommand=deviceView.getItem().get("off");
                                    break;
                                case 1:
                                    lightCommand=deviceView.getItem().get("low");
                                    break;
                                case 2:
                                    lightCommand=deviceView.getItem().get("middle");
                                    break;
                                case 3:
                                    lightCommand=deviceView.getItem().get("height");
                                    break;
                                case 4:
                                    lightCommand=deviceView.getItem().get("big");
                                    break;
                                default:
                                    lightCommand=deviceView.getItem().get("off");
                                    break;
                            }
                            String command = machinecode+function+address+lightCommand;
                            Log.i("rangeBar","滑动开光: "+command);
                        }
                    }
                });
            }
        }else if("conditioning".equals(type)){
            Log.i("conditioning","-------------------widgetname :"+deviceView.getWidgetname()+" | machinecode: "+deviceView.getMachinecode()+" | function: "+deviceView.getFunction()+" | address:  "+deviceView.getAddress());
            labelTextView = (TextView) widgetView
            .findViewById(R.id.setpointlabel);
            if (labelTextView != null)
                labelTextView.setText(deviceView.getWidgetname());
            TextView setPointValueTextView = (TextView) widgetView
                    .findViewById(R.id.setpointvaluelabel);
            setPointValueTextView.setText(deviceView.getState()!=null?deviceView.getState():"22");
            Button setPointMinusButton = (Button) widgetView.findViewById(R.id.setpointbutton_minus);
            Button setPointPlusButton = (Button) widgetView.findViewById(R.id.setpointbutton_plus);
            setPointMinusButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Log.i("AutohomeAdapter", "Minus");
//                    String setPointWidget = deviceView.getState()!=null?deviceView.getState():"22";
//                    Float currentValue = 22F;
//                    currentValue = currentValue - 1f;
//                    if (currentValue < Float.valueOf(deviceView.getMinvalue())) {
//                        currentValue = Float.valueOf(deviceView.getMinvalue());
//                    }
                    //setPointValueTextView.setText(currentValue.toString());
                }
            });
            setPointPlusButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Log.i("AutohomeAdapter", "Plus");
//                    String setPointWidget = deviceView.getState()!=null?deviceView.getState():"22";
//                    Float currentValue = 22F;
//                    currentValue = currentValue + 1f;
//                    if (currentValue > Float.valueOf(deviceView.getMaxvalue())) {
//                        currentValue = Float.valueOf(deviceView.getMaxvalue());
//                    }
                    //setPointValueTextView.setText(currentValue.toString());
                }
            });
        }else if("rollershutter".equals(type)){

        }else if("text".equals(type)){

        }else if("setpoint".equals(type)){

        }else if("genericitem".equals(type)){

        }

        LinearLayout dividerLayout = (LinearLayout) widgetView
                .findViewById(R.id.listdivider);
        if (dividerLayout != null) {
            if (position < this.getCount() - 1) {
                if (this.getItemViewType(position + 1) == TYPE_FRAME) {
                    dividerLayout.setVisibility(View.GONE); // hide dividers
                    // before frame
                    // widgets
                } else {
                    dividerLayout.setVisibility(View.VISIBLE); // show dividers
                    // for all
                    // others
                }
            } else { // last widget in the list, hide divider
                dividerLayout.setVisibility(View.GONE);
            }
        }
        return widgetView;
    }

    @Override
    public int getItemViewType(int position) {
        SmtechDeviceView deviceView = getItem(position);
        if (deviceView.getType().equals("switch")) {
            return TYPE_SWITCH;
        } else if (deviceView.getType().equals("text")) {
            return TYPE_TEXT;
        } else if (deviceView.getType().equals("slider")) {
            return TYPE_SLIDER;
        } else if (deviceView.getType().equals("selection")) {
            return TYPE_SELECTION;
        } else if (deviceView.getType().equals("condition")) {
            return TYPE_SETPOINT;
        } else {
            return TYPE_GENERICITEM;
        }
    }

    @Override
    public int getViewTypeCount() {
        return TYPES_COUNT;
    }
}
