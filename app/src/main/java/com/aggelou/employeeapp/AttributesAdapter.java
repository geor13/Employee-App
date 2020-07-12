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

//4 -- EXTEND THE CLASS
public class AttributesAdapter extends RecyclerView.Adapter<AttributesAdapter.ViewHolder>{

    private List<AttributesModel> attributes = new ArrayList<>();

    interface AdapterListener{
        void deleteClicked(AttributesModel attribute);
        void editClicked(AttributesModel attribute);
    }

    private AdapterListener listener;

    //SOS
    private Context context;

    public AttributesAdapter(Context context){
        this.context = context;
        this.listener = (AdapterListener)context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_attribute_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.attributeName.setText(attributes.get(position).getAttrName());

        //TRY TO ADD DELETE FUNCTIONALITY
        holder.deleteAttributeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //BETTER IMPLEMENT A LISTENER
                if(listener != null){
                    listener.deleteClicked(attributes.get(position));
                }
            }
        });

        holder.attributeName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null){
                    listener.editClicked(attributes.get(position));
                }
            }
        });
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
