package com.example.aquaassistant.mustafa;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.aquaassistant.mustafa.Disease; 
import com.example.aquaassistant.R;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.security.AccessControlContext;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

     Context mContext;
     List<Disease> mData;

    public RecyclerViewAdapter(Context mContext, List<Disease> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.disease_row_item,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder( MyViewHolder holder, int position) {

        holder.diseaseName.setText(mData.get(position).getName());
        holder.symptoms.setText(mData.get(position).getSymptoms());
        holder.img.setImageResource(mData.get(position).getImage());
    }

    @Override
    public int getItemCount() {

        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
       private TextView diseaseName;
       private TextView symptoms;
       private ImageView img;


        public MyViewHolder(View itemView){

            super(itemView);

            diseaseName = itemView.findViewById(R.id.disease_name);
            symptoms = itemView.findViewById(R.id.Symptoms);
            img = itemView.findViewById(R.id.thumbnail);
        }
    }
}
