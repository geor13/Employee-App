package database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Attributes")
public class AttributesModel {

    public AttributesModel(String attrName){
        this.attrName = attrName;
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "attrID")
    private int attrID;

    @ColumnInfo(name = "attrName")
    private String attrName;


    public long getAttrID() {
        return attrID;
    }

    public String getAttrName() {
        return attrName;
    }

 }
