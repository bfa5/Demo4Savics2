package com.egwusi.demo4savics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Fragment fragment = SingInFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.detail_fragment_container,newDetail)
                .commit();
    }
}
