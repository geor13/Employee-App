package database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

@Dao
public interface EmployeesAndAttributesDao {

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertLink (EmployeesAndAttributes employeesAndAttributes);

    @Delete
    void deleteLink(EmployeesAndAttributes employeesAndAttributes);

    @Query("DELETE FROM EmployeesAndAttributes WHERE attribute_ID = :attrid AND employee_ID =:emplid")
    void deleteSpecifiedLink(int attrid, int emplid);

    @Transaction
    @Query("SELECT * FROM Employees")
    LiveData<List<EmployeeWithAttributes>> getEmployeesWithAttributes();

    @Transaction
    @Query("SELECT * FROM EmployeesAndAttributes")
    LiveData<List<EmployeesAndAttributes>> getEmployeesAndAttributes();
}
