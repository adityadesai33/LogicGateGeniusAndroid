package com.example.testdrag;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.content.ClipData;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

public class MainActivity extends Activity {

	// text views being dragged and dropped onto
//	private TextView option1, choice1;
	private ImageView img_drag, img_drop;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// get both sets of text views
		/*
		 * //views to drag option1 = (TextView)findViewById(R.id.option_1);
		 * 
		 * 
		 * //views to drop onto choice1 = (TextView)findViewById(R.id.choice_1);
		 * 
		 * 
		 * //set touch listeners option1.setOnTouchListener(new
		 * ChoiceTouchListener());
		 * 
		 * 
		 * //set drag listeners choice1.setOnDragListener(new
		 * ChoiceDragListener());
		 */
		
		img_drag = (ImageView) findViewById(R.id.imgdrag);
		img_drop = (ImageView) findViewById(R.id.imgdrop);
		
		img_drag.setOnTouchListener(new ChoiceTouchListener());
		
		img_drop.setOnDragListener(new ChoiceDragListener());
		

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/**
	 * ChoiceTouchListener will handle touch events on draggable views
	 * 
	 */
	private final class ChoiceTouchListener implements OnTouchListener {
		public boolean onTouch(View view, MotionEvent motionEvent) {
			if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
				/*
				 * Drag details: we only need default behavior - clip data could
				 * be set to pass data as part of drag - shadow can be tailored
				 */
				ClipData data = ClipData.newPlainText("", "");
				DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(
						view);
				// start dragging the item touched
				view.startDrag(data, shadowBuilder, view, 0);
				return true;
			} else {
				return false;
			}
		}
	}

	/**
	 * DragListener will handle dragged views being dropped on the drop area -
	 * only the drop action will have processing added to it as we are not -
	 * amending the default behavior for other parts of the drag process
	 * 
	 */
	private class ChoiceDragListener implements OnDragListener {

			@SuppressLint("NewApi")
		@Override
		public boolean onDrag(View v, DragEvent event) {
			switch (event.getAction()) {
			case DragEvent.ACTION_DRAG_STARTED:
				// no action necessary
				break;
			case DragEvent.ACTION_DRAG_ENTERED:
				// no action necessary
				break;
			case DragEvent.ACTION_DRAG_EXITED:
				// no action necessary
				break;
			case DragEvent.ACTION_DROP:
				// handle the dragged view being dropped over a drop view
				View view = (View) event.getLocalState();
				// stop displaying the view where it was before it was dragged
				view.setVisibility(View.INVISIBLE);
				// view dragged item is being dropped on
				ImageView dropTarget = (ImageView) v;
				// view being dragged and dropped
				ImageView dropped = (ImageView) view;
				// update the text in the target view to reflect the data being
				// dropped
				Log.v("Main", "dropped is"+dropped);
				dropTarget.setBackground(dropped.getDrawable());
				// if an item has already been dropped here, there will be a tag
	/*			Object tag = dropTarget.getTag();
				// if there is already an item here, set it back visible in its
				// original place
				if (tag != null) {
					// the tag is the view id already dropped here
					int existingID = (Integer) tag;
					// set the original view visible again
					findViewById(existingID).setVisibility(View.VISIBLE);
				}
				// set the tag in the target view being dropped on - to the ID
				// of the view being dropped
				dropTarget.setTag(dropped.getId());*/
				break;
			case DragEvent.ACTION_DRAG_ENDED:
				// no action necessary
				break;
			default:
				break;
			}
			return true;
		}
	}
}
