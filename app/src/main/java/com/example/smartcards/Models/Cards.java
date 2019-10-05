package com.example.smartcards.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "cards",foreignKeys = {
        @ForeignKey(entity = Folders.class, parentColumns = "folder_id",
                childColumns = "current_folder_id", onDelete = ForeignKey.CASCADE),
                })
public class Cards {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "card_id")
    private int cardId;
    @ColumnInfo(name = "card_name")
    private String cardName;
    @ColumnInfo(name = "card_value")
    private String cardValue;
    @ColumnInfo(name = "current_folder_id")
    private int folderId;
    @Ignore
    public Cards() {
    }

    public Cards(String cardName, String cardValue, int folderId) {
        this.cardName = cardName;
        this.cardValue = cardValue;
        this.folderId = folderId;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardValue() {
        return cardValue;
    }

    public void setCardValue(String cardValue) {
        this.cardValue = cardValue;
    }

    public int getFolderId() {
        return folderId;
    }

    public void setFolderId(int folderId) {
        this.folderId = folderId;
    }

    @Override
    public String toString() {
        return "Cards{" +
                "cardId=" + cardId +
                ", cardName='" + cardName + '\'' +
                ", cardValue='" + cardValue + '\'' +
                ", folderId=" + folderId +
                '}';
    }
}
