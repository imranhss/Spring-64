package com.emranhss.jee_64;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;


import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;

import androidx.appcompat.app.AppCompatActivity;


import com.emranhss.jee_64.entity.JobSeeker;
import com.emranhss.jee_64.entity.User;
import com.emranhss.jee_64.service.ApiService;
import com.emranhss.jee_64.util.ApiClient;
import com.emranhss.jee_64.util.FileUtils;

import com.google.gson.Gson;

import java.io.File;
import java.util.Calendar;
import java.util.Map;


import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Registration extends AppCompatActivity {


    private static final int PICK_IMAGE_REQUEST = 1;

    EditText etName, etEmail, etPhone, etPassword, etAddress, etDOB;
    RadioGroup rgGender;
    RadioButton rbMale, rbFemale;
    Button btnSelectPhoto, btnRegister;
    Uri selectedImageUri;
    File selectedImageFile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registration);


        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        etPassword = findViewById(R.id.etPassword);
        etAddress = findViewById(R.id.etAddress);
        etDOB = findViewById(R.id.etDOB);
        rgGender = findViewById(R.id.rgGender);
        rbMale = findViewById(R.id.rbMale);
        rbFemale = findViewById(R.id.rbFemale);

        btnSelectPhoto = findViewById(R.id.btnSelectPhoto);
        btnRegister = findViewById(R.id.btnRegister);

        btnSelectPhoto.setOnClickListener(v -> openFileChooser());
        btnRegister.setOnClickListener(v -> registerUser());


        etDOB.setOnClickListener(v -> {
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    Registration.this,
                    (view, selectedYear, selectedMonth, selectedDay) -> {
                        // Pad month and day to 2 digits
                        String dob = String.format("%04d-%02d-%02d", selectedYear, selectedMonth + 1, selectedDay);
                        etDOB.setText(dob);
                    },
                    year, month, day
            );

            datePickerDialog.show();
        });


    }


    private void openFileChooser() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            selectedImageUri = data.getData(); // ✅ This is safe
            selectedImageFile = FileUtils.getFileFromUri(this, selectedImageUri); // ✅ Copies file into cache
            Toast.makeText(this, "Photo Selected: " + selectedImageFile.getAbsolutePath(), Toast.LENGTH_SHORT).show();
        }
    }


    private void registerUser() {
        String name = etName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String address = etAddress.getText().toString().trim();
        String dob = etDOB.getText().toString().trim();
        String gender = rbMale.isChecked() ? "Male" : "Female";

        // Create User and JobSeeker objects
        User user = new User(name, email, password);
        JobSeeker jobSeeker = new JobSeeker(phone, address, dob, gender);

        Gson gson = new Gson();

        RequestBody userBody = RequestBody.create(gson.toJson(user), MediaType.parse("application/json"));
        RequestBody jobSeekerBody = RequestBody.create(gson.toJson(jobSeeker), MediaType.parse("application/json"));

        MultipartBody.Part photoPart = null;
        if (selectedImageFile != null) {
            RequestBody photoReq = RequestBody.create(selectedImageFile, MediaType.parse("image/*"));
            photoPart = MultipartBody.Part.createFormData("photo", selectedImageFile.getName(), photoReq);
        }

        ApiService apiService = ApiClient.getApiService();

        Call<Map<String, String>> call = apiService.registerJobSeeker(userBody, jobSeekerBody, photoPart);

        call.enqueue(new Callback<Map<String, String>>() {
            @Override
            public void onResponse(Call<Map<String, String>> call, Response<Map<String, String>> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), response.body().get("Message"), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Registration failed", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<Map<String, String>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();

                System.out.println(t.getMessage());
            }
        });


    }




}