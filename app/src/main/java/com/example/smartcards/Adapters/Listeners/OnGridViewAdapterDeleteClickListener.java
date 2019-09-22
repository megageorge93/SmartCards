package com.example.smartcards.Adapters.Listeners;

import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcards.Dialogs.DeleteDialog;

public class OnGridViewAdapterDeleteClickListener {
    private final FragmentManager fragmentManager;

    public OnGridViewAdapterDeleteClickListener(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public void onClickDeleteCategory(RecyclerView.ViewHolder viewHolder) {
        DeleteDialog deleteDialog = new DeleteDialog(viewHolder);
        deleteDialog.show(fragmentManager, "DeleteDialog_tag");
    }
}
