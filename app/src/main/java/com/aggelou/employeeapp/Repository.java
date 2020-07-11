package com.aggelou.employeeapp;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import database.ApplicationDatabase;
import database.AttributesModel;
import database.AttributesModelDao;
import database.EmployeesModel;
import database.EmployeesModelDao;

public class Repository {
    private AttributesModelDao attributesDAO;
    private EmployeesModelDao employeesDAO;

    private LiveData<List<AttributesModel>> attributes;
    private LiveData<List<EmployeesModel>> employees;

    public Repository(Application application){
        //INITIALIZE DATABASE
        ApplicationDatabase database = ApplicationDatabase.getInstance(application);

        //INITIALIZE DAO
        attributesDAO = database.getAttributesDao();
        employeesDAO = database.getEmployeesDao();

        //GETTING TABLES
        attributes = attributesDAO.getAllAttributes();
        employees = employeesDAO.getAllEmployees();
    }

    public void insertEmployee(EmployeesModel employee){
        new InsertEmployeeAsyncTask(employeesDAO).execute(employee);
    }

    public void insertAttribute(AttributesModel attribute){
        new InsertAttributeAsyncTask(attributesDAO).execute(attribute);
    }

    public void deleteEmployee(EmployeesModel employee){
        new DeleteEmployeeAsyncTask(employeesDAO).execute(employee);
    }

    public void deleteAttribute(AttributesModel attribute){
        new DeleteAttributesAsyncTsk(attributesDAO).execute(attribute);
    }

    public LiveData<List<AttributesModel>> getAttributesList(){
        return attributes;
    }

    public LiveData<List<EmployeesModel>> getEmployeesList(){
        return employees;
    }

    //ASYNC TASKS FOR THE INSERT AND DELETE OPERATIONS
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
