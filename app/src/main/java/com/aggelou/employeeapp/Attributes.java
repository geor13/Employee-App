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

import java.util.List;

import database.AttributesModel;

public class Attributes extends Fragment {
    private RecyclerView attributesList;
    private Button addNewAttributeButton;
    private PageViewModel fragViewModel;

    public static Attributes getAttrInstance(){
        return new Attributes();
    }

    public Attributes() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_attributes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(getView()!= null){
            attributesList = getView().findViewById(R.id.attributes_list); //GET A REFERENCE TO THE RECYCLER VIEW
            addNewAttributeButton = getView().findViewById(R.id.add_attribute);
        }


        attributesList.setLayoutManager(new LinearLayoutManager(getActivity()));
        final AttributesAdapter adapter = new AttributesAdapter(getActivity());
        attributesList.setAdapter(adapter);

        fragViewModel = new ViewModelProvider(requireActivity()).get(PageViewModel.class);

        addNewAttributeButton.setOnClickListener(new View.OnClickListener() {
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

        //LISTEN FOR CHANGES IN ATTRIBUTES LIST
        fragViewModel.getAttributes().observe(getViewLifecycleOwner(), new Observer<List<AttributesModel>>() {
            @Override
            public void onChanged(List<AttributesModel> attributesModels) {
                adapter.changeAttributes(attributesModels);
            }
        });
    }

}