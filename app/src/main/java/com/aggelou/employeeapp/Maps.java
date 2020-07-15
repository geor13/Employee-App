package com.aggelou.employeeapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import database.AttributesModel;
import database.EmployeeWithAttributes;
import database.EmployeesModel;

public class Maps extends Fragment {

    public interface EmptySearchListener{
        void emptyAttributesList();
    }

    private RecyclerView availableAttributes;
    private RecyclerView employeesResults;
    private PageViewModel mapsViewModel;
    private Button searchButton;

    private List<EmployeeWithAttributes> employees;
    private List<AttributesModel> clickedAttributes;
    private List<AttributesModel> allAttributes;

    private EmptySearchListener listener;


    public static Maps getInstanceMaps(){
        return new Maps();
    }

    public Maps() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.listener = (EmptySearchListener)getActivity();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(getView() != null){
            availableAttributes = getView().findViewById(R.id.available_attributes_list);
            employeesResults = getView().findViewById(R.id.resulted_employees_list);
            searchButton = getView().findViewById(R.id.search_employees_button);
        }

        employees = new ArrayList<>();
        clickedAttributes = new ArrayList<>();

        availableAttributes.setLayoutManager(new LinearLayoutManager(getActivity()));
        final SelectedAttributesAdapter adapter = new SelectedAttributesAdapter(getActivity());
        availableAttributes.setAdapter(adapter);

        employeesResults.setLayoutManager(new LinearLayoutManager(getActivity()));
        final SearchedEmployeesAdapter employeeAdapter = new SearchedEmployeesAdapter(getActivity());
        employeesResults.setAdapter(employeeAdapter);

        mapsViewModel = new ViewModelProvider(requireActivity()).get(PageViewModel.class);

        mapsViewModel.getAttributes().observe(getViewLifecycleOwner(), new Observer<List<AttributesModel>>() {
            @Override
            public void onChanged(List<AttributesModel> attributesModels) {
                adapter.changeSelectedAttributes(attributesModels);
                allAttributes = attributesModels;
            }
        });

        mapsViewModel.getSelectedAttributes().observe(getViewLifecycleOwner(), new Observer<List<AttributesModel>>() {
            @Override
            public void onChanged(List<AttributesModel> attributesModels) {
                clickedAttributes = attributesModels;
            }
        });

        mapsViewModel.getEmployeesWithAttributes().observe(getViewLifecycleOwner(), new Observer<List<EmployeeWithAttributes>>() {
            @Override
            public void onChanged(List<EmployeeWithAttributes> employeeWithAttributes) {
                employees = employeeWithAttributes;
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<EmployeesModel> searchedEmployees = new ArrayList<>();
                List<EmployeesModel> finalSearchedEmployees = new ArrayList<>();

                for(int i = 0; i < employees.size(); i++){

                    for(int z = 0; z < employees.get(i).attributes.size(); z++){

                        for(int y = 0; y < clickedAttributes.size(); y++){

                            if( employees.get(i).attributes.get(z).getAttrID() == clickedAttributes.get(y).getAttrID()){

                                searchedEmployees.add(employees.get(i).employee);

                            }

                        }
                    }
                }

                for(int x = 0; x < searchedEmployees.size(); x++){

                    if(!finalSearchedEmployees.contains(searchedEmployees.get(x))){

                        finalSearchedEmployees.add(searchedEmployees.get(x));
                    }
                }

                employeeAdapter.changeEmployeeList(finalSearchedEmployees);

            }
        });

    }

    @Override
    public void onDetach() {
        super.onDetach();

        for(int i = 0; i < allAttributes.size(); i++){
            allAttributes.get(i).setClicked(false);
        }

        if(listener != null){
            listener.emptyAttributesList();
        }
    }
}