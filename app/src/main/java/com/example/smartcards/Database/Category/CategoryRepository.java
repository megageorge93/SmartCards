package com.example.smartcards.Database.Category;
import android.app.Application;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import com.example.smartcards.Database.SmartCardDatabase;
import com.example.smartcards.Models.Categories;
import java.util.List;

public class CategoryRepository {
    private static CategoryRepository instance;

    public static CategoryRepository getInstance(@NonNull Application application){
        if (instance == null){
            instance = new CategoryRepository(application);
        }
        return instance;
    }

    private SmartCardDatabase mSmartCardDatabase;
    private CategoryDao categoryDao;
    private LiveData<List<Categories>> allCategories;

    private CategoryRepository(Application application) {
        mSmartCardDatabase = SmartCardDatabase.getInstance(application);
        categoryDao = mSmartCardDatabase.getCategoriesDao();
        allCategories = categoryDao.getAllCategories();
    }

    public void insertCategoryTask(Categories categories){
        new InsertCategoryTask(categoryDao).execute(categories);
//        int categoryId = categories.getCategoryId();
    }

    public void updateCategoryTask (Categories categories){
        new UpdateCategoryTask(categoryDao).execute(categories);
    }

    public LiveData<List<Categories>> retrieveCategoriesTask(){
        return allCategories;
    }

    public void deleteCategoryTask(Categories categories){
        new DeleteCategoryTask(categoryDao).execute(categories);
    }

    //Async
    private static class InsertCategoryTask extends AsyncTask<Categories, Void, Void>{

        private CategoryDao categoryDao;

        public InsertCategoryTask(CategoryDao categoryDao) {
            this.categoryDao = categoryDao;
        }

        @Override
        protected Void doInBackground(Categories... categories) {
            categoryDao.insertCategory(categories);
            return null;
        }
    }
    private static class UpdateCategoryTask extends AsyncTask<Categories, Void, Void>{

        private CategoryDao categoryDao;

        public UpdateCategoryTask(CategoryDao categoryDao) {
            this.categoryDao = categoryDao;
        }

        @Override
        protected Void doInBackground(Categories... categories) {
            categoryDao.updateCategory(categories);
            return null;
        }
    }

    private static class DeleteCategoryTask extends AsyncTask<Categories, Void, Void>{

        private CategoryDao categoryDao;

        public DeleteCategoryTask(CategoryDao categoryDao) {
            this.categoryDao = categoryDao;
        }

        @Override
        protected Void doInBackground(Categories... categories) {
            categoryDao.deleteCategory(categories);
            return null;
        }
    }
}
