package com.example.smartcards.UI;
import android.os.Bundle;
import com.example.smartcards.Adapters.FolderRecyclerAdapter;
import com.example.smartcards.Adapters.Listeners.OnFolderDeleteListener;
import com.example.smartcards.Dialogs.AddDialog;
import com.example.smartcards.Dialogs.DeleteDialog;
import com.example.smartcards.Models.Folders;
import com.example.smartcards.ViewModels.FoldersViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import com.example.smartcards.R;
import java.util.List;


public class FoldersActivity extends AppCompatActivity  implements AddDialog.OnInputListener, DeleteDialog.OnDeleteInputListener {

    private static final String TAG = "FoldersActivity";

    private FoldersViewModel mFoldersViewModel;
    FolderRecyclerAdapter folderAdapter = new FolderRecyclerAdapter();
    AddDialog addDialog = new AddDialog();
    ItemTouchHelper.Callback itemTouchHelperCallback;
    //private MediatorLiveData
    @Override
    public void sendInput(String inputCategoryName) {
        Bundle idFromCategoryAdapter = getIntent().getExtras();
        int categoryId = idFromCategoryAdapter.getInt("Category Id");
            // both Name and Id are ok here
        mFoldersViewModel.onTakeNewFolder(inputCategoryName, categoryId);
    }

    @Override
    public void sendDeleteInput(boolean deleteBtnIsPressed, RecyclerView.ViewHolder viewHolder) {
        Bundle idFromCategoryAdapter = getIntent().getExtras();
        int categoryId = idFromCategoryAdapter.getInt("Category Id");
        if (deleteBtnIsPressed = true){

            //Folders selectedFolder = (Folders) mFoldersViewModel.getAllFoldersFromCategory(categoryId).getValue();
           // Folders selectedFolder = mFoldersViewModel.getAllFolders().getValue().get(viewHolder.getAdapterPosition());
            Folders selectedFolder = mFoldersViewModel.getAllFoldersFromCategory(categoryId).getValue().get(viewHolder.getAdapterPosition());
            mFoldersViewModel.deleteFolder(selectedFolder);
            Log.d(TAG, "sendDeleteInput: Delete Btn Pressed? " + deleteBtnIsPressed + " adapt position " + viewHolder);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folders);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle idFromCategoryAdapter = getIntent().getExtras();
        final int categoryId = idFromCategoryAdapter.getInt("Category Id");
        final RecyclerView recyclerView = findViewById(R.id.folderRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(folderAdapter.itemTouchHelperCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
//          categoryId is ok here

        mFoldersViewModel = ViewModelProviders.of(this).get(FoldersViewModel.class);
        mFoldersViewModel.getAllFoldersFromCategory(categoryId).observe(this, new Observer<List<Folders>>() {

            @Override
            public void onChanged(List<Folders> folders) {
                //categoryId is OK
                recyclerView.setAdapter(folderAdapter);
                folderAdapter.setFolders(folders);
                folderAdapter.setOnFolderDeleteListener(new OnFolderDeleteListener(getSupportFragmentManager()));
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Add Dialog Clicled");


                Boolean openedViaCategory = false;
                Bundle args = new Bundle();
                args.putBoolean("fromMainActivity",openedViaCategory);
                addDialog.setArguments(args);
                addDialog.show(getSupportFragmentManager(), "AddCategory_tag");

                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }

        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}
