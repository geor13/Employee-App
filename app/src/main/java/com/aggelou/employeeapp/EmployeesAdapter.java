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

public class EmployeesAdapter extends RecyclerView.Adapter<EmployeesAdapter.ViewHolder>{

    interface EmployeeAdapterListener{
        void deleteClickedEmployee(EmployeesModel employee);
        void editClickedEmployee(EmployeesModel employee);
    }

    private List<EmployeesModel> employees = new ArrayList<>();
    private Context context;
    private EmployeeAdapterListener listener;

    public EmployeesAdapter(Context context){
        this.context = context;
        this.listener = (EmployeeAdapterListener)context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_employee_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

      holder.employeeName.setText(employees.get(position).getEmployeeName());
      holder.employeeSurname.setText(employees.get(position).getEmployeeSurname());

      holder.deleteEmployee.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              //DELETE THE OBJECT HERE THROUGH THE LISTENER
              if(listener != null){
                  listener.deleteClickedEmployee(employees.get(position));
              }
          }
      });

    }

    public void changeEmployees(List<EmployeesModel> employees){
        this.employees = employees;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return employees.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView employeeName;
        private TextView employeeSurname;
        private ImageButton deleteEmployee;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            employeeName = itemView.findViewById(R.id.employee_name);
            employeeSurname = itemView.findViewById(R.id.employee_surname);
            deleteEmployee = itemView.findViewById(R.id.employee_delete);
        }
    }
}
