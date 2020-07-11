package database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "Employees")
public class EmployeesModel {

    public EmployeesModel(String employeeName, Date employeeDateOfBirth, boolean hasCar, String homeAddress){
        this.employeeName = employeeName;
        this.employeeDateOfBirth = employeeDateOfBirth;
        this.hasCar = hasCar;
        this.homeAddress = homeAddress;
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "employeeID")
    @NonNull
    private int employeeID;

    @ColumnInfo(name = "employeeName")
    private String employeeName;

    @ColumnInfo(name = "employeeDateOfBirth")
    private Date employeeDateOfBirth;

    @ColumnInfo(name = "hasCar")
    private boolean hasCar;

    @ColumnInfo(name = "homeAddress")
    private String homeAddress;

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
