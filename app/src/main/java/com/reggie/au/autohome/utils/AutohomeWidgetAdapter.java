package com.reggie.au.autohome.utils;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TextView;
import android.widget.Toast;

import com.reggie.au.autohome.R;
import com.reggie.au.autohome.model.AutohomeItem;
import com.reggie.au.autohome.model.AutohomeWidget;
import com.reggie.au.autohome.model.AutohomeWidgetMapping;

import java.util.ArrayList;
import java.util.Iterator;

import com.reggie.au.autohome.utils.SlideSwitchView.OnSwitchChangedListener;

/**
 * Created by michaelchen on 2015/3/30.
 */
public class AutohomeWidgetAdapter extends ArrayAdapter<AutohomeWidget> {
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

    public AutohomeWidgetAdapter(Context context, int resource, AutohomeWidget[] objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RelativeLayout widgetView;
        TextView labelTextView;
        TextView valueTextView;
        int widgetLayout = 0;
        String[] splitString = {};
        AutohomeWidget autohomeWidget = getItem(position);
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
            case TYPE_SELECTION:
                widgetLayout = R.layout.autohomewidgetlist_selectionitem;
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
                    labelTextView.setText(autohomeWidget.getLabel());
                SlideSwitchView switchSwitch = (SlideSwitchView) widgetView
                        .findViewById(R.id.switchswitch);
                if (autohomeWidget.hasItem()) {
                    if (autohomeWidget.getItem().getState().equals("ON")) {
                        switchSwitch.setChecked(true);
                    } else {
                        switchSwitch.setChecked(false);
                    }
                }
                switchSwitch.setTag(autohomeWidget.getItem());
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
                AutohomeSmartImageView switchImage = (AutohomeSmartImageView) widgetView
                        .findViewById(R.id.switchimage);
                switchImage.setImageUrl(".png");
                break;
            case TYPE_ROLLERSHUTTER:
                labelTextView = (TextView) widgetView
                        .findViewById(R.id.rollershutterlabel);
                if (labelTextView != null)
                    labelTextView.setText(autohomeWidget.getLabel());
                ImageButton rollershutterUpButton = (ImageButton) widgetView
                        .findViewById(R.id.rollershutterbutton_up);
                ImageButton rollershutterStopButton = (ImageButton) widgetView
                        .findViewById(R.id.rollershutterbutton_stop);
                ImageButton rollershutterDownButton = (ImageButton) widgetView
                        .findViewById(R.id.rollershutterbutton_down);
                rollershutterUpButton.setTag(autohomeWidget.getItem());
                rollershutterStopButton.setTag(autohomeWidget.getItem());
                rollershutterDownButton.setTag(autohomeWidget.getItem());
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
                splitString = autohomeWidget.getLabel().split("\\[|\\]");
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
                splitString = autohomeWidget.getLabel().split("\\[|\\]");
                if (labelTextView != null)
                    labelTextView.setText(splitString[0]);
                AutohomeSmartImageView itemImage = (AutohomeSmartImageView) widgetView
                        .findViewById(R.id.sliderimage);
                itemImage.setImageUrl(".png");
                SeekBar sliderSeekBar = (SeekBar) widgetView
                        .findViewById(R.id.sliderseekbar);
                if (autohomeWidget.hasItem()) {
                    sliderSeekBar.setTag(autohomeWidget.getItem());
                    int sliderState = (int) Float.parseFloat(autohomeWidget
                            .getItem().getState());
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
            case TYPE_SELECTION:
                labelTextView = (TextView) widgetView
                        .findViewById(R.id.selectionlabel);
                if (labelTextView != null)
                    labelTextView.setText(autohomeWidget.getLabel());
                Spinner selectionSpinner = (Spinner) widgetView
                        .findViewById(R.id.selectionspinner);
                ArrayList<String> spinnerArray = new ArrayList<String>();
                Iterator<AutohomeWidgetMapping> mappingIterator = autohomeWidget
                        .getMappings().iterator();
                while (mappingIterator.hasNext()) {
                    spinnerArray.add(mappingIterator.next().getLabel());
                }
                ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(
                        this.getContext(), android.R.layout.simple_spinner_item,
                        spinnerArray);
                spinnerAdapter
                        .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                selectionSpinner.setAdapter(spinnerAdapter);
                selectionSpinner.setTag(autohomeWidget);
                selectionSpinner.setSelection((int) Float.parseFloat(autohomeWidget
                        .getItem().getState()));
                selectionSpinner
                        .setOnItemSelectedListener(new OnItemSelectedListener() {

                            @Override
                            public void onItemSelected(AdapterView<?> parent,
                                                       View view, int index, long id) {
                                Log.i("AutohomeAdapter",
                                        "Spinner item click on index " + index);
                                AutohomeWidget ahWidget = (AutohomeWidget) parent
                                        .getTag();
                                if (ahWidget != null)
                                    Log.i("AutohomeAdapter",
                                            "Label selected = "
                                                    + ahWidget.getMapping(
                                                    index).getLabel());
                                if (!ahWidget.getItem().getState().equals(ahWidget.getMapping(index).getCommand())) {
                                    // sendItemCommand(ahWidget.getItem(), ahWidget.getMapping(index).getCommand());
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> arg0) {
                            }
                        });
                AutohomeSmartImageView selectionImage = (AutohomeSmartImageView) widgetView
                        .findViewById(R.id.selectionimage);
                selectionImage.setImageUrl(".png");
                break;
            case TYPE_SETPOINT:
                labelTextView = (TextView) widgetView
                        .findViewById(R.id.setpointlabel);
                splitString = autohomeWidget.getLabel().split("\\[|\\]");
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
                setPointMinusButton.setTag(autohomeWidget);
                setPointPlusButton.setTag(autohomeWidget);
                setPointMinusButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.i("AutohomeAdapter", "Minus");
                        AutohomeWidget setPointWidget = (AutohomeWidget) v.getTag();
                        float currentValue = Float.valueOf(
                                setPointWidget.getItem().getState()).floatValue();
                        currentValue = currentValue - setPointWidget.getStep();
                        if (currentValue < setPointWidget.getMinValue()) {
                            currentValue = setPointWidget.getMinValue();
                        }
                        //sendItemCommand(setPointWidget.getItem(),String.valueOf(currentValue));
                    }
                });
                setPointPlusButton.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.i("AutohomeAdapter", "Plus");
                        AutohomeWidget setPointWidget = (AutohomeWidget) v.getTag();
                        float currentValue = Float.valueOf(
                                setPointWidget.getItem().getState()).floatValue();
                        currentValue = currentValue + setPointWidget.getStep();
                        if (currentValue > setPointWidget.getMaxValue()) {
                            currentValue = setPointWidget.getMaxValue();
                        }
                        //sendItemCommand(setPointWidget.getItem(), String.valueOf(currentValue));
                    }
                });
                break;
            default:
                labelTextView = (TextView) widgetView.findViewById(R.id.itemlabel);
                if (labelTextView != null)
                    labelTextView.setText(autohomeWidget.getLabel());
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
        AutohomeWidget autohomeWidget = getItem(position);
        if (autohomeWidget.getType().equals("Switch")) {
            if (autohomeWidget.hasMappings()) {
                return TYPE_SECTIONSWITCH;
            } else if (autohomeWidget.getItem().getType().equals("RollershutterItem")) {
                return TYPE_ROLLERSHUTTER;
            } else {
                return TYPE_SWITCH;
            }
        } else if (autohomeWidget.getType().equals("Text")) {
            return TYPE_TEXT;
        } else if (autohomeWidget.getType().equals("Slider")) {
            return TYPE_SLIDER;
        } else if (autohomeWidget.getType().equals("Selection")) {
            return TYPE_SELECTION;
        } else if (autohomeWidget.getType().equals("Setpoint")) {
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
