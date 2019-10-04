package com.example.smartcards.UI;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcards.Adapters.CardsRecyclerAdapter;
import com.example.smartcards.Dialogs.AddCardsDialog;
import com.example.smartcards.Models.Cards;
import com.example.smartcards.R;
import com.example.smartcards.ViewModels.CardsViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class CardsActivity extends AppCompatActivity implements AddCardsDialog.OnCardInputListener {
    private static final String TAG = "CardsActivity";
    CardsViewModel mCardsViewModel;
    CardsRecyclerAdapter cardsAdapter = new CardsRecyclerAdapter();
    AddCardsDialog addCardsDialog = new AddCardsDialog();

    @Override
    public void sendCardInput(String frontSideInput, String backSideInput) {
        Bundle idFromFolderAdapter = getIntent().getExtras();
        int folderId = idFromFolderAdapter.getInt("folder Id");
        //int categoryId = idFromFolderAdapter.getInt("Category Id");
        //folderId & categoryId NOT ok here
        //Log.d(TAG, "sendCardInput: "+frontSideInput + " " + backSideInput + " folder id " + folderId + " category id " + categoryId);
        Log.d(TAG, "sendCardInput: " +frontSideInput + " " + backSideInput + " folder id " + folderId);
        //folder id ok here
        mCardsViewModel.onTakeNewCard(frontSideInput,backSideInput,folderId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle idFromFolderAdapter = getIntent().getExtras();
        final int folderId = idFromFolderAdapter.getInt("folder Id");
        //folderId ok here
        final RecyclerView recyclerView = findViewById(R.id.cardsRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        mCardsViewModel = ViewModelProviders.of(this).get(CardsViewModel.class);
//        mCardsViewModel.getAllCardsFromFolder(folderId).observe(this, new Observer<List<Cards>>() {
        // TODO: create request to get card by ID, now it gets all cards from DB
        mCardsViewModel.getAllCards().observe(this, new Observer<List<Cards>>() {
        @Override
            public void onChanged(List<Cards> cards) {
                recyclerView.setAdapter(cardsAdapter);
                cardsAdapter.setCards(cards);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCardsDialog.show(getSupportFragmentManager(), "add_card");
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public boolean onSupportNavigateUp() {
        NavUtils.navigateUpTo(this, getIntent());
        return true;
    }
}
