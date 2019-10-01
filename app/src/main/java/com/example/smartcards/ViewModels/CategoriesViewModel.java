package com.example.smartcards.ViewModels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.smartcards.Database.Category.CategoryRepository;

import com.example.smartcards.Models.Categories;

import java.util.List;

public class CategoriesViewModel extends AndroidViewModel {
    private CategoryRepository repository;
    private LiveData<List<Categories>> allCategories;

    public CategoriesViewModel(@NonNull Application application) {
        super(application);
        repository = CategoryRepository.getInstance(application);
        allCategories = repository.retrieveCategoriesTask();
    }

    public void insertCategory(Categories categories){
        Log.d(TAG, "insertCategory: insert activated" + categories);
        repository.insertCategoryTask(categories);
    }

    public void updateCategory(Categories categories){
        repository.updateCategoryTask(categories);
    }

    public void deleteCategory(Categories categories){
        repository.deleteCategoryTask(categories);
    }

    public LiveData<List<Categories>> getAllCategories(){
        return allCategories;
    }

    public void onTakeNewCategory(String listener){
            Categories newCategory = new Categories(listener);
            insertCategory(newCategory);
    }

    private static final String TAG = "CategoriesViewModel";
}
