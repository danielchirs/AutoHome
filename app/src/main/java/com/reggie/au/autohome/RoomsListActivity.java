package com.reggie.au.autohome;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.etsy.android.grid.StaggeredGridView;
import com.reggie.au.autohome.command.SmtechData;
import com.reggie.au.autohome.view.SmtechHouseView;
import com.tandong.sa.activity.SmartActivity;
import com.tandong.sa.tools.AssistTool;

import java.util.List;
import java.util.Random;


public class RoomsListActivity extends SmartActivity {

    private static final List<SmtechHouseView> houseList = SmtechData.houseList;

    private static final int[] COLOR = new int[]{0xff33b5e5, 0xffaa66cc, 0xff99cc00, 0xffffbb33, 0xffff4444};

    private StaggeredGridView sGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AssistTool.setFullScreen(this);
        setContentView(R.layout.activity_rooms_list);
        sGridView = (StaggeredGridView) findViewById(R.id.grid_view);
        sGridView.setAdapter(new RoomlistAdapter());
        Log.d("autohome", "----------------------setOnItemClickListener----------------------"+houseList.size());
        sGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                SmtechHouseView house = (SmtechHouseView) parent.getItemAtPosition(position);
                intent.putExtra("room_name", String.valueOf(house.getName()));
                intent.putExtra("roomId", String.valueOf(id));
                Log.d("autohome", "\"----------------------\"+roomNames[position]");
                intent.setClass(RoomsListActivity.this, DetailedControlsActivity.class);
                startActivity(intent);
            }
        });
    }

    public class RoomlistAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return houseList.size();
        }

        @Override
        public Object getItem(int position) {
            return houseList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return houseList.get(position).getRid();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = new TextView(RoomsListActivity.this);
                AbsListView.LayoutParams lp = new AbsListView.LayoutParams(AbsListView.LayoutParams.WRAP_CONTENT, AbsListView.LayoutParams.WRAP_CONTENT);
                convertView.setLayoutParams(lp);
            }
            TextView view = (TextView) convertView;
            view.setText(houseList.get(position).getName());
            view.setBackgroundColor(COLOR[position % 5]);
            view.setGravity(Gravity.CENTER);
            view.setTextColor(Color.WHITE);
            AbsListView.LayoutParams lp = (AbsListView.LayoutParams) view.getLayoutParams();
            lp.height = (int) (getPositionRatio(position) * 200);
            view.setLayoutParams(lp);
            return view;
        }

    }

    private final Random mRandom = new Random();
    private static final SparseArray<Double> sPositionHeightRatios = new SparseArray<Double>();

    private double getPositionRatio(final int position) {
        double ratio = sPositionHeightRatios.get(position, 0.0);
        if (ratio == 0) {
            ratio = getRandomHeightRatio();
            sPositionHeightRatios.append(position, ratio);
        }
        return ratio;
    }

    private double getRandomHeightRatio() {
        return (mRandom.nextDouble() / 2.0) + 1.0; // height will be 1.0 - 1.5 the width
    }

}
