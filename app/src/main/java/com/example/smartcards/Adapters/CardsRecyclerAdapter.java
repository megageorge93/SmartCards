package com.example.smartcards.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcards.Models.Cards;
import com.example.smartcards.R;

import java.util.ArrayList;
import java.util.List;

public class CardsRecyclerAdapter extends RecyclerView.Adapter<CardsRecyclerAdapter.CardsViewHolder> {
    private static final String TAG = "CardsRecyclerAdapter";

    private List<Cards> mCards = new ArrayList<>();
    int currentCardId;

    @NonNull
    @Override
    public CardsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        return new CardsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardsViewHolder holder, int position) {
        Cards currentCard = mCards.get(position);
        holder.cardName.setText(currentCard.getCardName());
    }

    @Override
    public int getItemCount() {
        return mCards.size();
    }

    public class CardsViewHolder extends RecyclerView.ViewHolder{
        TextView cardName;
        ImageView playBtn;

        public CardsViewHolder(@NonNull View itemView) {
            super(itemView);
            this.cardName = itemView.findViewById(R.id.card_name);
            this.playBtn = itemView.findViewById(R.id.cardPlayBtn);

            cardName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

            playBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
    }

    public void setCards(List<Cards> mCards){
        this.mCards = mCards;
        if (mCards != null){
            notifyDataSetChanged();
        }
    }
}
