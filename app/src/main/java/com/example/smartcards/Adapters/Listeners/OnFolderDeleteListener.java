package com.example.smartcards.Adapters.Listeners;

import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcards.Adapters.FolderRecyclerAdapter;
import com.example.smartcards.Dialogs.DeleteDialog;

public class OnFolderDeleteListener {
    private final FragmentManager fragmentManager;

    public OnFolderDeleteListener(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public void onFolderDelete(FolderRecyclerAdapter.FolderViewHolder viewHolder) {
        DeleteDialog deleteDialog = new DeleteDialog(viewHolder);
        deleteDialog.show(fragmentManager, "DeleteDialog_tag");
    }
}

