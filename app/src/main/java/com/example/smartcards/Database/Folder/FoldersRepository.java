package com.example.smartcards.Database.Folder;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.smartcards.Database.Category.CategoryRepository;
import com.example.smartcards.Database.SmartCardDatabase;
import com.example.smartcards.Models.Categories;
import com.example.smartcards.Models.Folders;

import java.util.List;

public class FoldersRepository {
    private static FoldersRepository instance;

    public static FoldersRepository getInstance(@NonNull Application application){
        if (instance == null){
            instance = new FoldersRepository(application);
        }
        return instance;
    }

    private SmartCardDatabase smartCardDatabase;
    private FolderDao folderDao;
    private LiveData<List<Folders>> allFolders;

    public FoldersRepository(Application application) {
        smartCardDatabase = SmartCardDatabase.getInstance(application);
        folderDao = smartCardDatabase.getFoldersDao();
   //     allFolders = folderDao.getAllFoldersFromCategory();
        allFolders = folderDao.getAllFolders();
    }


    public void insertFoldersTask(Folders folders){
        new FoldersRepository.InsertFoldersTask(folderDao).execute(folders);
//        int folderId = folders.getFolderId();
//        int folderCategoryId = folders.getCategoryId();
    }
    public void deleteFoldersTask(Folders folders){
        new FoldersRepository.DeleteFoldersTask(folderDao).execute(folders);
    }
    public void updateFoldersTask(Folders folders){
        new FoldersRepository.UpdateFoldersTask(folderDao).execute(folders);
    }

    public LiveData<List<Folders>> retrieveFoldersTask(){
        return allFolders;
    }

    public LiveData<List<Folders>> retrieveFoldersFromCategory(int currentCategoryId){
        return folderDao.getAllFoldersFromCategory(currentCategoryId);
    }





    //Async

    private static class InsertFoldersTask extends AsyncTask<Folders, Void, Void>{
        private FolderDao folderDao;

        public InsertFoldersTask(FolderDao folderDao) {
            this.folderDao = folderDao;
        }

        @Override
        protected Void doInBackground(Folders... folders) {
            folderDao.insertFolder(folders);
            return null;
        }
    }
    private static class DeleteFoldersTask extends AsyncTask<Folders, Void, Void>{
        private FolderDao folderDao;

        public DeleteFoldersTask(FolderDao folderDao) {
            this.folderDao = folderDao;
        }

        @Override
        protected Void doInBackground(Folders... folders) {
            folderDao.deleteFolder(folders);
            return null;
        }
    }
    private static class UpdateFoldersTask extends AsyncTask<Folders, Void, Void>{
        private FolderDao folderDao;

        public UpdateFoldersTask(FolderDao folderDao) {
            this.folderDao = folderDao;
        }

        @Override
        protected Void doInBackground(Folders... folders) {
            folderDao.updateFolder(folders);
            return null;
        }
    }
}
