package blog.juno.daumapi_localhtml;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

public class WebViewActivity extends AppCompatActivity {

    private WebView browser;
    private Handler handler;

    private class AndroidBridge {
        @JavascriptInterface
        public void returnData(final String arg1, final String arg2, final String arg3) {
            handler.post(new Runnable() {
                @Override
                public void run() {

                    Intent intent = new Intent();
                    intent.putExtra("fir",arg1);
                    intent.putExtra("sec",arg2);
                    if(!arg3.equals("")|| !(arg3.length()==0)){
                        intent.putExtra("thr","/"+arg3);
                    }else{
                        intent.putExtra("thr","/");
                    }
                    Log.e("test",arg1 + arg2 + arg3);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        browser = (WebView) findViewById(R.id.webView);
        browser.getSettings().setJavaScriptEnabled(true);
        browser.addJavascriptInterface(new AndroidBridge(), "Android");

        browser.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {

                browser.loadUrl("javascript:sample2_execDaumPostcode();");
            }
        });

/*        String htmlFilename = "daum.html";
        AssetManager mgr = getBaseContext().getAssets();
        try {
            InputStream in = mgr.open(htmlFilename, AssetManager.ACCESS_BUFFER);
            String htmlContentInStringFormat = StreamToString(in);
            in.close();
            browser.loadDataWithBaseURL(null, htmlContentInStringFormat, "text/html", "utf-8", null);

        } catch (IOException e) {
            e.printStackTrace();
        }*/
        //browser.loadUrl("file:///android_asset/test.html");
        //browser.loadUrl("http://www.daddyface.com/public/daum.html");
        //browser.loadUrl("http://cdn.rawgit.com/jolly73-df/DaumPostcodeExample/master/DaumPostcodeExample/app/src/main/assets/daum.html");
        //browser.loadUrl("http://cdn.rawgit.com/plk3314/DaumApi_LocalHtml/e838d0be/app/src/main/assets/daum.html");
        //browser.loadUrl("https://cdn.rawgit.com/plk3314/DaumApi_LocalHtml/51364c79/app/src/main/assets/daum.html");
        browser.loadUrl("http://rawgit.com/plk3314/DaumApi_LocalHtml/master/app/src/main/assets/test.html");

    }

}
