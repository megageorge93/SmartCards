package com.example.smartcards;

import android.content.Intent;
import android.os.Bundle;

import com.example.smartcards.Adapters.GridViewAdapter;
import com.example.smartcards.Adapters.Listeners.OnGridViewAdapterDeleteClickListener;
import com.example.smartcards.Dialogs.AddDialog;
import com.example.smartcards.Dialogs.DeleteDialog;
import com.example.smartcards.Models.Categories;
import com.example.smartcards.UI.FoldersActivity;
import com.example.smartcards.ViewModels.CategoriesViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AddDialog.OnInputListener, DeleteDialog.OnDeleteInputListener {
    private static final String TAG = "MainActivity";

    private static final int NUM_COLUMNS = 2;

    private CategoriesViewModel mCategoriesViewModel;
    GridViewAdapter gridViewAdapter = new GridViewAdapter();
    AddDialog addDialog = new AddDialog();

    @Override
    public void sendInput(String inputCategoryName) {
        mCategoriesViewModel.onTakeNewCategory(inputCategoryName);
        Log.d(TAG, "Main Activity SendInput: " + inputCategoryName);
    }

    @Override
    public void sendDeleteInput(boolean deleteBtnIsPressed, RecyclerView.ViewHolder viewHolder) {
        if (deleteBtnIsPressed = true){
            Categories selectedCategory = mCategoriesViewModel.getAllCategories().getValue().get(viewHolder.getAdapterPosition());
            mCategoriesViewModel.deleteCategory(selectedCategory);
            Log.d(TAG, "sendDeleteInput: Delete Btn Pressed? " + deleteBtnIsPressed + " adapt position " + viewHolder);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final RecyclerView recyclerView = findViewById(R.id.gridRecyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, NUM_COLUMNS);
        recyclerView.setLayoutManager(gridLayoutManager);


        mCategoriesViewModel = ViewModelProviders.of(this).get(CategoriesViewModel.class);
        mCategoriesViewModel.getAllCategories().observe(this, new Observer<List<Categories>>() {
            @Override
            public void onChanged(List<Categories> categories) {

                Log.d(TAG, "onCreate: Adapter Set "+ gridViewAdapter);

                recyclerView.setAdapter(gridViewAdapter);
                gridViewAdapter.setCategories(categories);
                gridViewAdapter.setDeleteClickListener(new OnGridViewAdapterDeleteClickListener(getSupportFragmentManager()));
            }
    });

        FloatingActionButton fab = findViewById(R.id.fabCategory);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicled");

                Boolean openedViaCategory = true;
                Bundle args = new Bundle();
                args.putBoolean("fromMainActivity",openedViaCategory);
                addDialog.setArguments(args);
                addDialog.show(getSupportFragmentManager(), "AddCategory_tag");

                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.import_excel) {
            return true;
        }
        if (id == R.id.export_excel) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
