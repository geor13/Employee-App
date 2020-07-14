package database;

import androidx.room.Entity;

@Entity(primaryKeys = {"employee_ID", "attribute_ID"})
public class EmployeesAndAttributes {

    public EmployeesAndAttributes(int employee_ID, int attribute_ID){
        this.attribute_ID = attribute_ID;
        this.employee_ID = employee_ID;
    }

    private int employee_ID;
    private int attribute_ID;

    public int getEmployee_ID() {
        return employee_ID;
    }

    public int getAttribute_ID() {
        return attribute_ID;
    }
}
