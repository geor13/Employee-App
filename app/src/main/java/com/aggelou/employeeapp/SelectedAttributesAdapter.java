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

public class SelectedAttributesAdapter extends RecyclerView.Adapter<SelectedAttributesAdapter.ViewHolder>{

    public interface SelectAttributeListener{
        void addToSelectedAttributes(AttributesModel attribute);
        void removeSelectedAttribute(AttributesModel attribute);
    }


    private List<AttributesModel> attributes = new ArrayList<>();
    private Context context;
    private SelectAttributeListener listener;

    public SelectedAttributesAdapter(Context context){
        this.context = context;
        this.listener = (SelectAttributeListener)context;
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


        if(attributes.get(position).isClicked()){
            holder.selectAttribute.setImageResource(R.drawable.ic_baseline_done_green);
        } else {
            holder.selectAttribute.setImageResource(R.drawable.ic_baseline_done_24);
        }


        holder.selectAttribute.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                boolean isClicked= !attributes.get(position).isClicked();
                attributes.get(position).setClicked(isClicked);

                if(isClicked){
                    holder.selectAttribute.setImageResource(R.drawable.ic_baseline_done_green);

                    if(listener != null){
                        listener.addToSelectedAttributes(attributes.get(position));
                    }

                } else {
                    holder.selectAttribute.setImageResource(R.drawable.ic_baseline_done_24);
                    listener.removeSelectedAttribute(attributes.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return attributes.size();
    }

    public void changeSelectedAttributes(List<AttributesModel> attributes){
        this.attributes = attributes;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView attributeName;
        private ImageButton selectAttribute;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            attributeName = itemView.findViewById(R.id.attribute_nameV2);
            selectAttribute = itemView.findViewById(R.id.attribute_deleteV2);
        }
    }
}
