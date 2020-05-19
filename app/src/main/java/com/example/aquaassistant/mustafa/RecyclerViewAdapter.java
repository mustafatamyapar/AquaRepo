package com.example.aquaassistant.mustafa;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
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
/**
 * This adapter is used for plants page and diseases page. It uses row items to show information. Also
 * when the user touches on one of the item, user will see detailed information about this plant or disease.
 * @author Mustafa Efe Tamyapar
 * @version 1.0 02.05.2020 Created adapter and trying JSOUP
 * @version 2.0 03.05.2020 Trying JSON and Glide , created a gits website for storing data
 * @version 3.0 04.05.2020 JSon and Glide did not worked, creating a local data List and getting data from it
 * also created disease row item.
 * @version 4.0 11.05.2020 Solved problems about putExtra method.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    Context mContext;
    List<Disease> mData;

    //constructor
    public RecyclerViewAdapter(Context mContext, List<Disease> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }
    //Methods

    /**
     * This method creates a viewHolder which is used for showing row items on diseases and plants page
     * @param viewType
     * @return is viewHolder
     */
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        view = LayoutInflater.from(mContext).inflate(R.layout.disease_row_item, parent, false);
        final MyViewHolder myViewH = new MyViewHolder(view);

        return myViewH;
    }

    /**
     * This method gets information from main page and it fills detaild information pages of the row items.
     * @param holder
     * @return is void.
     */
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.diseaseName.setText(mData.get(position).getName());
        holder.symptoms.setText(mData.get(position).getSymptoms());
        holder.img.setImageResource(mData.get(position).getImage());

        holder.rowItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, DetailsActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                i.putExtra("Title", mData.get(position).getName());
                i.putExtra("Description", mData.get(position).getDescription());
                i.putExtra("Image", mData.get(position).getImage());

                mContext.startActivity(i);
            }
        });
    }

    /**
     * This method gets item count in the list
     * @param
     * @return size of the list
     */
    @Override
    public int getItemCount() {

        return mData.size();
    }

    //Inner class

    /**
     * This inner Class gets information from List in the main page and it fills
     * row items in diseases and plants page.
     */
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout rowItem;
        private TextView diseaseName;
        private TextView symptoms;
        private ImageView img;

        public MyViewHolder(View itemView) {

            super(itemView);

            rowItem = itemView.findViewById(R.id.row_item);
            diseaseName = itemView.findViewById(R.id.disease_name);
            symptoms = itemView.findViewById(R.id.Symptoms);
            img = itemView.findViewById(R.id.thumbnail);
        }
    }
}
