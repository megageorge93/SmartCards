package com.example.smartcards.ViewModels;
import android.app.Application;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.smartcards.Database.Cards.CardsRepository;
import com.example.smartcards.Models.Cards;
import java.util.List;
import static androidx.constraintlayout.widget.Constraints.TAG;

public class CardsViewModel extends AndroidViewModel {
    private CardsRepository cardsRepository;
    private LiveData<List<Cards>> allCards;

    public CardsViewModel(@NonNull Application application) {
        super(application);
        cardsRepository = CardsRepository.getInstance(application);
        allCards = cardsRepository.retrieveCardsTask();
    }

    public void insertCard (Cards cards) {
        cardsRepository.insertCardsTask(cards);
    }

    public void deleteCard(Cards cards){cardsRepository.deleteCardsTask(cards);}

    public void updateCard(Cards cards){cardsRepository.updateCardsTask(cards);}

    public LiveData<List<Cards>> getAllCards(){
        return allCards;
    }

    public LiveData<List<Cards>> getAllCardsFromFolder(int currentCardsFolderId){
        return cardsRepository.retrieveCardsFromFolder(currentCardsFolderId);
    }

    public void onTakeNewCard(String cardName, String value, int folderId){
        Cards newCard = new Cards(cardName,value,folderId);
        Log.d(TAG, "onTakeNewCard: new card "+ newCard);
        insertCard(newCard);
    }
}
