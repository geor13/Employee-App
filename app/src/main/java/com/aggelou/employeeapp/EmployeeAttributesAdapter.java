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
import database.EmployeesModel;

public class EmployeeAttributesAdapter extends RecyclerView.Adapter<EmployeeAttributesAdapter.ViewHolder> {

    public interface AttributesInEmployeeListener{
        void deleteAttributeInEmployee(EmployeesModel employee, AttributesModel attribute);
    }

    private List<AttributesModel> attributesList = new ArrayList<>();
    private Context context;
    private EmployeesModel employee;
    private AttributesInEmployeeListener listener;
//    private List<EmployeeWithAttributes> employeesList = new ArrayList<>();

    public EmployeeAttributesAdapter(Context context, EmployeesModel employee){
        this.context = context;
        this.employee = employee;
        this.listener = (AttributesInEmployeeListener)context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_attribute_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
       holder.attributeName.setText(attributesList.get(position).getAttrName());

       holder.deleteFromEmployee.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(listener != null){
                   listener.deleteAttributeInEmployee(employee, attributesList.get(position));
               }
           }
       });

    }

    @Override
    public int getItemCount() {
        return attributesList.size();
    }

    public void ChangeAttributeList(List<AttributesModel> attributesList){
        this.attributesList = attributesList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView attributeName;
        private ImageButton deleteFromEmployee;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            attributeName =  itemView.findViewById(R.id.attribute_name);
            deleteFromEmployee = itemView.findViewById(R.id.attribute_delete);
        }
    }
}
