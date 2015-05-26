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
        TextView valueTextView;
        int widgetLayout = 0;
        String[] splitString = {};
        SmtechDeviceView deviceView = getItem(position);
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
            LayoutInflater vi;
            vi = (LayoutInflater) getContext().getSystemService(inflater);
            vi.inflate(widgetLayout, widgetView, true);
        } else {
            widgetView = (RelativeLayout) convertView;
        }
        String machinecode = deviceView.getMachinecode();
        String function = deviceView.getFunction();
        String widgetname = deviceView.getWidgetname();
        String widgetId = deviceView.getWidgetId();
        Map<String, String> state = deviceView.getItem();//state
        String icon = deviceView.getIcon();
        String address = deviceView.getAddress();
        /*根据布局文件来生成各控件属性*/
        if("switch".equals(type)){
            labelTextView = (TextView) widgetView.findViewById(R.id.switchlabel);
            if (labelTextView != null)
                labelTextView.setText(widgetname);
            final SlideSwitchView switchSwitch = (SlideSwitchView) widgetView
                    .findViewById(R.id.switchswitch);
            switchSwitch.setTag(1,machinecode+function+address+state.get("on"));
            switchSwitch.setTag(2,machinecode+function+address+state.get("off"));
            switchSwitch.setOnChangeListener(new OnSwitchChangedListener() {
                @Override
                public void onSwitchChange(SlideSwitchView switchView, boolean isChecked) {
                    if (isChecked) {
                        Log.i("AutohomeWidgetAdapter",switchSwitch.getTag(1).toString());//开
                    } else {
                        Log.i("AutohomeWidgetAdapter",switchSwitch.getTag(2).toString());//关
                    }
                }
            });
            ImageView switchImage = (ImageView) widgetView
                    .findViewById(R.id.switchimage);
            //用icon替换
            switchImage.setImageResource(R.drawable.ic_launcher);
        }else if("slider".equals(type)){
            labelTextView = (TextView) widgetView
                    .findViewById(R.id.sliderlabel);
            splitString = deviceView.getWidgetname().split("\\[|\\]");
            if (labelTextView != null)
                labelTextView.setText(splitString[0]);
            AutohomeSmartImageView itemImage = (AutohomeSmartImageView) widgetView
                    .findViewById(R.id.sliderimage);
            itemImage.setImageUrl(".png");
            SeekBar sliderSeekBar = (SeekBar) widgetView
                    .findViewById(R.id.sliderseekbar);
            if (deviceView.getState() != null) {
                sliderSeekBar.setTag(machinecode+function+address);
                int sliderState = (int) Float.parseFloat(deviceView.getState());
                sliderSeekBar.setProgress(sliderState);
                sliderSeekBar
                        .setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
                            @Override
                            public void onProgressChanged(SeekBar seekBar,
                                                          int progress, boolean fromUser) {
                            }

                            @Override
                            public void onStartTrackingTouch(SeekBar seekBar) {
                                Log.i("AutohomeAdapter",
                                        "onStartTrackingTouch position = "
                                                + seekBar.getProgress());
                            }

                            @Override
                            public void onStopTrackingTouch(SeekBar seekBar) {
                                Log.i("AutohomeAdapter",
                                        "onStopTrackingTouch position = "
                                                + seekBar.getProgress());
                                AutohomeItem sliderItem = (AutohomeItem) seekBar
                                        .getTag();
                                // sliderItem.sendCommand(String.valueOf(seekBar.getProgress()));
//                                    sendItemCommand(sliderItem,
//                                            String.valueOf(seekBar.getProgress()));
                            }
                        });
            }
        }else if("conditioning".equals(type)){
            labelTextView = (TextView) widgetView
                    .findViewById(R.id.setpointlabel);
            splitString = deviceView.getWidgetname().split("\\[|\\]");
            if (labelTextView != null)
                labelTextView.setText(splitString[0]);
            AutohomeSmartImageView setPointImage = (AutohomeSmartImageView) widgetView
                    .findViewById(R.id.setpointimage);
            setPointImage.setImageUrl(".png");
            TextView setPointValueTextView = (TextView) widgetView
                    .findViewById(R.id.setpointvaluelabel);
            if (setPointValueTextView != null)
                if (splitString.length > 1) {
                    // If value is not empty, show TextView
                    setPointValueTextView.setVisibility(View.VISIBLE);
                    setPointValueTextView.setText(splitString[1]);
                }
            Button setPointMinusButton = (Button) widgetView
                    .findViewById(R.id.setpointbutton_minus);
            Button setPointPlusButton = (Button) widgetView
                    .findViewById(R.id.setpointbutton_plus);
            setPointMinusButton.setTag(deviceView.getCode(""));
            setPointPlusButton.setTag(deviceView.getCode(""));
            setPointMinusButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("AutohomeAdapter", "Minus");
                    String setPointWidget = (String) v.getTag();
                    float currentValue = Float.valueOf(
                            setPointWidget).floatValue();
                    currentValue = currentValue - 0.5f;
                    if (currentValue < 16f) {
                        currentValue = 16f;
                    }
                    //sendItemCommand(setPointWidget.getItem(),String.valueOf(currentValue));
                }
            });
            setPointPlusButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("AutohomeAdapter", "Plus");
                    String setPointWidget = (String) v.getTag();
                    float currentValue = Float.valueOf(
                            setPointWidget).floatValue();
                    currentValue = currentValue + 0.5f;
                    if (currentValue > 28f) {
                        currentValue = 28f;
                    }
                    //sendItemCommand(setPointWidget.getItem(), String.valueOf(currentValue));
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
