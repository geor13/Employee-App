package database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;
import java.util.Random;

@Entity(tableName = "Employees")
public class EmployeesModel implements Serializable {

    public EmployeesModel(String employeeName,String employeeSurname, Date employeeDateOfBirth, boolean hasCar, String homeAddress){
        this.employeeName = employeeName;
        this.employeeDateOfBirth = employeeDateOfBirth;
        this.hasCar = hasCar;
        this.homeAddress = homeAddress;
        this.employeeSurname = employeeSurname;

        Random rand = new Random();
        this.employeeID = rand.nextInt();
    }


    @PrimaryKey
    @ColumnInfo(name = "employee_ID")
    private int employeeID;

    @ColumnInfo(name = "employeeName")
    private String employeeName;

    @ColumnInfo(name = "employeeDateOfBirth")
    private Date employeeDateOfBirth;

    @ColumnInfo(name = "hasCar")
    private boolean hasCar;

    @ColumnInfo(name = "homeAddress")
    private String homeAddress;

    @ColumnInfo(name = "employeeSurname")
    private String employeeSurname;

    public String getEmployeeSurname() {
        return employeeSurname;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public Date getEmployeeDateOfBirth() {
        return employeeDateOfBirth;
    }

    public boolean isHasCar() {
        return hasCar;
    }

    public String getHomeAddress() {
        return homeAddress;
    }
}
