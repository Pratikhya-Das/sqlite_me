package com.example.sqlite_me;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText mname, mid, mbranch;
    private Button madd, mcount;
    private Dbhelper helper;

    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mname = findViewById(R.id.name);
        mid = findViewById(R.id.idd);
        mbranch = findViewById(R.id.branch);
        madd = findViewById(R.id.add);
        mcount = findViewById(R.id.count);
        madd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = mname.getText().toString();
                String sid = mid.getText().toString();
                String branch = mbranch.getText().toString();
                saveToDb(name, sid, branch);
            }
        });

        rv = findViewById(R.id.recycler_view);
        rv.setLayoutManager(new LinearLayoutManager(this));

        mcount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                helper = new Dbhelper(MainActivity.this);

                int count = helper.getStudentCount();
                Toast.makeText(MainActivity.this,"" +count, Toast.LENGTH_SHORT).show();

               setupRecycler( helper.getallstudent());

            }
        });
    }

    private void setupRecycler(List<Student> getallstudent) {
       StudentAdpater adapter = new StudentAdpater(MainActivity.this, (ArrayList<Student>)getallstudent);
       rv.setAdapter(adapter);
   }

    private void saveToDb(String name, String sid, String branch) {
      Dbhelper  helper = new Dbhelper(this);
        helper.insertStudent(name, sid, branch);
    }


}
