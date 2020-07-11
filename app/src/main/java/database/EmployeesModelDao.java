package database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface EmployeesModelDao {

    @Query("SELECT * FROM Employees")
    LiveData<List<EmployeesModel>> getAllEmployees();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(EmployeesModel employee);

    @Delete
    void delete(EmployeesModel employee);
}
