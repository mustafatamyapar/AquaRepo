package com.example.aquaassistant.mustafa;

import android.content.Context;
import android.content.Intent;
import android.nfc.cardemulation.CardEmulation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aquaassistant.R;
import com.example.aquaassistant.zulal.Fish;

import java.util.List;
/**
 * This adapter used for fish and other creature page.It uses cardView items to show information.
 * Also when user touches on one of the items, user will see detailed information about that creature or fish.
 * @author Mustafa Efe Tamyapar
 * @version 1.0 07.05.2020 Created adapter.
 * @version 2.0 09.05.2020 Solved problems about gridlayout.
 */
public class RecyclerViewAdapter2 extends RecyclerView.Adapter<RecyclerViewAdapter2.MyViewHolder2> {

    Context mContext;
    List<Fish> mData;

    //Constructor
    public RecyclerViewAdapter2(Context mContext, List<Fish> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }
    //Methods

    /**
     * This method creates a viewHolder which is used for showing cardView items on fish and other creature pages.
     * @param viewType
     * @return is viewHolder
     */
    @NonNull
    @Override
    public RecyclerViewAdapter2.MyViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        view = LayoutInflater.from(mContext).inflate(R.layout.fish_encyclopedia_item, parent, false);
        final RecyclerViewAdapter2.MyViewHolder2 myViewH = new RecyclerViewAdapter2.MyViewHolder2(view);

        return myViewH;
    }

    /**
     * This method gets information from main page and it fills detailwd information pages of the cardView items.
     * @param holder
     * @return is void.
     */
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter2.MyViewHolder2 holder, final int position) {

        holder.fishName.setText(mData.get(position).getName());
        holder.difficulty.setText(mData.get(position).getDifficulty());
        holder.img.setImageResource(mData.get(position).getId());

        holder.Item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, FishDetails.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                i.putExtra("Name", mData.get(position).getName());
                i.putExtra("Description", mData.get(position).getDescription());
                i.putExtra("Image", mData.get(position).getId());

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
     * cardView items in fish and other creatures pages.
     */
    public static class MyViewHolder2 extends RecyclerView.ViewHolder {
        private CardView Item;
        private TextView fishName;
        private TextView difficulty;
        private ImageView img;


        public MyViewHolder2(View itemView) {

            super(itemView);

            Item = itemView.findViewById(R.id.fish_item);
            fishName = itemView.findViewById(R.id.titleOf);
            difficulty = itemView.findViewById(R.id.difficulty);
            img = itemView.findViewById(R.id.fish_image);
        }
    }
}
