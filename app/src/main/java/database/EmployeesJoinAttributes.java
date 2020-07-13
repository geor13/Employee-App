package database;

import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(
        tableName = "Employee_Attribute_Join",
        primaryKeys = {"attribute_id", "employee_id"},
        foreignKeys = {
                @ForeignKey(entity = AttributesModel.class,
                            parentColumns = "attribute_ID",
                            childColumns = "attribute_id"),
                @ForeignKey(entity = EmployeesModel.class,
                            parentColumns = "employee_ID",
                            childColumns = "employee_id")}

)
public class EmployeesJoinAttributes {
    private int employee_id;
    private int attribute_id;

    public EmployeesJoinAttributes(int employee_id, int attribute_id){
        this.attribute_id = attribute_id;
        this.employee_id = employee_id;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public int getAttribute_id() {
        return attribute_id;
    }
}
