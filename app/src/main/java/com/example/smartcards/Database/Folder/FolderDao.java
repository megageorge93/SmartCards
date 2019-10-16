package com.example.smartcards.Database.Folder;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.smartcards.Models.Folders;

import java.util.List;

@Dao
public interface FolderDao {

    @Insert
    void insertFolder(Folders... folders);

    @Query("SELECT * FROM folders")
    LiveData<List<Folders>> getAllFolders();
//    List<Folders> getAllFolders();

    @Query("SELECT * FROM folders WHERE current_category_id = :currentCategoryId")
    LiveData<List<Folders>> getAllFoldersFromCategory(int currentCategoryId);
//    List<Folders> getAllFoldersFromCategory(int currentCategoryId);

    @Delete
    void deleteFolder(Folders... folders);
    @Update
    void updateFolder(Folders... folders);

}
