package com.emranhss.jee_64;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Subject extends AppCompatActivity {

    private WebView webSubject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_subject);


        webSubject = findViewById(R.id.webSubject);


        WebSettings webSettings= webSubject.getSettings();
        webSubject.setWebViewClient(new SameView());
        webSettings.setJavaScriptEnabled(true);
        webSubject.loadUrl("https://isdb-bisew.org/it-scholarship-programme/it-scholarship-courses");

//        WebSettings webSettings = webView.getSettings();
//        webView.setWebViewClient(new SameView());
//        webSettings.setJavaScriptEnabled(true);
//        webView.loadUrl("https://www.youtube.com/");





    }

    public  class SameView extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

    }


}