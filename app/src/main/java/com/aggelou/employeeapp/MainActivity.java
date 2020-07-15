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

import java.util.ArrayList;
import java.util.List;

import database.AttributesModel;
import database.EmployeesModel;

public class MainActivity extends AppCompatActivity implements AttributesAdapter.AdapterListener, EmployeesAdapter.EmployeeAdapterListener, EmployeeAttributesAdapter.AttributesInEmployeeListener, SelectedAttributesAdapter.SelectAttributeListener, Maps.EmptySearchListener, SearchedEmployeesAdapter.MapListener {
    private BottomNavigationView bottomNav;
    private PageViewModel actViewModel;
    private List<AttributesModel> selectedAttributes;

    public static final String THE_MODEL = "com.aggelou.employeeapp.THE_MODEL";
    public static final String THE_EMPLOYEE = "com.aggelou.employeeapp.THE_EMPLOYEE";
    public static final String THE_RESULT_EMPLOYEES = "com.aggelou.employeeapp.THE_RESULT_EMPLOYEES";
    public static final String TAG = "helloooooo";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNav = (BottomNavigationView)findViewById(R.id.container_bottom_nav);
        bottomNav.setOnNavigationItemSelectedListener(bottomNavMethod);
        displayAttributes();

        actViewModel = new ViewModelProvider(this).get(PageViewModel.class);
        selectedAttributes = new ArrayList<>();

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

    @Override
    protected void onResume() {
        super.onResume();
//
//            // Instantiate the RequestQueue.
//            RequestQueue queue = Volley.newRequestQueue(this);
//            String url ="https://maps.googleapis.com/maps/api/place/findplacefromtext/json?input=Παρθενωνας&inputtype=textquery&fields=photos,formatted_address,name,rating,opening_hours,geometry&key=AIzaSyAL7IRjsN8g4FjVzH8Jj2huM7S7W6XZBtI";
//
//// Request a string response from the provided URL.
//            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
//                    new Response.Listener<String>() {
//                        @Override
//                        public void onResponse(String response) {
//
//                            RequestResults result = new Gson().fromJson(response, RequestResults.class);
//
//                        }
//                    }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//
//                }
//            });
//
//// Add the request to the RequestQueue.
//            queue.add(stringRequest);

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
        Bundle bundle = new Bundle();
        bundle.putSerializable(THE_EMPLOYEE, employee);

        EditEmployeeFragment editemployee = EditEmployeeFragment.newInstance();
        editemployee.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragments_container, editemployee).addToBackStack(null).commit();
    }

    @Override
    public void deleteAttributeInEmployee(EmployeesModel employee, AttributesModel attribute) {
        actViewModel.deleteSpecifiedLink(attribute.getAttrID(), employee.getEmployeeID());
    }

    @Override
    public void addToSelectedAttributes(AttributesModel attribute) {
        selectedAttributes.add(attribute);
        actViewModel.setSelectedAttributes(selectedAttributes);
    }

    @Override
    public void removeSelectedAttribute(AttributesModel attribute) {
        selectedAttributes.remove(attribute);
        actViewModel.setSelectedAttributes(selectedAttributes);
    }

    @Override
    public void emptyAttributesList() {
        selectedAttributes.clear();
    }

    @Override
    public void goToMap(List<EmployeesModel> employees) {

//        actViewModel.setLocationRequest(employees.get(0).getHomeAddress(), this);

        MapFragment mapFragment = new MapFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragments_container, mapFragment).addToBackStack(null).commit();

        for(int i = 0; i < employees.size(); i++){

            actViewModel.setLocationRequest(employees.get(i).getHomeAddress(), this);

        }

    }


}