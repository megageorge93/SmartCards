package com.example.smartcards.ViewModels;
import android.app.Application;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.smartcards.Database.Folder.FoldersRepository;
import com.example.smartcards.Models.Folders;
import java.util.List;
import static androidx.constraintlayout.widget.Constraints.TAG;

public class FoldersViewModel extends AndroidViewModel {
    private FoldersRepository foldersRepository;
    private LiveData<List<Folders>> allFolders;
//    private List<Folders> mAllFolders;
//    private MutableLiveData<List<Folders>> allFolders;

    public FoldersViewModel(@NonNull Application application) {
        super(application);
        foldersRepository = FoldersRepository.getInstance(application);
        allFolders = foldersRepository.retrieveFoldersTask();
//        allFolders.setValue(foldersRepository.retrieveFoldersTask());
    }

//    public FoldersViewModel(@NonNull int categoryId){
//        allFolders = foldersRepository.retrieveFoldersFromCategory(categoryId);
//    }

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

//    public MutableLiveData<List<Folders>> getAllFoldersFromCategory(int currentCategoryId){
//        mAllFolders = foldersRepository.retrieveFoldersFromCategory(currentCategoryId);
//        allFolders.setValue(mAllFolders);
//        return allFolders;
//    }

    public LiveData<List<Folders>> getAllFolders(){return allFolders;}

    public void onTakeNewFolder(String listener, int categoryId){
        Folders newFolder = new Folders(listener, categoryId);
        Log.d(TAG, "onTakeNewFolder: new folder " + newFolder);
        insertFolder(newFolder);
    }
}
