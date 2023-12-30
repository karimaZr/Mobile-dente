package com.example.dentaire.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dentaire.MainActivity;
import com.example.dentaire.R;
import com.google.android.material.button.MaterialButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private List<Student> allStudents;
    private boolean apiRequestCompleted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText usernameEditText = findViewById(R.id.username);
        EditText passwordEditText = findViewById(R.id.password);
        MaterialButton loginButton = findViewById(R.id.loginbtn);
        // Set up Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.198.139:8083/")  // Replace with your base URL
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create the API service
        ApiService apiService = retrofit.create(ApiService.class);

        // Fetch students from the server
        Call<List<Student>> call = apiService.getStudents();
        call.enqueue(new Callback<List<Student>>() {
            @Override
            public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
                if (response.isSuccessful()) {
                    allStudents = response.body();

                    // Log the retrieved data
                    for (Student student : allStudents) {
                        Log.d("StudentData", "Username: " + student.getUserName() + ", Password: " + student.getPassword());
                    }

                    apiRequestCompleted = true;
                    loginButton.setEnabled(true);
                } else {
                    Toast.makeText(LoginActivity.this, "Failed to fetch students", Toast.LENGTH_SHORT).show();
                    Log.e("LoginActivity", "Failed to fetch students. Response code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Student>> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("LoginActivity", "Network error: " + t.getMessage());
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredUsername = usernameEditText.getText().toString();
                String enteredPassword = passwordEditText.getText().toString();

                if (apiRequestCompleted) {
                    // Find the connected student
                    Student connectedStudent = findConnectedStudent(enteredUsername, enteredPassword);

                    if (connectedStudent != null) {
                        // Pass the student ID to MainActivity
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("studentId", connectedStudent.getId());
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "LOGIN FAILED !!!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Please wait while fetching data from the server", Toast.LENGTH_SHORT).show();
                }
            }
        });

// Add this method to find the connected student

    }

    private boolean checkCredentials(String username, String password) {
        for (Student student : allStudents) {
            if (student.getUserName().equals(username) && student.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
    private Student findConnectedStudent(String username, String password) {
        for (Student student : allStudents) {
            if (student.getUserName().equals(username) && student.getPassword().equals(password)) {
                return student;
            }
        }
        return null;
    }
}