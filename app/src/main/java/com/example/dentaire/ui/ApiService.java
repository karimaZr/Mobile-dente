package com.example.dentaire.ui;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
    @GET("student")
    Call<List<Student>> getStudents();
    @GET("student/PWS/{studentId}")
    Call<List<PW>> getStudentPWs(@Path("studentId") int studentId);
    @POST("studentpw/add")
    Call<Void> addStudentPW(@Body StudentPW studentPWRequest);
    @GET("student/{id}")
    Call<Student> getStudentById(@Path("id") int studentId);
}
