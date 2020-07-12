package com.aggelou.employeeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddAttributeActivity extends AppCompatActivity {

    private EditText addAttrTitle;
    private TextView cancelAddingAttr;
    private TextView addNewAttr;

    public static final String EXTRA_TITLE = "com.aggelou.employeeapp.EXTRA_TITLE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_attribute);

        addAttrTitle = (EditText)findViewById(R.id.enter_attribute_name);
        cancelAddingAttr = (TextView)findViewById(R.id.cancel_button);
        addNewAttr = (TextView)findViewById(R.id.create_button);

        addNewAttr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAttribute();
            }
        });

        cancelAddingAttr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                setResult(Attributes.FRAGMENT_RESULT_CODE_CANCEL, intent);
                finish();
            }
        });
    }

    private void addAttribute(){
        String title = addAttrTitle.getText().toString();

        if(title.trim().isEmpty()){
            Toast.makeText(this, "Please insert a title", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(EXTRA_TITLE, title);
        setResult(Attributes.FRAGMENT_RESULT_CODE_SUCCESS, intent);
        finish();
    }
}