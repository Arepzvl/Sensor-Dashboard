package com.example.sensordashboard;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    private WebView dashboardWebView;
    private ProgressBar progressBar;
    private MaterialButton btnRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Menghubungkan ID komponen UI
        dashboardWebView = findViewById(R.id.dashboardWebView);
        progressBar = findViewById(R.id.progressBar);
        btnRefresh = findViewById(R.id.btnRefresh);

        // Tetapan Prestasi & Cache WebView
        WebSettings webSettings = dashboardWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);

        // URL Looker Studio Dashboard anda
        String dashboardUrl = "https://datastudio.google.com/embed/reporting/e43886aa-275d-4620-a4ec-a97b4da0cef2/page/BwXBG";

        dashboardWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                // Sembunyikan ProgressBar apabila halaman selesai dimuatkan
                progressBar.setVisibility(View.GONE);
            }
        });

        // Muatkan URL ke dalam WebView
        dashboardWebView.loadUrl(dashboardUrl);

        // Fungsi apabila butang Refresh ditekan
        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                dashboardWebView.reload();
            }
        });
    }
}