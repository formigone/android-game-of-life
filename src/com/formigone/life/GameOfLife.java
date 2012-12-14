package com.formigone.life;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.EditText;

public class GameOfLife extends Activity {

	private Grid grid;
	private boolean isDone;
	private Handler handler;
	
	/**********************************************************
	 * @Override
	 **********************************************************/
	protected void onCreate(Bundle instance) {
		super.onCreate(instance);
		
		grid = new Grid(5, 5, 10, 10);
		isDone = false;
		handler = new Handler();
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
		
		final long generationDelay = 5000;
		final EditText out = (EditText) findViewById(R.id.txtOut);
		
		new Thread() {

			public void run() {
				while (!isDone) {
					handler.post(new Runnable(){
						public void run(){
							String gridStatus = grid.getGrid();
							out.setText(gridStatus);
						};
					});

					try {
						Thread.sleep(generationDelay);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					grid.nextGeneration();
				}
			}
			
		}.start();
	}
}
