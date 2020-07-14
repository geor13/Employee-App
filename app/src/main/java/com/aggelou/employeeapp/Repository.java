package com.aggelou.employeeapp;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import database.ApplicationDatabase;
import database.AttributesModel;
import database.AttributesModelDao;
import database.EmployeeWithAttributes;
import database.EmployeesAndAttributes;
import database.EmployeesAndAttributesDao;
import database.EmployeesModel;
import database.EmployeesModelDao;

public class Repository {
    private AttributesModelDao attributesDAO;
    private EmployeesModelDao employeesDAO;
    private EmployeesAndAttributesDao employeesAndAttributesDao;

    private LiveData<List<AttributesModel>> attributes;
    private LiveData<List<EmployeesModel>> employees;
    private LiveData<List<EmployeeWithAttributes>> employeesWithAttributes;

    public Repository(Application application){
        //INITIALIZE DATABASE
        ApplicationDatabase database = ApplicationDatabase.getInstance(application);

        //INITIALIZE DAO
        attributesDAO = database.getAttributesDao();
        employeesDAO = database.getEmployeesDao();
        employeesAndAttributesDao = database.getEmployeesAndAttributesDao();

        //GETTING Lists
        attributes = attributesDAO.getAllAttributes();
        employees = employeesDAO.getAllEmployees();
        employeesWithAttributes = employeesAndAttributesDao.getEmployeesWithAttributes();
    }

    //INSERT AN ATTRIBUTE - EMPLOYEE JOIN IN JOIN TABLE
    public void insertEmployeeAttributeLink(EmployeesAndAttributes employeeAttrLink){
        new InsertAttributeEmployeeLink(employeesAndAttributesDao).execute(employeeAttrLink);
    }

    //INSERT AN EMPLOYEE TO THE EMPLOYEE TABLE
    public void insertEmployee(EmployeesModel employee){
        new InsertEmployeeAsyncTask(employeesDAO).execute(employee);
    }

    //INSERT AN ATTRIBUTE TO THE ATTRIBUTE TABLE
    public void insertAttribute(AttributesModel attribute){
        new InsertAttributeAsyncTask(attributesDAO).execute(attribute);
    }

    //DELETE AN EMPLOYEE FROM THE EMPLOYEE TABLE
    public void deleteEmployee(EmployeesModel employee){
        new DeleteEmployeeAsyncTask(employeesDAO).execute(employee);
    }

    //DELETE AN ATTRIBUTE FROM THE ATTRIBUTE TABLE
    public void deleteAttribute(AttributesModel attribute){
        new DeleteAttributesAsyncTsk(attributesDAO).execute(attribute);
    }


    //RETRIEVE ALL ATTRIBUTES FROM ATTRIBUTES TABLE
    public LiveData<List<AttributesModel>> getAttributesList(){
        return attributes;
    }

    //RETRIEVE ALL EMPLOYEES FROM EMPLOYEES TABLE
    public LiveData<List<EmployeesModel>> getEmployeesList(){
        return employees;
    }

    public LiveData<List<EmployeeWithAttributes>> getEmployeesWithAttributes(){
        return employeesWithAttributes;
    }

    //ASYNC TASKS FOR THE INSERT AND DELETE OPERATIONS

    //ASYNC TASK TO INSERT EMPLOYEE TO EMPLOYEE TABLE
    private static class InsertEmployeeAsyncTask extends AsyncTask<EmployeesModel, Void, Void> {
        private EmployeesModelDao employeeDao;

        //CONSTRUCTOR
        private InsertEmployeeAsyncTask(EmployeesModelDao employeeDao){
            this.employeeDao = employeeDao;
        }

        @Override
        protected Void doInBackground(EmployeesModel... employeesModels) {
            employeeDao.insert(employeesModels[0]);
            return null;
        }
    }

    //ASYNC TASK TO INSERT EMPLOYEE - ATTRIBUTE LINK TO JOIN TABLE
    private  static class InsertAttributeEmployeeLink extends AsyncTask<EmployeesAndAttributes, Void, Void>{
        private EmployeesAndAttributesDao joinDao;

        private InsertAttributeEmployeeLink(EmployeesAndAttributesDao joinDao){
            this.joinDao = joinDao;
        }

        @Override
        protected Void doInBackground(EmployeesAndAttributes... employeesAndAttributes) {
            joinDao.insertLink(employeesAndAttributes[0]);
            return null;
        }
    }

    //ASYNC TASK TO INSERT ATTRIBUTE TO ATTRIBUTES TABLE
    private static class InsertAttributeAsyncTask extends AsyncTask<AttributesModel, Void, Void>{
        private AttributesModelDao attributeDao;

        //CONSTRUCTOR
        private InsertAttributeAsyncTask(AttributesModelDao attributeDao){
            this.attributeDao = attributeDao;
        }

        @Override
        protected Void doInBackground(AttributesModel... attributesModels) {
            attributeDao.insert(attributesModels[0]);
            return null;
        }
    }

    //ASYNC TASK TO DELETE EMPLOYEE FROM EMPLOYEES TABLE
    private static class DeleteEmployeeAsyncTask extends AsyncTask<EmployeesModel, Void, Void>{
        private EmployeesModelDao employeeDao;

        //CONSTRUCTOR
        private DeleteEmployeeAsyncTask(EmployeesModelDao employeeDao){
            this.employeeDao = employeeDao;
        }

        @Override
        protected Void doInBackground(EmployeesModel... employeesModels) {
            employeeDao.delete(employeesModels[0]);
            return null;
        }
    }

    //ASYNC TASK TO DELETE ATTRIBUTE TO ATTRIBUTES TABLE
    private static class DeleteAttributesAsyncTsk extends AsyncTask<AttributesModel, Void, Void>{
        private AttributesModelDao attributeDao;

        //CONSTRUCTOR
        private DeleteAttributesAsyncTsk(AttributesModelDao attributeDao){
            this.attributeDao = attributeDao;
        }

        @Override
        protected Void doInBackground(AttributesModel... attributesModels) {
            attributeDao.delete(attributesModels[0]);
            return null;
        }
    }

}
