package com.aggelou.employeeapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.NumberPicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Calendar;
import java.util.Date;

import database.EmployeesModel;


public class EditEmployeeFragment extends Fragment {
    private EmployeesModel employee;
    private EditText changeEmployeeName;
    private EditText changeEmployeeSurname;
    private EditText changeEmployeeAddress;
    private CheckBox hasCarYes;
    private CheckBox hasCarNo;
    private RecyclerView employeeAttributesList;
    private NumberPicker employeeDay;
    private NumberPicker employeeMonth;
    private NumberPicker employeeYear;


    public EditEmployeeFragment() {
        // Required empty public constructor
    }


    public static EditEmployeeFragment newInstance() {
        EditEmployeeFragment fragment = new EditEmployeeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_employee, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(getView() != null){
            changeEmployeeAddress = getView().findViewById(R.id.address_add_edit);
            changeEmployeeName = getView().findViewById(R.id.edit_employee_name);
            changeEmployeeSurname = getView().findViewById(R.id.edit_employee_surname);
            hasCarYes = getView().findViewById(R.id.has_car_yes_edit);
            hasCarNo = getView().findViewById(R.id.has_car_no_edit);
            employeeDay = getView().findViewById(R.id.employee_day_edit);
            employeeMonth = getView().findViewById(R.id.employee_month_edit);
            employeeYear = getView().findViewById(R.id.employee_year_edit);
            employeeAttributesList = getView().findViewById(R.id.attributes_list_employee_has);
        }

        employee = (EmployeesModel) getArguments().getSerializable(MainActivity.THE_EMPLOYEE);
        changeEmployeeName.setText(employee.getEmployeeName());
        changeEmployeeAddress.setText(employee.getHomeAddress());
        changeEmployeeSurname.setText(employee.getEmployeeSurname());
        hasCarYes.setChecked(employee.isHasCar());
        hasCarNo.setChecked(!employee.isHasCar());

        Date employeeDate = employee.getEmployeeDateOfBirth();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(employeeDate);

        employeeDay.setMaxValue(31);
        employeeDay.setMinValue(1);
        employeeDay.setValue(calendar.get(Calendar.DAY_OF_MONTH));

        employeeMonth.setMaxValue(12);
        employeeMonth.setMinValue(1);
        employeeMonth.setValue(calendar.get(Calendar.MONTH));

        employeeYear.setMaxValue(2000);
        employeeYear.setMinValue(1980);
        employeeYear.setValue(calendar.get(Calendar.YEAR));

        hasCarYes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    hasCarNo.setChecked(false);
                }
            }
        });

        hasCarNo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    hasCarYes.setChecked(false);
                }
            }
        });

        //IMPLEMENT THE ADAPTER FOR THE RECYCLER VIEW AND POPULATE IT USING THE CONNECTION

    }
}