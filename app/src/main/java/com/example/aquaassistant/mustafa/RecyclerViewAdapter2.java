package com.example.aquaassistant.mustafa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aquaassistant.R;
import com.example.aquaassistant.zulal.Fish;

import java.util.List;

public class RecyclerViewAdapter2 extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    Context mContext;
    List<Fish> mData;

    public RecyclerViewAdapter2(Context mContext, List<Fish> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }


    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        view = LayoutInflater.from(mContext).inflate(R.layout.fish_encyclopedia_item,parent,false);
        final RecyclerViewAdapter.MyViewHolder myViewH = new RecyclerViewAdapter.MyViewHolder(view);

        return myViewH;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private LinearLayout Item;
        private TextView fishName;
        private TextView difficulty;
        private ImageView img;


        public MyViewHolder(View itemView){

            super(itemView);

            Item = itemView.findViewById(R.id.fish_item);
            fishName = itemView.findViewById(R.id.titleOf);
            difficulty = itemView.findViewById(R.id.Symptoms);
            img = itemView.findViewById(R.id.fish_image);
        }
    }
}
