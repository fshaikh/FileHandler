package com.reversecurrent.filehandler;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by fshaikh on 8/30/13.
 */
public class WebViewFragment extends Fragment {
    private WebView _webView = null;
    private IMediator _mediator = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_webview,container,false);
        _webView = (WebView)view.findViewById(R.id._webView);
        WebSettings webSettings = _webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
       // _webView.setWebViewClient(new FileViewerWebView(getActivity().getApplicationContext()));
        _webView.setWebViewClient(new WebViewClient());
        _webView.setWebChromeClient(new WebChromeClient());
        _webView.addJavascriptInterface(new WebAppInterface(getActivity().getApplicationContext(),this),"Android");
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        _mediator = (IMediator)activity;
    }

    public String GetFileContent()
    {
        return _mediator.GetFileContent().FileContent;
    }

    @Override
    public void onStart() {
        super.onStart();
        // read the file from assets folder.
        AssetManager assetManager = getActivity().getAssets();
        InputStream stream = null;
        if(assetManager != null)
        {
            try
            {
                stream = assetManager.open("codemirror.html");
                if(stream != null)
                {
                    byte contentBytes[] = new byte[stream.available()];
                    stream.read(contentBytes);
                    String str = new String(contentBytes);
                    //_webView.loadData(str,"text/html; charset=UTF-8", null);
                    _webView.loadDataWithBaseURL("file:///android_asset/",str,"text/html","UTF-8",null);

                }
            }
            catch(IOException exObj)
            {

            }
        }
    }
}
