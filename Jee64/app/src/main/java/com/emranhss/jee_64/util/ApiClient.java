package com.emranhss.jee_64.util;

import com.emranhss.jee_64.service.ApiService;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

   private static final String BASE_URL = "http://10.0.2.2:8085/"; // Emulator localhost
 //   private static final String BASE_URL = "http://192.168.88.250:8085/";

    private static Retrofit retrofit;

    public static ApiService getApiService() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(ApiService.class);
    }


}
