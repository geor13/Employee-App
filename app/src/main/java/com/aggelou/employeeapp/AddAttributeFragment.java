package com.aggelou.employeeapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import database.AttributesModel;

public class AddAttributeFragment extends Fragment {

    private PageViewModel addAttributeViewModel;
    private TextView saveNewAttr;
    private TextView cancelNewAttr;
    private EditText newAttributeName;

    public AddAttributeFragment() {
        // Required empty public constructor
    }


    public static AddAttributeFragment newInstance() {
        AddAttributeFragment fragment = new AddAttributeFragment();
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
        return inflater.inflate(R.layout.fragment_add_attribute, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(getView() != null){
            saveNewAttr = getView().findViewById(R.id.create_button_fr);
            cancelNewAttr = getView().findViewById(R.id.cancel_button_fr);
            newAttributeName = getView().findViewById(R.id.enter_attribute_name_fr);
        }

        addAttributeViewModel = new ViewModelProvider(requireActivity()).get(PageViewModel.class);

        saveNewAttr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String addedAttributeName = newAttributeName.getText().toString();
                AttributesModel newAttr = new AttributesModel(addedAttributeName);
                addAttributeViewModel.insertAttribute(newAttr);

                Fragment back = new Attributes();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragments_container, back);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }
}