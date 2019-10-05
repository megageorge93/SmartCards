package com.example.smartcards.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity(tableName = "folders", foreignKeys = @ForeignKey(entity = Categories.class,
        parentColumns = "category_id", childColumns = "current_category_id", onDelete = ForeignKey.CASCADE))

public class Folders {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "folder_id")
    private int folderId;

    @ColumnInfo(name = "folder_name")
    private String folderName;

    @ColumnInfo(name = "current_category_id")
    private int categoryId;

    @Ignore
    public Folders() {
    }

    public Folders(String folderName, int categoryId) {
        this.folderName = folderName;
        this.categoryId = categoryId;
    }
    @Ignore
    public Folders(String folderName) {
        this.folderName = folderName;
    }

    public int getFolderId() {
        return folderId;
    }

    public void setFolderId(int folderId) {
        this.folderId = folderId;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "Folders{" +
                "id=" + folderId +
                ", folderName='" + folderName + '\'' +
                '}';
    }
}
