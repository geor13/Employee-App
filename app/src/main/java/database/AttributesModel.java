package database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Random;

@Entity(tableName = "Attributes")
public class AttributesModel implements Serializable {

    public AttributesModel(String attrName){
        this.attrName = attrName;
        this.isClicked = false;

        Random rand = new Random();
        this.attrID = rand.nextInt();
    }

    @PrimaryKey
    @ColumnInfo(name = "attribute_ID")
    private int attrID;

    @ColumnInfo(name = "attrName")
    private String attrName;

    @Ignore
    private boolean isClicked;

    public boolean isClicked() {
        return isClicked;
    }

    public void setClicked(boolean clicked) {
        isClicked = clicked;
    }

    public void setAttrID(int attrID) {
        this.attrID = attrID;
    }

    public int getAttrID() {
        return attrID;
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }
}
