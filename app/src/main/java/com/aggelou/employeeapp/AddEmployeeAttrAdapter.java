package com.aggelou.employeeapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import database.AttributesModel;

public class AddEmployeeAttrAdapter extends RecyclerView.Adapter<AddEmployeeAttrAdapter.ViewHolder>{


    private List<AttributesModel> attributes = new ArrayList<>();
    private Context context;

    public AddEmployeeAttrAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_attribute_listv2, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.attributeName.setText(attributes.get(position).getAttrName());

        holder.addAttribute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isClicked= !attributes.get(position).isClicked();
                attributes.get(position).setClicked(isClicked);

                if(isClicked){
                    holder.addAttribute.setImageResource(R.drawable.ic_baseline_done_green);
                } else {
                    holder.addAttribute.setImageResource(R.drawable.ic_baseline_done_24);
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return attributes.size();
    }

    public void changeAttributes(List<AttributesModel> attributes){
        this.attributes = attributes;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView attributeName;
        private ImageButton addAttribute;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            attributeName = itemView.findViewById(R.id.attribute_nameV2);
            addAttribute = itemView.findViewById(R.id.attribute_deleteV2);
        }
    }
}
