package com.psw.park.simpleurlschemeexample;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // webview를 가져온다.
        webview = (WebView)findViewById(R.id.webView);
        // webview client 객체를 넘긴다.
        webview.setWebViewClient(new WebClient()); 
        // 브라우저 세팅을 가져온다.
        WebSettings set = webview.getSettings();
        // 자바스크립트를 실행가능하게
        set.setJavaScriptEnabled(true);
        // 줌인아웃을 불가능하게
        set.setBuiltInZoomControls(false);
        webview.loadUrl("http://www.vintageappmaker.com");
    }

    // webview에서 필요한 클래스
    class WebClient extends WebViewClient {
        // URL을 요청했다면...
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        // 실행되었을 때, 누군가 Intent로 호출했다면
        // 그 Intent 객체로 getData()를 하여 파라메터를 가져온다. 
        Intent i = getIntent();
        if(i != null){
            Uri uri = i.getData();
            if (uri == null) return;

            // 파라메터를 읽어와서 보여준다.
            // ex) www.vintageappmaker.com?a=3&b=4이면
            // a=3&b=4 sMessage에 가져온다. 
            String sMessage = uri.getQueryParameter("message");
            Toast.makeText(getApplicationContext(), sMessage, Toast.LENGTH_LONG).show();
        }
    }
}
