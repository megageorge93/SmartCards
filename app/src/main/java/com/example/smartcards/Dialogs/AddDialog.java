package com.example.smartcards.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcards.Adapters.GridViewAdapter;
import com.example.smartcards.Database.Category.CategoryRepository;
import com.example.smartcards.Models.Categories;
import com.example.smartcards.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


public class AddDialog extends DialogFragment {
    private static final String TAG = "AddDialog";
    private TextInputEditText categoryName;
    private TextInputLayout categoryNameHint;
    private MaterialButton addButton;
    private TextView textView;
    private OnInputListener inputListener;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_dialog, container, false);
        categoryName = view.findViewById(R.id.addCategoryName);
        categoryNameHint = view.findViewById(R.id.addCategoryNameHint);
        addButton = view.findViewById(R.id.addCategoryBtn);
        textView = view.findViewById(R.id.textView);
        final Bundle args = getArguments();
        Log.d(TAG, "onCreateView: "+ args);
        if (args!=null) {
            Boolean openedViaCategoy = args.getBoolean("fromMainActivity");
            Log.d(TAG, "onCreateView: "+ openedViaCategoy);

            if (openedViaCategoy == true) {
                setTextToCategory();
            } else
                setTextToFolder();
        }
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputCategoryName = categoryName.getText().toString();
                Log.d(TAG, "onClick: " + inputCategoryName);
                if ( !inputCategoryName.isEmpty() ) {
                   inputListener.sendInput(inputCategoryName);

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
            inputListener = (OnInputListener) getActivity();
        }catch (ClassCastException e){
            Log.e(TAG, "onAttach: ClassCastException" + e.getMessage());
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        inputListener = null;
    }

    public interface OnInputListener{
               void sendInput(String inputCategoryName);
    }

    public void setTextToCategory(){

        textView.setText(R.string.new_category);
        categoryName.setHint(R.string.category_name_hint);
        categoryNameHint.setHint("Enter Category Name");
    }
    public void setTextToFolder(){
        textView.setText(R.string.new_folder);
        categoryName.setHint(R.string.folder_name_hint);
        categoryNameHint.setHint("Enter Folder Name");
    }
}
