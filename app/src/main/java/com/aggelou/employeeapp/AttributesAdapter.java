package com.aggelou.employeeapp;

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

//4 -- EXTEND THE CLASS
public class AttributesAdapter extends RecyclerView.Adapter<AttributesAdapter.ViewHolder>{

    private List<AttributesModel> attributes = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_attribute_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.attributeName.setText(attributes.get(position).getAttrName());
    }

    @Override
    public int getItemCount() {
        return attributes.size();
    }

    //USE IT TO CHANGE THE ATTRIBUTES LIST EVERY TIME THE LIST CHANGES
    public void changeAttributes(List<AttributesModel> attributes){
        this.attributes = attributes;
        notifyDataSetChanged();
    }

    //5 -- DEFINE DATA STRUCTURES FOR OUR RECYCLER VIEW

    //1 -- CREATE VIEW HOLDER CLASS
    public class ViewHolder extends RecyclerView.ViewHolder{

        //2 -- ALL VIEWS THAT ARE AT EVERY SINGLE LIST OF THE RECYCLER VIEW
        private TextView attributeName;
        private ImageButton deleteAttributeBtn;

        //3 -- VIEW HOLDER CONSTRUCTOR
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //4 -- INSTANTIATE OUR VIEWS USING itemView
            attributeName = itemView.findViewById(R.id.attribute_name);
            deleteAttributeBtn = itemView.findViewById(R.id.attribute_delete);
        }

    }
}
