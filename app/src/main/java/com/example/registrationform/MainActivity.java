package com.example.registrationform;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.MediaParser;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btn;
    private Button displayBtn;
    private Button updateBtn;
    private EditText edt1, edt2, edt3, edt4, edt5, edt6;
    private boolean b = false;
    databaseHandler handler = new databaseHandler(this);
    boolean inserted = false;
    boolean answer = false;


//    private void updateUserData() {
//        String name = userDatabase.getUsername();
//        String fName = userDatabase.getFullName();
//        String phoneN = userDatabase.getPhoneNumber();
//        String pass = userDatabase.getPassword();
//        String e_mail = userDatabase.getEmail();
//        String cf = userDatabase.getConfirmPassword();
//
//        Log.d("Name : " ,name);
//
//        updateBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                handler.updateData(ud);
//            }
//        });
//
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.submit_btn);
        displayBtn = findViewById(R.id.display_Btn);
        updateBtn = findViewById(R.id.update_Btn);
        edt1 = findViewById(R.id.edit);
        edt2 = findViewById(R.id.edit2);
        edt3 = findViewById(R.id.edit3);
        edt4 = findViewById(R.id.edit4);
        edt5 = findViewById(R.id.edit5);
        edt6 = findViewById(R.id.edit6);
//        boolean b = true;
//        Intent i = getIntent();
//        b = i.getBooleanExtra("b", b);

        btn.setVisibility(View.VISIBLE);

        btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(validation()){
                        String username = edt1.getText().toString();
                        String fullName= edt2.getText().toString();
                        String phoneNumber = edt3.getText().toString();
                        String emailId = edt4.getText().toString();
                        String password = edt5.getText().toString();
                        String confirmPassword = edt6.getText().toString();

                        UserDatabase userDatabase = new UserDatabase();
                        int id = userDatabase.getId();

                        inserted = insertData(id, username, fullName, phoneNumber, emailId, password, confirmPassword);
                        List<UserDatabase> userDatabaseList = handler.getAllUser();
                        for ( UserDatabase ud  : userDatabaseList){
                            String log = "ID:" + ud.getId() + ",Username: " + ud.getUsername() +
                                    ",FullName: " + ud.getFullName() + ",Phone_Number: " + ud.getPhoneNumber() + ",Email: " + ud.getEmail() + ",Password: " + ud.getPassword() + ",Confirm_Passowrd: " + ud.getConfirmPassword();
                            Log.d("Name: ", log);
                        }
                        edt1.setText("");
                        edt2.setText("");
                        edt3.setText("");
                        edt4.setText("");
                        edt5.setText("");
                        edt6.setText("");
                    }
                }
        });

        displayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                displayData();
            }
        });

        Intent intent = getIntent();
        UserDatabase database;
        if (intent != null && intent.hasExtra("object")) {

            btn.setVisibility(View.GONE);
            database = (UserDatabase) intent.getSerializableExtra("object");

            edt1.setText(database.getUsername());
            edt2.setText(database.getFullName());
            edt3.setText(database.getPhoneNumber());
            edt4.setText(database.getEmail());
            edt5.setText(database.getPassword());
            edt6.setText(database.getConfirmPassword());

            updateBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(validation()){
                        String name = edt1.getText().toString();
                        String fName = edt2.getText().toString();
                        String phoneN = edt3.getText().toString();
                        String email_Id = edt4.getText().toString();
                        String pass = edt5.getText().toString();
                        String cf = edt6.getText().toString();
                        database.setUsername(name);
                        database.setFullName(fName);
                        database.setPhoneNumber(phoneN);
                        database.setEmail(email_Id);
                        database.setPassword(pass);
                        database.setConfirmPassword(cf);
                        handler.updateData(database);
                        List<UserDatabase> userDatabaseList = handler.getAllUser();
                        for ( UserDatabase ud  : userDatabaseList){
                            String log = "ID:" + ud.getId() + ",Username: " + ud.getUsername() +
                                    ",FullName: " + ud.getFullName() + ",Phone_Number: " + ud.getPhoneNumber() + ",Email: " + ud.getEmail() + ",Password: " + ud.getPassword() + ",Confirm_Passowrd: " + ud.getConfirmPassword();
                            Log.d("Name: ", log);
                        }

                        edt1.setText("");
                        edt2.setText("");
                        edt3.setText("");
                        edt4.setText("");
                        edt5.setText("");
                        edt6.setText("");
                        finish();
                        Intent intent1 = new Intent(MainActivity.this, DetailsActivity.class);
                        startActivity(intent1);

                    }

                }
            });


//            Log.e("TAG", "onCreate: database : " + database.getFullName());
        }


    }
    private boolean emptyField(String data) {
        if (data.matches("")) {
            return true;
        }
        else {
            return false;
        }
    }

    private boolean validation() {
        String username = edt1.getText().toString();
        String fullName = edt2.getText().toString();
        String phoneNumber = edt3.getText().toString();
        String emailId = edt4.getText().toString().trim();
        String password = edt5.getText().toString();
        String confirmPassword = edt6.getText().toString();
        int size = edt3.getText().toString().length();

        boolean result = emptyField(username);

        if(result) {
//            Toast.makeText(getApplicationContext(), "Username is not entered", Toast.LENGTH_SHORT).show();
            edt1.setError("This field is required");
        }
        else if(emptyField(fullName)) {
            edt1.setText(username);
//            Toast.makeText(getApplicationContext(), "Full Name is not entered", Toast.LENGTH_SHORT).show();
            edt2.setError("This field is required");
        }
        else if(emptyField(phoneNumber)){
            edt2.setText(fullName);
//            Toast.makeText(getApplicationContext(), "Phone Number is not entered", Toast.LENGTH_SHORT).show();
            edt3.setError("This field is required");
        }
        else if(size < 10) {
//            Toast.makeText(getApplicationContext(), "Please enter 10 digit number", Toast.LENGTH_SHORT).show();
            edt3.setError("Please Enter 10 digit number");
        }
        else if(emptyField(emailId)) {
//            Toast.makeText(getApplicationContext(), "Email is not entered", Toast.LENGTH_SHORT).show();
            edt4.setError("This field is required");
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(emailId).matches()){
//            Toast.makeText(getApplicationContext(), "Invalid Email Id", Toast.LENGTH_SHORT).show();
            edt4.setError("Invalid Email Id");
        }
        else if(emptyField(password)) {
            edt3.setText(phoneNumber);
            edt4.setText(emailId);
//            Toast.makeText(getApplicationContext(), "Password is not entered", Toast.LENGTH_SHORT).show();
            edt5.setError("This field is required");
        }
        else if(emptyField(confirmPassword)){
            edt5.setText(password);
//            Toast.makeText(getApplicationContext(), "Confirm Password is not entered", Toast.LENGTH_SHORT).show();
            edt6.setError("This field is required");
        }
        else {
            if (!password.equals(confirmPassword)) {
//                Toast.makeText(getApplicationContext(), "Confirm Password does not match", Toast.LENGTH_SHORT).show();
                edt6.setError("Does not match with password");
            }
            else {
                edt6.setText(confirmPassword);
                answer = true;
            }
        }
        return answer;
    }

    private void displayData() {
        Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
        startActivity(intent);
    }
    private boolean insertData(int Id, String username, String fullName, String phoneNumber, String emailId, String password, String confirmPassword) {
        // Inserting Data
        handler.addUser(new UserDatabase(Id, username, fullName, phoneNumber, emailId, password, confirmPassword));
        List<UserDatabase> userData = handler.getAllUser();
//
//        for (UserDatabase ud : userData){
//            String log = "ID:" + ud.getId() + ",Username: " + ud.getUsername() +
//                    ",FullName: " + ud.getFullName() + ",Phone_Number: " + ud.getPhoneNumber() + ",Email: " + ud.getEmail() + ",Password: " + ud.getPassword() + ",Confirm_Passowrd: " + ud.getConfirmPassword();
//            Log.d("Name: ", log);
//        }

        return true;
    }

}