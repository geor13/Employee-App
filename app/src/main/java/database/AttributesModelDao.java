package database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AttributesModelDao {

    @Query("SELECT * FROM Attributes")
    LiveData<List<AttributesModel>> getAllAttributes();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(AttributesModel attribute);

    @Delete
    void delete(AttributesModel attribute);
}
