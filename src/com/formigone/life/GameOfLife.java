package com.formigone.life;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

public class GameOfLife extends Activity {

	private Grid grid;
	private boolean isDone;
	
	/**********************************************************
	 * @Override
	 **********************************************************/
	protected void onCreate(Bundle instance) {
		super.onCreate(instance);
		
		grid = new Grid(5, 8);
		isDone = false;
		setContentView(R.layout.test_grid);
	}

	
	/**********************************************************
	 * @Override
	 **********************************************************/
	protected void onPause() {
		super.onPause();
		
		isDone = true;
	}



	/**********************************************************
	 * @Override
	 **********************************************************/
	protected void onResume() {
		super.onResume();
		
		final long generationDelay = 1000;
		final EditText out = (EditText) findViewById(R.id.txtOut);
		
		new Thread() {

			/**********************************************************
			 * @Override
			 * 
			 * ---------------
			 * Lesson learned
			 * ---------------
			 * Only UI thread can update the UI
			 * -> More technically, 
			 * "Only the original thread that created a view hierarchy can touch its views" 
			 * 
			 * ---------
			 * Solutions
			 * - Make Grid an activity, so that it can change itself
			 *   - Grid would need to be an activity and a thread ?
			 * - Use async task
			 * ---------
			 **********************************************************/
			public void run() {
				while (!isDone) {
					try {
						grid.updateGrid();
	
						String gridStatus = grid.getGrid();
						out.setText(gridStatus);
						
						Thread.sleep(generationDelay);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			
		}.start();
	}
}
