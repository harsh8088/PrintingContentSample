package com.hrawat.printingcontentsample;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.print.PrintDocumentAdapter;
import android.print.PrintManager;
import android.support.annotation.RequiresApi;
import android.support.v4.print.PrintHelper;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

public class HtmlActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_html);
        init();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void init() {
        webView = findViewById(R.id.web_view);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl("https://developer.android.com/guide/webapps/webview.html");
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void printHtml(String fileName, WebView webView) {
        if (PrintHelper.systemSupportsPrint()) {
            PrintDocumentAdapter adapter = webView.createPrintDocumentAdapter("Document");
            PrintManager printManager = (PrintManager) getSystemService(Context.PRINT_SERVICE);
            printManager.print(fileName, adapter, null);
        } else {
            Toast.makeText(this, "unsupported", Toast.LENGTH_SHORT)
                    .show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.menu_html, menu);
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_print:
                printHtml("printJobName", webView);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
