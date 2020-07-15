package com.aggelou.employeeapp;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.List;

import database.AttributesModel;
import database.EmployeeWithAttributes;
import database.EmployeesAndAttributes;
import database.EmployeesModel;
import jsonModels.Location;
import jsonModels.RequestResults;

public class PageViewModel extends AndroidViewModel {

    private Repository repository;
    private LiveData<List<AttributesModel>> attributesList;
    private LiveData<List<EmployeesModel>> employeesList;
    private LiveData<List<EmployeeWithAttributes>> employeesWithAttributesList;
    private LiveData<List<EmployeesAndAttributes>> employeesAndAttributes;
    private MutableLiveData<List<AttributesModel>> selectedAttributes = new MutableLiveData<>();

    private MutableLiveData<Location> location = new MutableLiveData<>();


    public PageViewModel(@NonNull Application application) {
        super(application);

        repository = new Repository(application);
        attributesList = repository.getAttributesList();
        employeesList = repository.getEmployeesList();
        employeesWithAttributesList = repository.getEmployeesWithAttributes();
        employeesAndAttributes = repository.getEmployeesAndAttributes();

    }


    public void setLocationRequest(String address, Context context){
        makePlaceRequest(address, context);
    }

    public LiveData<Location> getLocationRequest(){
        return location;
    }



    public LiveData<List<AttributesModel>> getSelectedAttributes(){
        return selectedAttributes;
    }

    public void setSelectedAttributes(List<AttributesModel> selected){
        selectedAttributes.setValue(selected);
    }

    public LiveData<List<EmployeesAndAttributes>> getEmployeesAndAttributes(){
        return employeesAndAttributes;
    }

    public void deleteAttributeEmployeeLink(EmployeesAndAttributes employeesAndAttributes){
        repository.deleteEmployeeAttributeLink(employeesAndAttributes);
    }

    public void deleteSpecifiedLink(int attributeID, int employeeID){
        repository.deleteSpecifiedLink(attributeID, employeeID);
    }

    public void insertAttributeToEmployee(EmployeesAndAttributes employeeAttrLink){
        repository.insertEmployeeAttributeLink(employeeAttrLink);
    }


    public void insertEmployee (EmployeesModel employee){
        repository.insertEmployee(employee);
    }

    public void insertAttribute (AttributesModel attribute){
        repository.insertAttribute(attribute);
    }

    public void deleteEmployee (EmployeesModel employee){
        repository.deleteEmployee(employee);
    }

    public void deleteAttribute (AttributesModel attribute){
        repository.deleteAttribute(attribute);
    }

    public LiveData<List<AttributesModel>> getAttributes(){
        return attributesList;
    }

    public LiveData<List<EmployeesModel>> getEmployeesList(){
        return employeesList;
    }

    public LiveData<List<EmployeeWithAttributes>> getEmployeesWithAttributes(){
        return employeesWithAttributesList;
    }

    private void makePlaceRequest(String address, Context context){
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(context);
        String url ="https://maps.googleapis.com/maps/api/place/findplacefromtext/json?input="+address+"&inputtype=textquery&fields=photos,formatted_address,name,rating,opening_hours,geometry&key=AIzaSyAL7IRjsN8g4FjVzH8Jj2huM7S7W6XZBtI";
        List<Location> list;
// Request a string response from the provided URL.

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        RequestResults result = new Gson().fromJson(response, RequestResults.class);
                        location.setValue(result.getCandidates().get(0).getGeometry().getLocation());  //MAYBE POST
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

}
