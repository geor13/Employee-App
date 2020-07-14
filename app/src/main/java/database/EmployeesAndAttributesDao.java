package database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

@Dao
public interface EmployeesAndAttributesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertLink (EmployeesAndAttributes employeesAndAttributes);

    @Transaction
    @Query("SELECT * FROM Employees")
    LiveData<List<EmployeeWithAttributes>> getEmployeesWithAttributes();
}
