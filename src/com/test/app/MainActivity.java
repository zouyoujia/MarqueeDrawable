package com.test.app;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView scene = (ImageView) findViewById(R.id.scene);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.circle);
        Fly drawable = new Fly(bitmap);
        scene.setImageDrawable(drawable);
        drawable.start();
    }
}
