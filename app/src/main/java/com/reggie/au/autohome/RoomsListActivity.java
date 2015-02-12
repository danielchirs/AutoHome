package com.reggie.au.autohome;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;


public class RoomsListActivity extends ActionBarActivity {

    private String[] roomNames = {"主卧", "客厅", "书房", "厨房", "浴室", "次卧", "厕所"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rooms_list);

        GridView roomsGV = (GridView) findViewById(R.id.rooms_gv);
        roomsGV.setAdapter(new RoomlistAdapter(this));

        Log.d("autohome", "----------------------setOnItemClickListener----------------------");

        roomsGV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.putExtra("room_name", String.valueOf(parent.getItemAtPosition(position)));
                Log.d("autohome", "\"----------------------\"+roomNames[position]");
                intent.setClass(RoomsListActivity.this, DetailedControlsActivity.class);
                startActivity(intent);
            }
        });
    }

    public class RoomlistAdapter extends BaseAdapter {

        private Context mContext;
        private LayoutInflater inflater;

        private class GridTemp {
            TextView room_name;
        }

        public RoomlistAdapter(Context c) {
            this.mContext = c;
            inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return roomNames.length;
        }

        @Override
        public Object getItem(int position) {
            return roomNames[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            GridTemp temp;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.rooms_item_layout, null);
                temp = new GridTemp();
                temp.room_name = (TextView) convertView.findViewById(R.id.room_name_tv);
                convertView.setTag(temp);
            } else {
                temp = (GridTemp) convertView.getTag();
            }
            temp.room_name.setText(roomNames[position]);
            return convertView;
        }
    }

}
