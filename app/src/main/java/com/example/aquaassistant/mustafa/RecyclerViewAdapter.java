package com.example.aquaassistant.mustafa;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
     Dialog myDialog;

    public RecyclerViewAdapter(Context mContext, List<Disease> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        view = LayoutInflater.from(mContext).inflate(R.layout.disease_row_item,parent,false);
        final MyViewHolder myViewH = new MyViewHolder(view);

        myDialog = new Dialog(mContext);
        myDialog.setContentView(R.layout.disease_details);


        myViewH.rowItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView dName = myDialog.findViewById(R.id.DiseaseNameBig);
                TextView dInfo = myDialog.findViewById(R.id.DetailedInfo);
                ImageView dPic = myDialog.findViewById(R.id.bigDiseasePic);
                dName.setText(mData.get(myViewH.getAdapterPosition()).getName());
                dInfo.setText(mData.get(myViewH.getAdapterPosition()).getDescription());
                dPic.setImageResource(mData.get(myViewH.getAdapterPosition()).getImage());
                Toast.makeText(mContext,"Hope Your fish is okay. Aquaassitant Team",Toast.LENGTH_SHORT).show();
                myDialog.show();
            }
        });

        return myViewH;
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
        private LinearLayout rowItem;
        private TextView diseaseName;
        private TextView symptoms;
        private ImageView img;


        public MyViewHolder(View itemView){

            super(itemView);

            rowItem = itemView.findViewById(R.id.row_item);
            diseaseName = itemView.findViewById(R.id.disease_name);
            symptoms = itemView.findViewById(R.id.Symptoms);
            img = itemView.findViewById(R.id.thumbnail);
        }
    }
}
