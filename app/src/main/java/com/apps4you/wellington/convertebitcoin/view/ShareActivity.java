package com.apps4you.wellington.convertebitcoin.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.apps4you.wellington.convertebitcoin.R;
import com.facebook.FacebookSdk;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

public class ShareActivity extends AppCompatActivity {

    ShareDialog shareDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        facebookSDKInitialize();
        shareDialog = new ShareDialog(this);
        setContentView(R.layout.activity_share);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    protected void facebookSDKInitialize(){
        FacebookSdk.sdkInitialize(getApplicationContext());
    }

    public void shareOnFacebook(View view){
        ShareLinkContent content = new ShareLinkContent.Builder()
                .setContentUrl(Uri.parse("https://play.google.com/store/apps/details?id=com.xulipasoftworks.xulipaxulepa.convertebitcoin&hl=pt_BR"))
                .build();
        shareDialog.show(content);
    }

    public void shareOnTwitter(View view){
        String tweetUrl = "https://twitter.com/intent/tweet?text="+getString(R.string.Tweet)+" &url="
                + "https://play.google.com/store/apps/details?id=com.xulipasoftworks.xulipaxulepa.convertebitcoin&hl=pt_BR";
        Uri uri = Uri.parse(tweetUrl);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }

}
