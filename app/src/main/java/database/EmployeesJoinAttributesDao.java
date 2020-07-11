package database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface EmployeesJoinAttributesDao {

    @Insert
    void insert(EmployeesJoinAttributes employeesJoinAttributes);

    //QUERY TO DELETE ALL CONNECTIONS FROM LIST
    @Query("DELETE FROM Employee_Attribute_Join WHERE attribute_id =:attributeID")
    void deleteLists(int attributeID);


    @Query("SELECT * FROM Attributes INNER JOIN Employee_Attribute_Join ON Attributes.attrID = Employee_Attribute_Join.attribute_id WHERE Employee_Attribute_Join.employee_id = :employeeID")
    LiveData<List<AttributesModel>> getAttributesFromUser(int employeeID);
}
