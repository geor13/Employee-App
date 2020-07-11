package database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {AttributesModel.class, EmployeesModel.class}, exportSchema = false, version = 1)
public abstract class ApplicationDatabase extends RoomDatabase {
    private static  ApplicationDatabase instance;

    @NonNull
    public static ApplicationDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), ApplicationDatabase.class, "DatabaseProject.db")
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return instance;
    }

    public abstract AttributesModelDao getAttributesDao();

    public abstract EmployeesModelDao getEmployeesDao();
}
