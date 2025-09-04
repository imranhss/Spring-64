package com.emranhss.jee_64.service;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiService {


    @Multipart
    @POST("/api/jobseeker/")
    Call<Map<String, String>> registerJobSeeker(
            @Part("user") RequestBody user,
            @Part("jobSeeker") RequestBody jobSeeker,
            @Part MultipartBody.Part photo
    );

}
