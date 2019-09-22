package com.example.smartcards.Database.Category;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.smartcards.Models.Categories;

import java.util.List;

@Dao
public interface CategoryDao {

    @Insert
    void insertCategory(Categories... categories);


    @Query("SELECT * FROM categories")
    LiveData<List<Categories>> getAllCategories();

    @Delete
    void deleteCategory(Categories... categories);

    @Update
    void updateCategory(Categories... categories);
}
