package com.example.registrationform;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.io.Serializable;
import java.util.List;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;


public class DetailsActivity extends Activity {

    RecyclerView recyclerView;
    databaseHandler handler = new databaseHandler(this);
    List<UserDatabase> userData;
    boolean active = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_data);

        recyclerView = findViewById(R.id.data);
        userData = handler.getAllUser();


        Log.e("TAG", "onCreate: " + userData);
//        ViewData vd = new ViewData();
        for (UserDatabase ud : userData){
            String log = "ID:" + ud.getId() + ",Username: " + ud.getUsername() +
                    ",FullName: " + ud.getFullName() + ",Phone_Number: " + ud.getPhoneNumber() + ",Email: " + ud.getEmail() + ",Password: " + ud.getPassword() + ",Confirm_Passowrd: " + ud.getConfirmPassword();
            Log.d("Name: ", log);
        }


        ViewData viewData = new ViewData(this, userData);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(viewData);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                UserDatabase deletedCourse = userData.get(viewHolder.getAbsoluteAdapterPosition());
                int position = viewHolder.getAbsoluteAdapterPosition();
                userData.remove(viewHolder.getAbsoluteAdapterPosition());
                viewData.notifyItemRemoved(viewHolder.getAbsoluteAdapterPosition());

                Log.e("TAG", "onSwiped: " + deletedCourse);

//                Snackbar.make(recyclerView, deletedCourse.getUsername(), Snackbar.LENGTH_LONG).setAction("Undo", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        userData.add(position,deletedCourse);
//                        viewData.notifyItemInserted(position);
//                    }
//                }).show();

                for (UserDatabase ud : userData){
                    String log = "ID:" + ud.getId() + ",Username: " + ud.getUsername() +
                    ",FullName: " + ud.getFullName() + ",Phone_Number: " + ud.getPhoneNumber() + ",Email: " + ud.getEmail() + ",Password: " + ud.getPassword() + ",Confirm_Passowrd: " + ud.getConfirmPassword();
                    Log.d("Name: ", log);
                }
                handler.deleteUser(deletedCourse);
            }
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

                new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                        .addSwipeLeftBackgroundColor(ContextCompat.getColor(DetailsActivity.this, R.color.red))
                        .addSwipeLeftActionIcon(R.drawable.ic_baseline_delete_24)
                        .create()
                        .decorate();

                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        }).attachToRecyclerView(recyclerView);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                UserDatabase updateCourse = userData.get(viewHolder.getAbsoluteAdapterPosition());

                Log.e("TAG", "onMove: " + userData.get(viewHolder.getAbsoluteAdapterPosition()).getFullName());

                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                UserDatabase updateData = userData.get(viewHolder.getAbsoluteAdapterPosition());

                Intent intent = new Intent(DetailsActivity.this, MainActivity.class);
                intent.putExtra("object", (Serializable) updateData);
                intent.putExtra("b", false);
                startActivity(intent);
                viewData.notifyDataSetChanged();
                finish();

//                int position = viewHolder.getAbsoluteAdapterPosition();
//                Snackbar.make(recyclerView, updateData.getUsername(), Snackbar.LENGTH_LONG).setAction("Want to Edit", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
////                        userData.add(position, updateData);
////                        viewData.notifyItemInserted(position);
//
////                        active =  false;
//                    }
//                }).show();
            }

            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

                new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                        .addSwipeRightBackgroundColor(ContextCompat.getColor(DetailsActivity.this, R.color.green))
                        .addSwipeRightActionIcon(R.drawable.ic_baseline_edit_24)
                        .create()
                        .decorate();

                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
                isCurrentlyActive = active;
            }

        }).attachToRecyclerView(recyclerView);


    }
}
