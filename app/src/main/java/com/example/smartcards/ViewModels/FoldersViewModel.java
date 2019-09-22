package com.example.smartcards.ViewModels;

import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.smartcards.Database.Folder.FoldersRepository;
import com.example.smartcards.Models.Folders;
import com.example.smartcards.UI.FoldersActivity;

import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class FoldersViewModel extends AndroidViewModel {
    private FoldersRepository foldersRepository;
    private LiveData<List<Folders>> allFolders;

    public FoldersViewModel(@NonNull Application application) {
        super(application);
        foldersRepository = FoldersRepository.getInstance(application);

        allFolders = foldersRepository.retrieveFoldersTask();
    }

    public void insertFolder(Folders folder){
        foldersRepository.insertFoldersTask(folder);
    }

    public void deleteFolder(Folders folder){
        foldersRepository.deleteFoldersTask(folder);
    }

    public void updateFolder(Folders folder){
        foldersRepository.updateFoldersTask(folder);
    }

    public LiveData<List<Folders>> getAllFoldersFromCategory(int currentCategoryId){
        return foldersRepository.retrieveFoldersFromCategory(currentCategoryId);
    }

    public void onTakeNewFolder(String listener, int categoryId){
        Folders newFolder = new Folders(listener, categoryId);
        Log.d(TAG, "onTakeNewFolder: new folder " + newFolder);
        insertFolder(newFolder);
    }
}
