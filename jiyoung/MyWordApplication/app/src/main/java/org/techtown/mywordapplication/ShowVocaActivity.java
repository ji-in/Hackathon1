package org.techtown.mywordapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ShowVocaActivity extends AppCompatActivity {

    SQLiteDatabase database;
    DatabaseHelper dbHelper;
    RecyclerView recyclerView;
    VocaAdapter adapter;

    String korean = "";
    String english = "";
    String memo = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_voca);

        dbHelper = new DatabaseHelper(this);
        database = dbHelper.getWritableDatabase();

        recyclerView = findViewById(R.id.vacaList);

        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new VocaAdapter();


        Cursor cursor = database.rawQuery("select english, korean, memo from VOCA", null);
        int recordCount = cursor.getCount();

        for(int i=0; i<recordCount; i++){
            cursor.moveToNext();
            english = cursor.getString(0);
            korean = cursor.getString(1);
            memo = cursor.getString(2);

            adapter.addItem(new Voca(english, korean, memo));

        }

        recyclerView.setAdapter(adapter);

        EditText searchText = (EditText) findViewById(R.id.searchText);
        Button searchButton = (Button) findViewById(R.id.searchButton);
        Button addButton = (Button) findViewById(R.id.addButton);
        //Button modifyButton = (Button) findViewById(R.id.modifyButton);
        //Button deleteButton = (Button) findViewById(R.id.deleteButton);

        ImageButton homeButton = findViewById(R.id.homeButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowVocaActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowVocaActivity.this, AddVocaActivity.class);
                startActivity(intent);
                finish();
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String search = "";
                search = searchText.getText().toString();
                if(search == ""){
                    AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                    builder.setMessage("검색어를 입력하세요");
                    builder.setPositiveButton("확인",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                    builder.show();
                }
                searchVoca(search);

            }
        });
    }

    public void searchVoca(String search) {
        adapter.removeItem();
        recyclerView.setAdapter(adapter);

        Cursor cursor = database.rawQuery("select english, korean, memo from VOCA", null);
        int recordCount = cursor.getCount();

        for(int i=0; i<recordCount; i++) {
            cursor.moveToNext();
            english = cursor.getString(0);
            korean = cursor.getString(1);
            memo = cursor.getString(2);

            if (search.equals(english) || search.equals(korean)){
                adapter.addItem(new Voca(english, korean, memo));
            }
        }
        recyclerView.setAdapter(adapter);
    }



}
