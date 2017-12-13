package com.sft.annam.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sft.annam.Model.MachineCategories;
import com.sft.annam.R;
import com.bumptech.glide.Glide;
import com.sft.annam.TextView_Lato;

import java.util.List;

/**
 * Created by Anjush on 21/11/2016.
 */

public class MachineCategoriesAdapter extends RecyclerView.Adapter<MachineCategoriesAdapter.MyViewHolder> {

    private Context mContext;
    private List<MachineCategories> machineCategoriesList;

    private ItemClickCallback itemClickCallback;

    public interface ItemClickCallback {
        void onMachineClick(View view, int position);
    }

    public void setItemClickCallback(final ItemClickCallback itemClickCallback) {
        this.itemClickCallback = itemClickCallback;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView_Lato title;
        public ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView_Lato) view.findViewById(R.id.title);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
        }
    }

    public MachineCategoriesAdapter(Context mContext, List<MachineCategories> machineCategoriesList) {
        this.mContext = mContext;
        this.machineCategoriesList = machineCategoriesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.machine_categories_card, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        MachineCategories machineCategories = machineCategoriesList.get(position);
        holder.title.setText(machineCategories.getName());
        Log.e("IMAGE",machineCategories.getImageURL());
        // loading machineCategories cover using Glide library
        Glide
                .with(mContext)
                .load(machineCategories.getImageURL())

                .into(holder.thumbnail);
        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickCallback.onMachineClick(view, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return machineCategoriesList.size();
    }
}