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

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context mContext;
    private List<Disease> mData;
    RequestOptions option;

    public RecyclerViewAdapter(Context mContext, List<Disease> mData) {
        this.mContext = mContext;
        this.mData = mData;

        option = new RequestOptions().centerCrop().placeholder(R.drawable.shape_diseases);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.disease_row_item,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.diseaseName.setText(mData.get(position).getName());
        holder.symptoms.setText(mData.get(position).getSymptoms());

        Glide.with(mContext).load(mData.get(position).getImageUrl()).apply(option).into(holder.img_thumbnail);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView diseaseName;
        TextView symptoms;
        ImageView img_thumbnail;


        public MyViewHolder(View itemView){

            super(itemView);

            diseaseName = itemView.findViewById(R.id.disease_name);
            symptoms = itemView.findViewById(R.id.Symptoms);
            img_thumbnail = itemView.findViewById(R.id.thumbnail);
        }
    }
}
