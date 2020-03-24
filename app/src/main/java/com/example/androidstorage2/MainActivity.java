package com.example.androidstorage2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FloatingActionButton floatingActionButton;
    private Adapter adapter;

    private RecyclerView.LayoutManager layoutManager;
    List<EmployeeInformation> list;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUi();
getDetails();
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,DataInsertActivity.class);
                startActivity(intent);
            }
        });
    }
    private void initUi(){
        recyclerView=findViewById(R.id.recycler);
        floatingActionButton=findViewById(R.id.addmore);

    }

    private void getDetails(){
      class GetEmployeeData extends AsyncTask<Void,Void,List<EmployeeInformation>>  {

          @Override
          protected List<EmployeeInformation> doInBackground(Void... voids) {

              List<EmployeeInformation> employeeList = MyAppDatabase
                      .getDatabase(getApplicationContext()).dataAccessObject().getAllEmployee();
              return employeeList;
          }

          @Override
          protected void onPostExecute(List<EmployeeInformation> list) {
              super.onPostExecute(list);

              adapter=new Adapter(MainActivity.this,list);
              layoutManager =new LinearLayoutManager(MainActivity.this);
              recyclerView.setLayoutManager(layoutManager);

              recyclerView.setAdapter(adapter);
              recyclerView.setHasFixedSize(true);
          }
      }
      GetEmployeeData getEmployeeData=new GetEmployeeData();
      getEmployeeData.execute();


        }



    }


