package org.techtown.mywordapplication;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class AddVocaActivity extends AppCompatActivity {

    SQLiteDatabase database;
    DatabaseHelper dbHelper;

    String english;
    String korean;
    String memo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        dbHelper = new DatabaseHelper(this);
        database = dbHelper.getWritableDatabase();

        EditText ENGLISHText = (EditText) findViewById(R.id.ENGLISHText);
        EditText KOREANText = (EditText) findViewById(R.id.KOREANText);
        EditText MEMOText = (EditText) findViewById(R.id.MEMOText);
        Button registerButton = (Button) findViewById(R.id.registerButton);
        ImageButton homeButton = (ImageButton) findViewById(R.id.homeButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddVocaActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String english = ENGLISHText.getText().toString();
                String korean = KOREANText.getText().toString();
                String memo = MEMOText.getText().toString();

                database.execSQL("insert into " + "VOCA"
                + "(english, korean, memo) "
                + " values "
                + "('" + english + "'," + " '" + korean + "',"
                        + " '" + memo + "')");

                Intent registerIntent = new Intent(AddVocaActivity.this, ShowVocaActivity.class);
                startActivity(registerIntent);
                finish();

            }
        });
    }

}
