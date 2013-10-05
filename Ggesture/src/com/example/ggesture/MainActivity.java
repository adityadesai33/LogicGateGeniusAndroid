package com.example.ggesture;

import java.io.File;
import java.util.ArrayList;

import android.app.Activity;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.gesture.Prediction;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends Activity implements OnGesturePerformedListener {
	  private GestureLibrary gestureLib;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GestureOverlayView gestureOverlayView = new GestureOverlayView(this);
        View inflate = getLayoutInflater().inflate(R.layout.activity_main, null);
        gestureOverlayView.addView(inflate);
        gestureOverlayView.addOnGesturePerformedListener(this);
        File dir = Environment.getExternalStorageDirectory();
        File yourFile = new File(dir, "gestures");
        gestureLib = GestureLibraries.fromFile(yourFile);
        if (!gestureLib.load()) {
          finish();
        }
        setContentView(gestureOverlayView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

	@Override
	public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
		// TODO Auto-generated method stub
		ArrayList<Prediction> predictions = gestureLib.recognize(gesture);
	    for (Prediction prediction : predictions) {
	      if (prediction.score > 1.0) {
	        Toast.makeText(this, prediction.name, Toast.LENGTH_SHORT)
	            .show();
	        break;
		}
	    }

	}
	}
