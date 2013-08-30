package com.reversecurrent.filehandler;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

/**
 * Created by fshaikh on 8/30/13.
 */
public class WebAppInterface {
    Context mContext;
    WebViewFragment _fragment;

    /** Instantiate the interface and set the context */
    WebAppInterface(Context c,WebViewFragment fragment) {
        mContext = c;
        _fragment = fragment;
    }

    /** Show a toast from the web page */
    @JavascriptInterface
    public void showToast(String toast) {
        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
    }

    @JavascriptInterface
    public String getFileContent() {
        return _fragment.GetFileContent();
    }
}
