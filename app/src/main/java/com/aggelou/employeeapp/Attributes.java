package com.aggelou.employeeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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

    private static final int ADD_NOTE_REQUEST_CODE = 1;
    public static final int FRAGMENT_RESULT_CODE_SUCCESS = 12;
    public static final int FRAGMENT_RESULT_CODE_CANCEL = 13;

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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
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
                Intent intent = new Intent(getActivity(), AddAttributeActivity.class);
                startActivityForResult(intent, ADD_NOTE_REQUEST_CODE);
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == ADD_NOTE_REQUEST_CODE && resultCode == FRAGMENT_RESULT_CODE_SUCCESS){
            String title = data.getStringExtra(AddAttributeActivity.EXTRA_TITLE);
            AttributesModel attribute = new AttributesModel(title);
            fragViewModel.insertAttribute(attribute);
            Toast.makeText(getActivity(), "Attribute saved", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "Canceled adding attribute", Toast.LENGTH_SHORT).show();
        }
    }

}