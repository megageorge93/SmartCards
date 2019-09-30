package com.example.smartcards.Database;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.example.smartcards.Database.Cards.CardsDao;
import com.example.smartcards.Database.Category.CategoryDao;
import com.example.smartcards.Database.Folder.FolderDao;
import com.example.smartcards.Models.Cards;
import com.example.smartcards.Models.Categories;
import com.example.smartcards.Models.Folders;

@Database(entities = {Categories.class, Folders.class, Cards.class}, version = 17)
public abstract class SmartCardDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "Smart_Cards_Database";
    private static SmartCardDatabase instance;
    public abstract CategoryDao getCategoriesDao();
    public abstract FolderDao getFoldersDao();
    public abstract CardsDao getCardsDao();

    public static SmartCardDatabase getInstance(final Context context){
        if (instance == null){
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    SmartCardDatabase.class,
                    DATABASE_NAME
            ).fallbackToDestructiveMigration().build();
        }
        return instance;
    }




//No Idea what r these methods
    @NonNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @NonNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @Override
    public void clearAllTables() {

    }
}
