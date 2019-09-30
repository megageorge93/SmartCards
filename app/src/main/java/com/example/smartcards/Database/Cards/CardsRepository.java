package com.example.smartcards.Database.Cards;
import android.app.Application;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import com.example.smartcards.Database.SmartCardDatabase;
import com.example.smartcards.Models.Cards;
import java.util.List;

public class CardsRepository {
    private static CardsRepository instance;

    public static CardsRepository getInstance(@NonNull Application application){
        if (instance == null){
            instance = new CardsRepository(application);
        }
        return instance;
    }

    private SmartCardDatabase smartCardDatabase;
    private CardsDao cardDao;
    private LiveData<List<Cards>> allCards;

    public CardsRepository(Application application) {
        smartCardDatabase = SmartCardDatabase.getInstance(application);
        cardDao = smartCardDatabase.getCardsDao();
        allCards = cardDao.getAllCards();
    }

    public void insertCardsTask(Cards cards){
        new CardsRepository.InsertCardTask(cardDao).execute(cards);
    }

    public void deleteCardsTask(Cards cards){
        new CardsRepository.DeleteCardTask(cardDao).execute(cards);
    }

    public void updateCardsTask(Cards cards){
        new CardsRepository.UpdateCardTask(cardDao).execute(cards);
    }

    public LiveData<List<Cards>> retrieveCardsTask(){return allCards;}

    public LiveData<List<Cards>> retrieveCardsFromFolder(int currentFolderId){
        return cardDao.getAllCardsFromFolder(currentFolderId);
    }


    //Async
    private static class InsertCardTask extends AsyncTask<Cards, Void, Void> {
        private CardsDao cardDao;

        public InsertCardTask(CardsDao cardDao) {
            this.cardDao = cardDao;
        }

        @Override
        protected Void doInBackground(Cards... cards) {
            cardDao.insertCard(cards);
            return null;
        }
    }
    private static class DeleteCardTask extends AsyncTask<Cards, Void, Void> {
        private CardsDao cardDao;

        public DeleteCardTask(CardsDao cardDao) {
            this.cardDao = cardDao;
        }

        @Override
        protected Void doInBackground(Cards... cards) {
            cardDao.deleteCard(cards);
            return null;
        }
    }

    private static class UpdateCardTask extends AsyncTask<Cards, Void, Void> {
        private CardsDao cardDao;

        public UpdateCardTask(CardsDao cardDao) {
            this.cardDao = cardDao;
        }

        @Override
        protected Void doInBackground(Cards... cards) {
            cardDao.updateCard(cards);
            return null;
        }
    }
}
