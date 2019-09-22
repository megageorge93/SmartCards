package com.example.smartcards.Dialogs;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.smartcards.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class AddCardsDialog extends DialogFragment{

    private static final String TAG = "AddCardsDialog";
    private TextInputEditText frontSideEditText;
    private TextInputEditText backSideEditText;
    private MaterialButton addCardBtn;
    private OnCardInputListener onCardInputListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_cards_dialog, container, false);
        frontSideEditText = view.findViewById(R.id.CardFrontSideText);
        backSideEditText = view.findViewById(R.id.CardBackSideText);
        addCardBtn = view.findViewById(R.id.addCardBtn);

        addCardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputFrontSide = frontSideEditText.getText().toString();
                String inputBackSide = backSideEditText.getText().toString();

                if (!inputFrontSide.isEmpty() && !inputBackSide.isEmpty()){
                    onCardInputListener.sendCardInput(inputFrontSide, inputBackSide);
                }
                getDialog().dismiss();
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            onCardInputListener = (OnCardInputListener) getActivity();
        }catch (ClassCastException e){
            Log.e(TAG, "onAttach: ClassCastException" + e.getMessage());
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onCardInputListener = null;
    }

    public interface OnCardInputListener{
        void sendCardInput(String frontSideInput, String backSideInput);
    }

}
