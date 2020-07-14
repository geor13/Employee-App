package database;

import androidx.room.Entity;
import androidx.room.ForeignKey;

//SOS MIGHT BREAK
@Entity(primaryKeys = {"employee_ID", "attribute_ID"}
,
        foreignKeys = {
                @ForeignKey(entity = EmployeesModel.class,
                        parentColumns = "employee_ID",
                        childColumns = "employee_ID",
                        onUpdate = ForeignKey.CASCADE,
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = AttributesModel.class,
                        parentColumns = "attribute_ID",
                        childColumns = "attribute_ID",
                        onUpdate = ForeignKey.CASCADE,
                        onDelete = ForeignKey.CASCADE)}
                        )
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
