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

import java.util.List;

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
        switch (this.getItemViewType(position)) {
            case TYPE_SWITCH:
                widgetLayout = R.layout.autohomewidgetlist_switchitem;
                break;
            case TYPE_ROLLERSHUTTER:
                widgetLayout = R.layout.autohomewidgetlist_rollershutteritem;
                break;
            case TYPE_TEXT:
                widgetLayout = R.layout.autohomewidgetlist_textitem;
                break;
            case TYPE_SLIDER:
                widgetLayout = R.layout.autohomewidgetlist_slideritem;
                break;
            case TYPE_SETPOINT:
                widgetLayout = R.layout.autohomewidgetlist_setpointitem;
                break;
            default:
                widgetLayout = R.layout.autohome_genericitem;
                break;
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
        /*根据布局文件来生成各控件属性*/
        switch (getItemViewType(position)) {
            case TYPE_SWITCH:
                labelTextView = (TextView) widgetView
                        .findViewById(R.id.switchlabel);
                if (labelTextView != null)
                    labelTextView.setText(deviceView.getWidgetname());
                SlideSwitchView switchSwitch = (SlideSwitchView) widgetView
                        .findViewById(R.id.switchswitch);
                if (deviceView.getState() != null) {
                    if (deviceView.getState().equals("on")) {
                        switchSwitch.setChecked(true);
                    } else {
                        switchSwitch.setChecked(false);
                    }
                }
                switchSwitch.setTag(deviceView.getCode(""));
                switchSwitch.setOnChangeListener(new OnSwitchChangedListener() {
                    @Override
                    public void onSwitchChange(SlideSwitchView switchView, boolean isChecked) {
                        if (isChecked) {
                            Toast.makeText(getContext(), "打开", Toast.LENGTH_LONG);
                        } else {
                            Toast.makeText(getContext(), "关闭", Toast.LENGTH_LONG);
                        }
                    }
                });
                ImageView switchImage = (ImageView) widgetView
                        .findViewById(R.id.switchimage);
                switchImage.setImageResource(R.drawable.ic_launcher);
                break;
            case TYPE_ROLLERSHUTTER:
                labelTextView = (TextView) widgetView
                        .findViewById(R.id.rollershutterlabel);
                if (labelTextView != null)
                    labelTextView.setText(deviceView.getWidgetname());
                ImageButton rollershutterUpButton = (ImageButton) widgetView
                        .findViewById(R.id.rollershutterbutton_up);
                ImageButton rollershutterStopButton = (ImageButton) widgetView
                        .findViewById(R.id.rollershutterbutton_stop);
                ImageButton rollershutterDownButton = (ImageButton) widgetView
                        .findViewById(R.id.rollershutterbutton_down);
                rollershutterUpButton.setTag(deviceView.getCode(""));
                rollershutterStopButton.setTag(deviceView.getCode(""));
                rollershutterDownButton.setTag(deviceView.getCode(""));
                rollershutterUpButton.setOnTouchListener(new OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent motionEvent) {
                        ImageButton rollershutterButton = (ImageButton) v;
                        AutohomeItem rollershutterItem = (AutohomeItem) rollershutterButton
                                .getTag();
                        if (motionEvent.getActionMasked() == MotionEvent.ACTION_UP) {
                            //sendItemCommand(rollershutterItem, "UP");
                        }
                        return false;
                    }
                });
                rollershutterStopButton.setOnTouchListener(new OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent motionEvent) {
                        ImageButton rollershutterButton = (ImageButton) v;
                        AutohomeItem rollershutterItem = (AutohomeItem) rollershutterButton
                                .getTag();
                        if (motionEvent.getActionMasked() == MotionEvent.ACTION_UP) {
                            //sendItemCommand(rollershutterItem, "STOP");
                        }
                        return false;
                    }
                });
                rollershutterDownButton.setOnTouchListener(new OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent motionEvent) {
                        ImageButton rollershutterButton = (ImageButton) v;
                        AutohomeItem rollershutterItem = (AutohomeItem) rollershutterButton
                                .getTag();
                        if (motionEvent.getActionMasked() == MotionEvent.ACTION_UP) {
                            //sendItemCommand(rollershutterItem, "DOWN");
                        }
                        return false;
                    }
                });
                AutohomeSmartImageView rollershutterImage = (AutohomeSmartImageView) widgetView
                        .findViewById(R.id.rollershutterimage);
                rollershutterImage.setImageUrl(".png");
                break;
            case TYPE_TEXT:
                labelTextView = (TextView) widgetView.findViewById(R.id.textlabel);
                splitString = deviceView.getWidgetname().split("\\[|\\]");
                if (labelTextView != null)
                    labelTextView.setText(splitString[0]);
                valueTextView = (TextView) widgetView.findViewById(R.id.textvalue);
                if (valueTextView != null)
                    if (splitString.length > 1) {
                        // If value is not empty, show TextView
                        valueTextView.setVisibility(View.VISIBLE);
                        valueTextView.setText(splitString[1]);
                    } else {
                        // If value is empty, hide TextView to fix vertical
                        // alignment of label
                        valueTextView.setVisibility(View.GONE);
                        valueTextView.setText("");
                    }
                AutohomeSmartImageView textImage = (AutohomeSmartImageView) widgetView
                        .findViewById(R.id.textimage);
                textImage.setImageUrl(".png");
                break;
            case TYPE_SLIDER:
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
                    sliderSeekBar.setTag(deviceView.getCode(""));
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
                break;
            case TYPE_SETPOINT:
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
                break;
            default:
                labelTextView = (TextView) widgetView.findViewById(R.id.itemlabel);
                if (labelTextView != null)
                    labelTextView.setText(deviceView.getWidgetname());
                AutohomeSmartImageView sliderImage = (AutohomeSmartImageView) widgetView
                        .findViewById(R.id.itemimage);
                sliderImage.setImageUrl(".png");
                break;
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
        if (deviceView.getType().equals("Switch")) {
            return TYPE_SWITCH;
        } else if (deviceView.getType().equals("Text")) {
            return TYPE_TEXT;
        } else if (deviceView.getType().equals("Slider")) {
            return TYPE_SLIDER;
        } else if (deviceView.getType().equals("Selection")) {
            return TYPE_SELECTION;
        } else if (deviceView.getType().equals("Setpoint")) {
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
