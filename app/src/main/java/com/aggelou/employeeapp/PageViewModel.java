package com.aggelou.employeeapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import database.AttributesModel;
import database.EmployeeWithAttributes;
import database.EmployeesAndAttributes;
import database.EmployeesModel;

public class PageViewModel extends AndroidViewModel {

    private Repository repository;
    private LiveData<List<AttributesModel>> attributesList;
    private LiveData<List<EmployeesModel>> employeesList;
    private LiveData<List<EmployeeWithAttributes>> employeesWithAttributesList;
    private LiveData<List<EmployeesAndAttributes>> employeesAndAttributes;


    public PageViewModel(@NonNull Application application) {
        super(application);

        repository = new Repository(application);
        attributesList = repository.getAttributesList();
        employeesList = repository.getEmployeesList();
        employeesWithAttributesList = repository.getEmployeesWithAttributes();
        employeesAndAttributes = repository.getEmployeesAndAttributes();
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

}
