package com.example.dentaire.ui.home;

import com.example.dentaire.ui.PW;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface StudentApi {
    @GET("student/PWS/{studentId}")
    Call<List<PW>> getStudentPWs(@Path("studentId") int studentId);}
