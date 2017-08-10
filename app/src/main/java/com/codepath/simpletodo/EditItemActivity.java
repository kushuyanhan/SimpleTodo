package com.codepath.simpletodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditItemActivity extends AppCompatActivity {
    Button btnSaveItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        String content = getIntent().getStringExtra("content");
        final int pos = getIntent().getIntExtra("pos",0);

        final EditText etEditItem = (EditText) findViewById(R.id.etEditItem);
        etEditItem.setText(content);
        etEditItem.setSelection(content.length());

        btnSaveItem = (Button) findViewById(R.id.btnSaveItem);
        btnSaveItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("content", etEditItem.getText().toString());
                intent.putExtra("pos", pos);

                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }





}
