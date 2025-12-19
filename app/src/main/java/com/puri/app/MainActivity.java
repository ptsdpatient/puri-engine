package com.puri.app;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.FrameLayout;
import android.view.Gravity;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create a layout programmatically
        FrameLayout layout = new FrameLayout(this);

        // Set background color (for easy visual confirmation)
        layout.setBackgroundColor(Color.parseColor("#FF5722")); // Orange background

        // Add a TextView in the center
        TextView textView = new TextView(this);
        textView.setText("Hello Terminoid!");
        textView.setTextSize(32);
        textView.setTextColor(Color.WHITE);
        textView.setGravity(Gravity.CENTER);

        // Add TextView to layout
        layout.addView(textView);

        // Set layout as the content view
        setContentView(layout);
    }
}
