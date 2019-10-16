package com.example.smartcards.Adapters;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcards.Adapters.Listeners.OnFolderDeleteListener;
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
    OnFolderDeleteListener onFolderDeleteListener;

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

    public void setOnFolderDeleteListener(OnFolderDeleteListener onFolderDeleteListener){
        this.onFolderDeleteListener = onFolderDeleteListener;
    }

   public ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView,
                              @NonNull RecyclerView.ViewHolder viewHolder,
                              @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            Log.d(TAG, "onSwiped: "+ viewHolder.getAdapterPosition() + " " + viewHolder);
            onFolderDeleteListener.onFolderDelete((FolderViewHolder) viewHolder);

        }

//        @Override
//        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
//            View itemView = viewHolder.itemView;
//            Drawable deleteIcon = ContextCompat.getDrawable(itemView.getContext(), R.drawable.ic_delete);
//            int iconMargin =
//
//            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
//        }
    };
}
