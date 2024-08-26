package com.example.eventx20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eventx20.Database.Callback.FindByModel;
import com.example.eventx20.Database.DataManager;
import com.example.eventx20.Database.DataModel.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private EditText editTextUserLogin;
    private EditText editTextPasswordLogin;
    private Button buttonLogin;
//    private TextView forgotPasswordTextView;
    TextView textViewLogin;
    private SharedPreferences sharedPreferences;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextUserLogin = findViewById(R.id.usernameEditText);
        editTextPasswordLogin = findViewById(R.id.passwordEditText);
        buttonLogin = findViewById(R.id.button);
        textViewLogin = findViewById(R.id.textViewLogin);

        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
//        if(sharedPreferences.contains("Username")&&sharedPreferences.contains("Password")){
//            Intent intent = new Intent(MainActivity.this, Requirement_collection.class);
//            startActivity(intent);
//        }
        String registeredEmail = sharedPreferences.getString("Username", "");
        String registeredPassword = sharedPreferences.getString("Password", "");
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextUserLogin.getText().toString();
                String password = editTextPasswordLogin.getText().toString();

                if (name.isEmpty() || password.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    // Retrieve user data from SharedPreferences
//                    SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
//                    String registeredName = sharedPreferences.getString("Username", "");
//                    String registeredPassword = sharedPreferences.getString("Password", "");

                    // Authentication logic
//                    if (name.equals(registeredName) && password.equals(registeredPassword)) {
                    DataManager.FindByName(name, password, new FindByModel() {
                        @Override
                        public void onSuccess(Object model) {
                            Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, Requirement_collection.class);
                            startActivity(intent);
                            finish();
                        }

                        @Override
                        public void onFailure() {
                            Toast.makeText(MainActivity.this, "Invalid name or password", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Register_Activity.class);
                startActivity(intent);
            }
   });



    }
}