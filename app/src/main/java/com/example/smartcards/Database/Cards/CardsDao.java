package com.example.smartcards.Database.Cards;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.smartcards.Models.Cards;

import java.util.List;

@Dao
public interface CardsDao {

    @Insert
    void insertCard(Cards...cards);
    @Delete
    void deleteCard(Cards...cards);
    @Update
    void updateCard(Cards...cards);
    @Query("SELECT * FROM cards")
    LiveData<List<Cards>> getAllCards();
    @Query("SELECT * FROM cards WHERE 'current_folder_id' =:currentCardsFolderId")
    LiveData<List<Cards>> getAllCardsFromFolder(int currentCardsFolderId);

}
