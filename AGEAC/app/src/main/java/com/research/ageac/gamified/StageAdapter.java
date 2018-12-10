package com.research.ageac.gamified;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class StageAdapter extends BaseAdapter {
    Context context;
    ArrayList<StageContentDTO> stageContentDTOsList;

    public StageAdapter(Context context, ArrayList<StageContentDTO> stageContentDTOsList) {
        this.context = context;
        this.stageContentDTOsList = stageContentDTOsList;
    }

    @Override
    public int getCount() {
        return stageContentDTOsList.size();
    }

    @Override
    public Object getItem(int i) {
        return stageContentDTOsList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int index, View view, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (view == null) {
            view = inflater.inflate(R.layout.stage_square_layout, parent, false);
        }
        View stageSquare = view.findViewById(R.id.stage_view);
        TextView stageNo = view.findViewById(R.id.stage_textview);
        stageNo.setText(String.valueOf(stageContentDTOsList.get(index).getStageNo()));

        if (stageContentDTOsList.get(index).getCompleted().equals("Y"))
            stageSquare.setBackgroundColor(Color.parseColor("#00FF66"));
        else if (stageContentDTOsList.get(index).getAvailable().equals("Y"))
            stageSquare.setBackgroundColor(Color.parseColor("#00FFCC"));
        else {
            stageSquare.setBackgroundColor(Color.parseColor("#00CCFF"));
            stageSquare.findViewById(R.id.stage_unavailable_lock_imageview).setVisibility(View.VISIBLE);
        }
        return view;
    }
}
