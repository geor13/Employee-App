package com.aggelou.employeeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import database.AttributesModel;

public class EditAttribute extends AppCompatActivity {

    private EditText changeAttrName;
    private CheckBox yesDelete;
    private CheckBox noDelete;
    private TextView cancelEditing;
    private TextView storeEditing;
    private AttributesModel attribute;

    public static final String IS_DELETED = "com.aggelou.employeeapp.IS_DELETED";
    public static final String NEW_ATTRIBUTE = "com.aggelou.employeeapp.NEW_NAME";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_attribute);

        changeAttrName = findViewById(R.id.change_attribute_name);
        yesDelete = findViewById(R.id.yes_delete);
        noDelete = findViewById(R.id.no_delete);
        cancelEditing = findViewById(R.id.cancel_edit);
        storeEditing = findViewById(R.id.save_edit);

        Intent intent = getIntent();
        attribute = (AttributesModel)intent.getSerializableExtra(MainActivity.THE_MODEL);

        changeAttrName.setText(attribute.getAttrName());
        noDelete.setChecked(true);

        yesDelete.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    noDelete.setChecked(false);
                }
            }
        });

        noDelete.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    yesDelete.setChecked(false);
                }
            }
        });

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        storeEditing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newAttrName = changeAttrName.getText().toString();
                boolean isDeleted = yesDelete.isChecked();

                if(newAttrName.trim().isEmpty()){
                    Toast.makeText(EditAttribute.this, "Please provide an attribute name", Toast.LENGTH_SHORT).show();
                    return;
                } else {

                    attribute.setAttrName(newAttrName);

                    Intent data = new Intent();
                    data.putExtra(IS_DELETED, isDeleted);
                    data.putExtra(NEW_ATTRIBUTE, attribute);
                    setResult(RESULT_OK, data);
                    finish();
                }
            }
        });

        cancelEditing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }
}