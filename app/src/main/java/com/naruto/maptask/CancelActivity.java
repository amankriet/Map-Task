package com.naruto.maptask;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;

import java.util.Objects;

public class CancelActivity extends AppCompatActivity {

    @Override
    public boolean onNavigateUpFromChild(Activity child) {
        finish();
        return super.onNavigateUpFromChild(child);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel);

        overridePendingTransition(R.transition.anim_in, R.transition.anim_out);

        ActionBar actionBar = getSupportActionBar();
        Objects.requireNonNull(actionBar).setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(R.string.cancel_activity_title);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_back);

    }

    @Override
    protected void onStart() {
        super.onStart();
//        overridePendingTransition(R.transition.anim_in, R.transition.anim_out);
    }
}
