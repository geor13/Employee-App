package database;

import androidx.annotation.NonNull;
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
    @NonNull
    private int attrID;

    @ColumnInfo(name = "attrName")
    private String attrName;

    public void setAttrID(int attrID) {
        this.attrID = attrID;
    }

    public int getAttrID() {
        return attrID;
    }

    public String getAttrName() {
        return attrName;
    }

 }
