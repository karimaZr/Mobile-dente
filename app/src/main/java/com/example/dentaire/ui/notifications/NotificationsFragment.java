package com.example.dentaire.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.dentaire.R;
import com.example.dentaire.ui.ApiService;
import com.example.dentaire.ui.LoginActivity;
import com.example.dentaire.ui.Student;
import com.example.dentaire.ui.home.RetrofitClient;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationsFragment extends Fragment {

    private ImageView profileImageView;
    private EditText usernameEditText, firstNameEditText, lastNameEditText, passwordEditText;
    private Button updateProfileButton, logoutButton;
    private ProgressBar profileProgressBar;

    private ApiService apiService;
    private int connectedStudentId;  // Assuming this is set with the connected student's ID

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        // Initialize UI elements
        profileImageView = root.findViewById(R.id.profile_image_view);
        usernameEditText = root.findViewById(R.id.profile_username);
        firstNameEditText = root.findViewById(R.id.profile_firstName);
        lastNameEditText = root.findViewById(R.id.profile_LastName);
        passwordEditText = root.findViewById(R.id.profile_password);
        updateProfileButton = root.findViewById(R.id.profle_update_btn);
        logoutButton = root.findViewById(R.id.profle_logout_btn);
        profileProgressBar = root.findViewById(R.id.profile_progress_bar);

        // Initialize Retrofit
        apiService = RetrofitClient.getClient().create(ApiService.class);

        // Fetch student information by ID
        fetchStudentById(2);

        // Set OnClickListener for the logout button
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle logout action, for example, navigate to LoginActivity
                navigateToLoginActivity();
            }
        });

        return root;
    }

    private void fetchStudentById(int studentId) {
        Call<Student> call = apiService.getStudentById(studentId);

        call.enqueue(new Callback<Student>() {
            @Override
            public void onResponse(Call<Student> call, Response<Student> response) {
                if (response.isSuccessful()) {
                    // Student information received successfully
                    Student student = response.body();

                    // Update UI with student information
                    updateUIWithStudentInfo(student);
                } else {
                    // Handle error
                    Log.e("NotificationsFragment", "Failed to fetch student by ID. Response code: " + response.code());
                    // Log the response body for more details
                    try {
                        Log.e("NotificationsFragment", "Error response body: " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Student> call, Throwable t) {
                // Handle failure
                Log.e("NotificationsFragment", "Network error: " + t.getMessage());
                t.printStackTrace();  // Log the stack trace for more details
            }
        });
    }

    private void updateUIWithStudentInfo(Student student) {
        // Update UI elements with student information
        // For example, set text in EditText fields or load an image into ImageView
        usernameEditText.setText(student.getUserName());
        firstNameEditText.setText(student.getFirstName());
        lastNameEditText.setText(student.getLastName());
        passwordEditText.setText(student.getPassword());

        // Handle loading and displaying the profile image if applicable
        // Example: Glide or Picasso library can be used for image loading
        // Glide.with(this).load(student.getProfileImageUrl()).into(profileImageView);

        // Hide the progress bar
        profileProgressBar.setVisibility(View.GONE);
    }

    private void navigateToLoginActivity() {
        // Handle logout action, for example, navigate to LoginActivity
        // You can use Intent to navigate to LoginActivity
        Intent intent = new Intent(requireContext(), LoginActivity.class);
        startActivity(intent);
        // Finish the current activity to prevent going back to it using the back button
        requireActivity().finish();
    }
}
