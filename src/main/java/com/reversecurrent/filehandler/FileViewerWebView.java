package com.reversecurrent.filehandler;

import android.content.Context;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

/**
 * Created by fshaikh on 8/30/13.
 */
public class FileViewerWebView extends WebViewClient {
    private Context _context = null;
    public FileViewerWebView(Context context)
    {
        _context = context;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        return false;
    }

    public void onReceivedError(WebView view, int errorCod,String description, String failingUrl) {
        Toast.makeText(_context,  description, Toast.LENGTH_LONG).show();
    }
}
