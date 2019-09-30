package com.example.smartcards.Adapters;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.smartcards.Adapters.Listeners.OnGridViewAdapterDeleteClickListener;
import com.example.smartcards.Models.Categories;
import com.example.smartcards.R;
import com.example.smartcards.UI.FoldersActivity;
import java.util.ArrayList;
import java.util.List;

public class GridViewAdapter extends RecyclerView.Adapter<GridViewAdapter.ViewHolder> {
    private static final String TAG = "GridRecyclerView";
    private OnGridViewAdapterDeleteClickListener onGridViewAdapterDeleteClickListener;


    public void setDeleteClickListener(OnGridViewAdapterDeleteClickListener onGridViewAdapterDeleteClickListener) {
        this.onGridViewAdapterDeleteClickListener = onGridViewAdapterDeleteClickListener;
    }

    private List<Categories> categories = new ArrayList<>();
    private int currentCategoryId;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Categories currentCategory = categories.get(position);
        holder.categoryName.setText(currentCategory.getCategoryName());
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView categoryName;
        ImageView playBtn;
        ImageView deleteBtn;


        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            this.categoryName = itemView.findViewById(R.id.CategoryName);
            this.playBtn = itemView.findViewById(R.id.play);
            this.deleteBtn = itemView.findViewById(R.id.delete);


            deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onGridViewAdapterDeleteClickListener.onClickDeleteCategory(ViewHolder.this);
                }
            });

            categoryName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    currentCategoryId = categories.get(position).getCategoryId();
                    Intent intent = new Intent(view.getContext(), FoldersActivity.class);
                    intent.putExtra("Category Id", currentCategoryId);
                    Log.d(TAG, "onClick: category id" + currentCategoryId);
                    view.getContext().startActivity(intent);
                }
            });
        }
    }

    public void setCategories(List<Categories> categories){
        this.categories = categories;
        if (categories != null){
            notifyDataSetChanged();
        }
    }

}

