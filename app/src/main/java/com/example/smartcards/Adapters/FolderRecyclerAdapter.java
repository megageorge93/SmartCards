package com.example.smartcards.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcards.Models.Categories;
import com.example.smartcards.Models.Folders;
import com.example.smartcards.R;
import com.example.smartcards.UI.CardsActivity;

import java.util.ArrayList;
import java.util.List;

public class FolderRecyclerAdapter extends RecyclerView.Adapter<FolderRecyclerAdapter.FolderViewHolder> {

    private static final String TAG = "FolderRecyclerAdapter";

    public interface OnFolderClickListener{
        void onFolderClick(int position);
    }

    private List<Folders> mFolders = new ArrayList<>();
    int currentFolderId;

    @NonNull
    @Override
    public FolderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.folder_item, parent, false);
        return new FolderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FolderViewHolder holder, int position) {
        Folders currentFolder = mFolders.get(position);
        holder.folderName.setText(currentFolder.getFolderName());
    }

    @Override
    public int getItemCount() {
        return mFolders.size();
    }

    public class FolderViewHolder extends RecyclerView.ViewHolder{
        TextView folderName;
        ImageView folderPlayBtn;

        public FolderViewHolder(@NonNull View itemView) {
            super(itemView);
            this.folderName = itemView.findViewById(R.id.folder_name);
            this.folderPlayBtn = itemView.findViewById(R.id.folderPlayBtn);

            folderName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int folderPosition = getAdapterPosition();
                    currentFolderId = mFolders.get(folderPosition).getFolderId();
                    //CurrentFolderId is ok here
                    Intent intent = new Intent(view.getContext(), CardsActivity.class);
                    intent.putExtra("folder Id", currentFolderId);
                    view.getContext().startActivity(intent);
                }
            });

            folderPlayBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
    }

    public void setFolders(List<Folders> mFolders){
        this.mFolders = mFolders;
        if (mFolders != null){
            notifyDataSetChanged();
        }
    }

}
