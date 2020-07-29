package com.example.indiannew;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.fragment.app.DialogFragment;

/* renamed from: com.example.hp.indiannews.NewsFragment */
public class NewsFragment extends DialogFragment {
    MainActivity activity = new MainActivity();
    Context ctx;
    private Dialog mDialog;
    private WebView webview;

    /* renamed from: com.example.hp.indiannews.NewsFragment$InsideWebViewClient */
    private class InsideWebViewClient extends WebViewClient {
        private InsideWebViewClient() {
        }

        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    public NewsFragment(Context ctx2) {
        this.ctx = ctx2;
    }

    @SuppressLint("SetJavaScriptEnabled")
    public Dialog onCreateDialog(Bundle onSavedInstance) {
        if (this.mDialog == null) {
            this.mDialog = new Dialog(getActivity(), R.style.cart_dialog);
            this.mDialog.setContentView(R.layout.layout_cart_dialog);
            this.mDialog.setCanceledOnTouchOutside(true);
            this.mDialog.getWindow().setGravity(17);
            View view = this.mDialog.getWindow().getDecorView();
            String str = "url";
            String string = getArguments().getString(str);
            this.webview = (WebView) view.findViewById(R.id.web1);
            this.webview.getSettings().setJavaScriptEnabled(true);
            this.webview.getSettings().setBuiltInZoomControls(true);
            this.webview.getSettings().setLoadWithOverviewMode(true);
            this.webview.getSettings().setUseWideViewPort(true);
            this.webview.loadUrl(getArguments().getString(str));
            this.webview.setWebChromeClient(new WebChromeClient() {
                public void onProgressChanged(WebView view, int progress) {
                    ((MainActivity) NewsFragment.this.ctx).setProgress(progress * 100);
                }
            });
            this.webview.setWebViewClient(new InsideWebViewClient());
        }
        return this.mDialog;
    }
}
