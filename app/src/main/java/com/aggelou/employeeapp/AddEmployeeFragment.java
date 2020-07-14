package com.aggelou.employeeapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import database.AttributesModel;
import database.EmployeesAndAttributes;
import database.EmployeesModel;

public class AddEmployeeFragment extends Fragment {

    private RecyclerView attributesList;
    private EditText addEmployeeName;
    private EditText getAddEmployeeSurname;
    private NumberPicker addEmployeeDay;
    private NumberPicker addEmployeeMonth;
    private NumberPicker addEmployeeYear;
    private CheckBox hasCarYes;
    private CheckBox hasCarNo;
    private EditText employeeAddress;
    private TextView confirmAdd;
    private TextView cancelAdd;

    private List<AttributesModel> listAttributes;

    private PageViewModel addEmployeeViewModel;

    public AddEmployeeFragment() {
        // Required empty public constructor
    }


    public static AddEmployeeFragment newInstance() {
        AddEmployeeFragment fragment = new AddEmployeeFragment();
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
        return inflater.inflate(R.layout.fragment_add_employee, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(getView() != null){
            attributesList = getView().findViewById(R.id.attributes_list_employee);
            addEmployeeName = getView().findViewById(R.id.add_employee_name);
            getAddEmployeeSurname = getView().findViewById(R.id.add_employee_surname);
            addEmployeeDay = getView().findViewById(R.id.employee_day);
            addEmployeeMonth = getView().findViewById(R.id.employee_month);
            addEmployeeYear = getView().findViewById(R.id.employee_year);
            hasCarYes = getView().findViewById(R.id.has_car_yes);
            hasCarNo = getView().findViewById(R.id.has_car_no);
            employeeAddress = getView().findViewById(R.id.address_add);
            confirmAdd = getView().findViewById(R.id.confirm_employee_add);
            cancelAdd = getView().findViewById(R.id.cancel_employee_add);
        }

        hasCarYes.setChecked(true);
        addEmployeeDay.setMinValue(1);
        addEmployeeDay.setMaxValue(31);
        addEmployeeYear.setMaxValue(2000);
        addEmployeeYear.setMinValue(1980);
        addEmployeeMonth.setMinValue(1);
        addEmployeeMonth.setMaxValue(12);

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

        attributesList.setLayoutManager(new LinearLayoutManager(getActivity()));
        final AddEmployeeAttrAdapter adapter = new AddEmployeeAttrAdapter(getActivity());
        attributesList.setAdapter(adapter);

        addEmployeeViewModel = new ViewModelProvider(requireActivity()).get(PageViewModel.class);

        addEmployeeViewModel.getAttributes().observe(getViewLifecycleOwner(), new Observer<List<AttributesModel>>() {
            @Override
            public void onChanged(List<AttributesModel> attributesModels) {
                adapter.changeAttributes(attributesModels);
                listAttributes = attributesModels;
            }
        });

        cancelAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment back = Employees.getEmployeesInstance();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragments_container, back);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        confirmAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = addEmployeeName.getText().toString();
                String surname = getAddEmployeeSurname.getText().toString();
                String address = employeeAddress.getText().toString();
                int day = addEmployeeDay.getValue();
                int month = addEmployeeMonth.getValue();
                int year = addEmployeeYear.getValue();
                boolean hasCar = hasCarYes.isChecked();

                ArrayList<EmployeesAndAttributes> joints = new ArrayList<>();

                Calendar myCalendar = new GregorianCalendar(year, month-1, day);
                Date employeeDate = myCalendar.getTime();

                EmployeesModel employee = new EmployeesModel(name, surname, employeeDate, hasCar, address);

                addEmployeeViewModel.insertEmployee(employee);

                //GET THE CLICKED ATTRIBUTES AND MAKE CONNECTION ME THE EMPLOYEE !!

                for(int i = 0; i < listAttributes.size(); i++){
                    if(listAttributes.get(i).isClicked()){
                        EmployeesAndAttributes join = new EmployeesAndAttributes(employee.getEmployeeID(), listAttributes.get(i).getAttrID());
                        joints.add(join);

                        //I NEED TO INSERT IT !!!  --- MIGHT BREAK
                    }
                }

                //SOMETHING IS WRONG WITH THE FOREIGN KEYS IN THE JOINT MODEL IN DATABASE !!!!!
//                SOMETHING IS WRONG HERE
//                SOMETHING IS WRONG HERE
//                SOMETHING IS WRONG HERE
//                SOMETHING IS WRONG HERE
//                TRYING TO ADD THE CONNECTION OBJECT TO THE CONNECTION TABLE
//                CORRECTED !!!!!! CONTINUE AFTER LUNCH

                EmployeesAndAttributes join = new EmployeesAndAttributes(0,0); // TEST
                addEmployeeViewModel.insertAttributeToEmployee(join);                                 // TEST


                Fragment back = Employees.getEmployeesInstance();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragments_container, back);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });
    }
}