package com.thlight.test.demo;

import android.app.Activity;
import android.os.Bundle;

public class DemoActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
	System.out.println("Hello");

    }
}