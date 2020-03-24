package com.example.androidstorage2;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "employee_info")
public class EmployeeInformation {
    @PrimaryKey
    private int id;
    @ColumnInfo(name = "employee_name")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobilenumber() {
        return mobilenumber;
    }

    public void setMobilenumber(String mobilenumber) {
        this.mobilenumber = mobilenumber;
    }

    @ColumnInfo(name="employee_address")
    private String address;

    @ColumnInfo(name = "employee_mobile")
    private String mobilenumber;
}
