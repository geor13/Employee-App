package com.aggelou.employeeapp;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import database.AttributesModel;
import database.EmployeesModel;

public class MainActivity extends AppCompatActivity implements AttributesAdapter.AdapterListener, EmployeesAdapter.EmployeeAdapterListener {
    private BottomNavigationView bottomNav;
    private PageViewModel actViewModel;

    public static final String THE_MODEL = "com.aggelou.employeeapp.THE_MODEL";
    public static final int EDIT_ATTRIBUTE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNav = (BottomNavigationView)findViewById(R.id.container_bottom_nav);
        bottomNav.setOnNavigationItemSelectedListener(bottomNavMethod);
        displayAttributes();

        actViewModel = new ViewModelProvider(this).get(PageViewModel.class);

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    public void displayAttributes(){
        Attributes attributes = Attributes.getAttrInstance();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragments_container, attributes).addToBackStack(null).commit();
    }


    private BottomNavigationView.OnNavigationItemSelectedListener bottomNavMethod = new BottomNavigationView.OnNavigationItemSelectedListener(){
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment fragmentObj = null;

            switch(item.getItemId()){
                case R.id.tab_attributes:
                    fragmentObj = Attributes.getAttrInstance();
                    break;
                case R.id.tab_employees:
                    fragmentObj = Employees.getEmployeesInstance();
                    break;
                case R.id.tab_maps:
                    fragmentObj = Maps.getInstanceMaps();
                    break;
            }

            if(fragmentObj != null){
                getSupportFragmentManager().beginTransaction().replace(R.id.fragments_container, fragmentObj).commit();
            }
            return true;
        }
    };

    @Override
    public void deleteClicked(AttributesModel attribute) {
        actViewModel.deleteAttribute(attribute);
    }

    @Override
    public void editClicked(AttributesModel attribute) {

        //BEGIN AN EDIT ATTRIBUTE FRAGMENT AND SEND IT THE attribute
        Bundle bundle = new Bundle();
        bundle.putSerializable(THE_MODEL, attribute);

        EditAttributeFragment editAttribute = EditAttributeFragment.newInstance();
        editAttribute.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragments_container, editAttribute).addToBackStack(null).commit();

    }


    @Override
    public void deleteClickedEmployee(EmployeesModel employee) {
        actViewModel.deleteEmployee(employee);
    }

    @Override
    public void editClickedEmployee(EmployeesModel employee) {

    }
}