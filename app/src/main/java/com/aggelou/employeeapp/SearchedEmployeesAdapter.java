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

import database.EmployeesModel;

public class SearchedEmployeesAdapter extends RecyclerView.Adapter<SearchedEmployeesAdapter.ViewHolder> {

    public interface MapListener{
        void goToMap(List<EmployeesModel> employees);
    }

    private List<EmployeesModel> employees = new ArrayList<>();
    private Context context;
    private MapListener listener;

    public SearchedEmployeesAdapter(Context context){
        this.context = context;
        this.listener = (MapListener)context;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_searched_employee_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.employeeSurname.setText(employees.get(position).getEmployeeSurname());
        holder.employeeName.setText(employees.get(position).getEmployeeName());

        holder.employeeMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(listener != null){
                    listener.goToMap(employees);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return employees.size();
    }

    public void changeEmployeeList(List<EmployeesModel> employees){
        this.employees = employees;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView employeeName;
        private TextView employeeSurname;
        private ImageButton employeeMap;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            employeeName = itemView.findViewById(R.id.employee_name_searched);
            employeeSurname = itemView.findViewById(R.id.employee_surname_searched);
            employeeMap = itemView.findViewById(R.id.employee_map);
        }
    }
}
