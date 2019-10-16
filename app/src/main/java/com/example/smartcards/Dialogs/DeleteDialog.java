package com.example.smartcards.Dialogs;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;
import com.example.smartcards.R;
import com.google.android.material.button.MaterialButton;

public class DeleteDialog extends DialogFragment {
    private static final String TAG = "DeleteDialog";
    MaterialButton yesButton;
    MaterialButton noButton;
    RecyclerView.ViewHolder viewHolder;
 //   final int adapterPosition;
    OnDeleteInputListener deleteInputListener;

    public interface OnDeleteInputListener{
        void sendDeleteInput(boolean deleteBtnIsPressed, RecyclerView.ViewHolder viewHolder);
    }


    public DeleteDialog(RecyclerView.ViewHolder viewHolder) {
        this.viewHolder = viewHolder;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_delete_dialog, container, false);
        yesButton = view.findViewById(R.id.YesBtn);
        noButton = view.findViewById(R.id.NoBtn);

        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean deleteIsPressed = false;
                Log.d(TAG, "onClick: clicked NO, deleteBtn Pressed? " + deleteIsPressed);

                dismiss();
            }
        });

        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean deleteIsPressed = true;
                deleteInputListener.sendDeleteInput(deleteIsPressed, viewHolder);
                Log.d(TAG, "onClick: clicked "+ deleteIsPressed);
                dismiss();
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            deleteInputListener = (DeleteDialog.OnDeleteInputListener) getActivity();
        }catch (ClassCastException e){
            Log.e(TAG, "onAttach: ClassCastException" + e.getMessage());
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        deleteInputListener = null;
    }

}
