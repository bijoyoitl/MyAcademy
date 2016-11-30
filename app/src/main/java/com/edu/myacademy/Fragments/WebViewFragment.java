package com.edu.myacademy.Fragments;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.edu.myacademy.Utils.MyAcademySharePreference;
import com.edu.myacademy.R;
import com.edu.myacademy.Utils.BaseUrl;


public class WebViewFragment extends Fragment {
    WebView webView;
    ProgressBar progressBar;
    int feature_id;
    MyAcademySharePreference sharePreference;
    String response;
    int id = 0;

    public WebViewFragment() {
        // Required empty public constructor
    }


    @SuppressLint("ValidFragment")
    public WebViewFragment(int feature_id) {
        this.feature_id = feature_id;
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_web_view, container, false);

        sharePreference = new MyAcademySharePreference(getActivity());
        webView = (WebView) view.findViewById(R.id.webView);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);

        webView.clearCache(true);
        webView.setWebViewClient(new myWebClien());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setUseWideViewPort(false);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setBuiltInZoomControls(false);
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);
        webView.setScrollbarFadingEnabled(false);
        webView.loadUrl(BaseUrl.url + "?" + "feature=" + feature_id + "&user_id=" + sharePreference.getUserId());


        return view;
    }

    public class myWebClien extends WebViewClient {
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            progressBar.setVisibility(View.GONE);
        }

        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            progressBar.setVisibility(View.VISIBLE);
            super.onPageStarted(view, url, favicon);
            progressBar.setVisibility(View.VISIBLE);

        }

        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }


/*
    private class AllItemTask extends AsyncTask<Void, Void, String> {
        Context context;
        int feature_id;

        public AllItemTask(Context context, int feature_id) {
            this.context = context;
            this.feature_id = feature_id;
        }

        @Override
        protected String doInBackground(Void... voids) {

            response = new HTTPGetHelper().SendHttpRequest(BaseUrl.url + "?" + "feature=" + feature_id + "&user_id=" + sharePreference.getUserId());
            Log.e("feature id", " f id : " + feature_id);
            return response;

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            webView.loadUrl(response);
        }
    }
*/

}
