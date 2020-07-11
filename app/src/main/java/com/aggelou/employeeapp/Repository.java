package com.aggelou.employeeapp;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import database.ApplicationDatabase;
import database.AttributesModel;
import database.AttributesModelDao;
import database.EmployeesJoinAttributesDao;
import database.EmployeesModel;
import database.EmployeesModelDao;

public class Repository {
    private AttributesModelDao attributesDAO;
    private EmployeesModelDao employeesDAO;
    private EmployeesJoinAttributesDao employeesJoinAttributesDao;

    private LiveData<List<AttributesModel>> attributes;
    private LiveData<List<EmployeesModel>> employees;
    private LiveData<List<AttributesModel>> attributesOfEmployee;

    public Repository(Application application){
        //INITIALIZE DATABASE
        ApplicationDatabase database = ApplicationDatabase.getInstance(application);

        //INITIALIZE DAO
        attributesDAO = database.getAttributesDao();
        employeesDAO = database.getEmployeesDao();
        employeesJoinAttributesDao = database.getEmployeesJoinAttributesDao();

        //GETTING Lists
        attributes = attributesDAO.getAllAttributes();
        employees = employeesDAO.getAllEmployees();
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

    //RETRIEVE ALL ATTRIBUTES FROM A USER
    public LiveData<List<AttributesModel>> getEmployeeAttributes(int employeeID){
        return employeesJoinAttributesDao.getAttributesFromUser(employeeID);
    }

    //RETRIEVE ALL ATTRIBUTES FROM ATTRIBUTES TABLE
    public LiveData<List<AttributesModel>> getAttributesList(){
        return attributes;
    }

    //RETRIEVE ALL EMPLOYEES FROM EMPLOYEES TABLE
    public LiveData<List<EmployeesModel>> getEmployeesList(){
        return employees;
    }

    //DELETE SPECIFIC ATTRIBUTES FROM JOINT TABLE
    public void deleteAttributesFromUsers(int attributeID){
        new DeleteAttributesFromJointAsyncTask(employeesJoinAttributesDao).execute(attributeID);
    }

    //ASYNC TASKS FOR THE INSERT AND DELETE OPERATIONS

    //ASYNC TASK TO DELETE SPECIFIC ATTRIBUTES FROM JOINT TABLE (AND FROM USERS RESPECTIVELY)
    private static class DeleteAttributesFromJointAsyncTask extends AsyncTask<Integer, Void, Void>{

        private EmployeesJoinAttributesDao employeesAndAttributes;
        private DeleteAttributesFromJointAsyncTask(EmployeesJoinAttributesDao employeesAndAttributes){
            this.employeesAndAttributes = employeesAndAttributes;
        }

        @Override
        protected Void doInBackground(Integer... ints) {
            employeesAndAttributes.deleteLists(ints[0]);
            return null;
        }
    }

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
