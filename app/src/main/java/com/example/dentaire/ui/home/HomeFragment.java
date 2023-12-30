package com.example.dentaire.ui.home;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dentaire.R;
import com.example.dentaire.ui.ApiService;
import com.example.dentaire.ui.PW;
import com.example.dentaire.ui.dashboard.DashboardFragment;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private PWAdapter pwAdapter;
    private int connectedStudentId;

    public static HomeFragment newInstance(int studentId) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putInt("studentId", studentId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            connectedStudentId = getArguments().getInt("studentId", -1);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        // Initialize the adapter
        pwAdapter = new PWAdapter();

        // Set the adapter to the RecyclerView
        recyclerView.setAdapter(pwAdapter);
        pwAdapter.setImageClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle image click, e.g., navigate to DashboardFragment
                navigateToDashboardFragment();
            }
        });
        // Fetch PWs for the connected student
        fetchStudentPWs(connectedStudentId);

        return view;
    }

    private void fetchStudentPWs(int studentId) {
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        Call<List<PW>> pwCall = apiService.getStudentPWs(studentId);

        pwCall.enqueue(new Callback<List<PW>>() {
            @Override
            public void onResponse(Call<List<PW>> call, Response<List<PW>> response) {
                if (response.isSuccessful()) {
                    List<PW> studentPWs = response.body();

                    // Update the adapter data
                    pwAdapter.setPWList(studentPWs);
                } else {
                    // Handle error
                    Log.e("HomeFragment", "Failed to fetch student PWs. Response code: " + response.code());
                    // Log the response body for more details
                    try {
                        Log.e("HomeFragment", "Error response body: " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<PW>> call, Throwable t) {
                // Handle failure
                Log.e("HomeFragment", "Network error: " + t.getMessage());
                t.printStackTrace();  // Log the stack trace for more details
            }
        });
    }
    private void navigateToDashboardFragment() {
        // Assuming DashboardFragment is an Activity, you can start it like this
        Intent intent = new Intent(getActivity(), DashboardFragment.class);
        startActivity(intent);
    }
}
