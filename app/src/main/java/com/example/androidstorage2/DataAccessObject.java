package com.example.androidstorage2;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DataAccessObject {
    @Query("Select * from employee_info")
    List<EmployeeInformation> getAllEmployee();

    @Insert
     void addEmployee(EmployeeInformation employeeInformation);

    @Delete
    void deleteEmployee(EmployeeInformation employeeInformation);

    @Update
    void updateeEmployee(EmployeeInformation employeeInformation);




}

