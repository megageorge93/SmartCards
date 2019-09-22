package com.example.smartcards.Models;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "categories")
public class Categories {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "category_id")
    private int categoryId;

    @ColumnInfo(name = "category_name")
    private String categoryName;

    @Ignore
    public Categories() {
    }

    public Categories(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "Categories{" +
                "id=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }



}
