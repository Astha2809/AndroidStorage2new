package com.example.androidstorage2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class DataInsertActivity extends AppCompatActivity {
    private EditText editText_id;
    private EditText editText_name;
    private EditText editText_address;
    private EditText editText_number;
    private Button submit_button;
    public MyAppDatabase myAppDatabase;
    private DataAccessObject dataAccessObject;
    boolean FOR_UPDATE = false;

    int emp_id;
    String emp_name;
    String emp_number;
    String emp_address;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_insert);
        initUi();
        saveData();
        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addData();
            }
        });


    }

    private void initUi() {
        editText_id = findViewById(R.id.edittextid);
        editText_name = findViewById(R.id.edittextname);
        editText_address = findViewById(R.id.edittextaddress);
        editText_number = findViewById(R.id.edittextmobile);
        submit_button = findViewById(R.id.submit);

        final MyAppDatabase myAppDatabase = Room.databaseBuilder(getApplication(), MyAppDatabase.class, "Employee_database")
                .fallbackToDestructiveMigration().allowMainThreadQueries().build();
        dataAccessObject = myAppDatabase.dataAccessObject();


    }

    private void saveData() {
        if (getIntent().hasExtra("record")) {
            FOR_UPDATE = true;

            EmployeeInformation employeeInformation = new EmployeeInformation();

            editText_id.setText(employeeInformation.getId());
            editText_name.setText(employeeInformation.getName());
            editText_address.setText(employeeInformation.getAddress());
            editText_number.setText(employeeInformation.getMobilenumber());


        } else {
            FOR_UPDATE = false;
            submit_button.setText("Submit");

        }
    }

    private void addData() {


        emp_id = Integer.parseInt(editText_id.getText().toString());
        emp_name = editText_name.getText().toString();
        emp_number = editText_number.getText().toString();
        emp_address = editText_address.getText().toString();


        Toast.makeText(DataInsertActivity.this, "data saved", Toast.LENGTH_LONG).show();
        editText_id.setText(" ");
        editText_name.setText(" ");
        editText_number.setText(" ");
        editText_address.setText(" ");

        if (FOR_UPDATE == false) {
            SaveRecord saveRecord = new SaveRecord();
            saveRecord.execute();
        } else {
            UpdateRecord updateRecord = new UpdateRecord();
            updateRecord.execute();
        }
    }

    class SaveRecord extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            EmployeeInformation employeeInformation = new EmployeeInformation();
            employeeInformation.setId(emp_id);
            employeeInformation.setName(emp_name);
            employeeInformation.setMobilenumber(emp_number);
            employeeInformation.setAddress(emp_address);


            MyAppDatabase.getDatabase(getApplicationContext()).dataAccessObject().addEmployee(employeeInformation);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            finish();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            Toast.makeText(getApplicationContext(), "Data Saved.....", Toast.LENGTH_SHORT).show();
        }
    }

    class UpdateRecord extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            EmployeeInformation employeeInformation = new EmployeeInformation();
            employeeInformation.setId(emp_id);
            employeeInformation.setName(emp_name);
            employeeInformation.setMobilenumber(emp_number);
            employeeInformation.setAddress(emp_address);


            MyAppDatabase.getDatabase(getApplicationContext()).dataAccessObject().updateeEmployee(employeeInformation);


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            finish();
            startActivity(new Intent(DataInsertActivity.this, MainActivity.class));
            Toast.makeText(getApplicationContext(), "Record Updated...", Toast.LENGTH_SHORT).show();
        }
    }

}



