package com.naruto.maptask;

import android.content.Intent;
import android.graphics.drawable.ClipDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    ImageButton ib_phone;
    private GoogleMap mMap;
    private ClipDrawable mImageDrawable;

    private int mLevel = 0;
    private int toLevel = 10000;

    public static final int LEVEL_DIFF = 100;
    public static final int DELAY = 100;

    private Handler mDownHandler = new Handler();
    private Runnable animateDownImage = new Runnable() {

        @Override
        public void run() {
            if (mLevel <= toLevel) {
                doTheDownAnimation();
                mDownHandler.postDelayed(this, DELAY);
            }
        }
    };

    private void doTheDownAnimation() {
        mLevel += LEVEL_DIFF;
        mImageDrawable.setLevel(mLevel);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        ib_phone = findViewById(R.id.ibtn_phone);
        ImageView img = findViewById(R.id.img_up);
        mImageDrawable = (ClipDrawable) img.getDrawable();

        mImageDrawable.setLevel(0);
        mDownHandler.post(animateDownImage);

        overridePendingTransition(R.transition.anim_in, R.transition.anim_out);

        ib_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MapsActivity.this, CancelActivity.class);
                startActivity(intent);
            }
        });

        View transparentTouchpanel = findViewById(R.id.transparent_view);
        transparentTouchpanel.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    view.getParent().requestDisallowInterceptTouchEvent(false);
                } else {
                    view.getParent().requestDisallowInterceptTouchEvent(true);
                }
                return false;
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        overridePendingTransition(R.transition.anim_in, R.transition.anim_out);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng delhi = new LatLng(28.6139, 77.2090);
        mMap.addMarker(new MarkerOptions().position(delhi).title("Marker is in Delhi( India )"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(delhi));
    }

//    public void onClickAnim(View view) {
//        int temp_level = ((Integer.parseInt("700")) * MAX_LEVEL) / 100;
//
//        if (toLevel == temp_level || temp_level > MAX_LEVEL) {
//            return;
//        }
//        toLevel = (temp_level <= MAX_LEVEL) ? temp_level : toLevel;
//        if (toLevel > fromLevel) {
//            // cancel previous process first
//            mDownHandler.removeCallbacks(animateDownImage);
//            MapsActivity.this.fromLevel = toLevel;
//
//            mUpHandler.post(animateUpImage);
//        } else {
//            // cancel previous process first
//            mUpHandler.removeCallbacks(animateUpImage);
//            MapsActivity.this.fromLevel = toLevel;
//
//            mDownHandler.post(animateDownImage);
//        }
//    }
}
