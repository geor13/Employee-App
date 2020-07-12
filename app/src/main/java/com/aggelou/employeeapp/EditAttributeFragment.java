package com.aggelou.employeeapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import database.AttributesModel;

public class EditAttributeFragment extends Fragment {

    private PageViewModel editAttrViewModel;
    private EditText changeAttributeName;
    private CheckBox noDelete;
    private CheckBox yesDelete;
    private TextView cancelEdit;
    private TextView saveEdit;

    private AttributesModel attribute;


    public EditAttributeFragment() {
        // Required empty public constructor
    }

    public static EditAttributeFragment newInstance() {
        EditAttributeFragment fragment = new EditAttributeFragment();
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
        return inflater.inflate(R.layout.fragment_edit_attribute, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(getView() != null){
            changeAttributeName = getView().findViewById(R.id.change_attribute_name_fr);
            noDelete = getView().findViewById(R.id.no_delete_fr);
            yesDelete = getView().findViewById(R.id.yes_delete_fr);
            cancelEdit = getView().findViewById(R.id.cancel_edit_fr);
            saveEdit = getView().findViewById(R.id.save_edit_fr);
        }

        attribute = (AttributesModel) getArguments().getSerializable(MainActivity.THE_MODEL);

        editAttrViewModel = new ViewModelProvider(requireActivity()).get(PageViewModel.class);

        changeAttributeName.setText(attribute.getAttrName());

        noDelete.setChecked(true);
        yesDelete.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    noDelete.setChecked(false);
                }
            }
        });

        noDelete.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    yesDelete.setChecked(false);
                }
            }
        });

        saveEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newAttributeName = changeAttributeName.getText().toString();
                attribute.setAttrName(newAttributeName);

                if(yesDelete.isChecked()){

                    editAttrViewModel.deleteAttribute(attribute);
                } else {

                    editAttrViewModel.insertAttribute(attribute);
                }

                Fragment back = new Attributes();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragments_container, back);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
        }
        });

        cancelEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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