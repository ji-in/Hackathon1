package org.techtown.mywordapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper dbHelper;
    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createDatabase("WORDAPP.db");

        Button vocaButton = (Button) findViewById(R.id.vocaButton);
        Button wrongvocaButton = (Button) findViewById(R.id.wrongvocaButton);
        Button testButton = (Button) findViewById(R.id.testButton);


        vocaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ShowVocaActivity.class);
                MainActivity.this.startActivity(intent);


            }
        });

//        wrongvocaButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, ShowWrongVocaActivity.class);
//                startActivity(intent);
//
//            }
//        });
//
//        testButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, TestPreviewActivity.class);
//                startActivity(intent);
//            }
//        });
    }


    private void createDatabase(String name) {
        dbHelper = new DatabaseHelper(this);
        database = dbHelper.getWritableDatabase();
    }

//    private void createTable(String name) {
//        if(database == null) {
//            return;
//        }
//
//        database.execSQL("create table if not exists " + name + "("
//                + " english text, "
//                + " korean text, "
//                + " memo text)");
//    }
//
//    public SQLiteDatabase getDatabase() {
//        return database;
//    }
}