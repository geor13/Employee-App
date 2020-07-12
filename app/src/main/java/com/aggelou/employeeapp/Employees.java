package com.aggelou.employeeapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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
import java.util.List;

import database.AttributesModel;
import database.EmployeesModel;

public class Employees extends Fragment {
    private PageViewModel employeeViewModel;
    private RecyclerView employeeList;

    private Button addEmployeeButton;

    public static final int ADD_EMPLOYEE_CODE_SUCCESS = 1;
    public static final int ADD_EMPLOYEE_CODE_CANCEL = 2;
    public static final int ADD_EMPLOYEE_REQUEST_CODE = 3;

    public static final String ATTRIBUTES_LIST_NAME = "com.aggelou.employeeapp.ATTRIBUTES_LIST";

    //STORE THE LIST OF ATTRIBUTES HERE AND UPDATE WHEN THEY CHANGE
    private List<AttributesModel> allAttributes;

    public static Employees getEmployeesInstance(){
        return new Employees();
    }

    public Employees() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_employees, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(getView() != null){
            employeeList = getView().findViewById(R.id.employees_list);
            addEmployeeButton = getView().findViewById(R.id.add_employee_button);
        }
        allAttributes = new ArrayList<>();

        employeeList.setLayoutManager(new LinearLayoutManager(getActivity()));
        final EmployeesAdapter adapter = new EmployeesAdapter(getActivity());
        employeeList.setAdapter(adapter);

        employeeViewModel = new ViewModelProvider(requireActivity()).get(PageViewModel.class);

        //LISTEN FOR CHANGES IN THE ATTRIBUTE LIST
        employeeViewModel.getAttributes().observe(getViewLifecycleOwner(), new Observer<List<AttributesModel>>() {
            @Override
            public void onChanged(List<AttributesModel> attributesModels) {
                allAttributes =  attributesModels;
            }
        });

        //  LISTEN FOR CHANGES IN THE EMPLOYEES LIST AND ADD THE NEW LIST TO THE ADAPTER
        employeeViewModel.getEmployeesList().observe(getViewLifecycleOwner(), new Observer<List<EmployeesModel>>() {
            @Override
            public void onChanged(List<EmployeesModel> employeesModels) {
                adapter.changeEmployees(employeesModels);
            }
        });

        //LISTEN FOR CLICK EVENT AND START THE AddEmployeeActivity TO ADD NEW EMPLOYEE ... NEED TO PASS THE LIST OF ATTRIBUTES
        addEmployeeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment addAttribute = new AddAttributeFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragments_container, addAttribute);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }
}