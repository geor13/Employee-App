package database;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class EmployeeWithAttributes {
    @Embedded public EmployeesModel employee;
    @Relation(
            parentColumn = "employee_ID",
            entityColumn = "attribute_ID",
            associateBy = @Junction(EmployeesAndAttributes.class)
    )
    public List<AttributesModel> attributes;
}
