package com.formigone.life;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.EditText;

public class GameOfLife extends Activity {
	
	private enum PATTERN {
		X, PLUS, BLOCK, BLINKER, TOAD, BEACON
	}

	private Grid grid;
	private boolean isDone;
	private Handler handler;
	
	/**********************************************************
	 * @Override
	 **********************************************************/
	protected void onCreate(Bundle instance) {
		super.onCreate(instance);
		
		grid = new Grid(50, 50, 10, 10);
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

//		List<Boolean> state = stateFactory(PATTERN.TOAD);
//		grid.seedGrid(state);
		grid.seedGrid();
		isDone = false;

		final long generationDelay = 250;
		final EditText out = (EditText) findViewById(R.id.txtOut);
		out.setFocusable(false);
		out.setFocusableInTouchMode(false);
		out.setText("");
		
		new Thread() {

			public void run() {
				
				while (!isDone) {
					handler.post(new Runnable(){
						public void run(){
							Random rand = new Random();
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
	
	private List<Boolean> stateFactory(PATTERN state) {
		List<Boolean> states = new ArrayList<Boolean>();

		switch (state) {
		case X:
			// +---+---+---+
			// | x |   | x |
			// +---+---+---+
			// |   | x |   |
			// +---+---+---+
			// | x |   | x |
			// +---+---+---+
			states.add(true);
			states.add(false);
			states.add(true);
			states.add(false);
			states.add(true);
			states.add(false);
			states.add(true);
			states.add(false);
			states.add(true);
			break;
		case PLUS:
			// +---+---+---+
			// |   | x |   |
			// +---+---+---+
			// | x |   | x |
			// +---+---+---+
			// |   | x |   |
			// +---+---+---+
			states.add(false);
			states.add(true);
			states.add(false);
			states.add(true);
			states.add(false);
			states.add(true);
			states.add(false);
			states.add(true);
			states.add(false);
			break;
			
		case BEACON:
			// +---+---+---+---+
			// | x | x |   |   |
			// +---+---+---+---+
			// | x | x | x | x |
			// +---+---+---+---+
			// |   |   | x | x |
			// +---+---+---+---+
			states.add(true);
			states.add(true);
			states.add(false);
			states.add(false);
			states.add(true);
			states.add(true);
			states.add(true);
			states.add(true);
			states.add(false);
			states.add(false);
			states.add(true);
			states.add(true);
			break;
			
		case TOAD:
			// +---+---+---+---+
			// |   |   | x |   |
			// +---+---+---+---+
			// | x |   |   | x |
			// +---+---+---+---+
			// | x |   |   | x |
			// +---+---+---+---+
			// |   | x |   |   |
			// +---+---+---+---+
			states.add(false);
			states.add(false);
			states.add(true);
			states.add(false);
			states.add(true);
			states.add(false);
			states.add(false);
			states.add(true);
			states.add(true);
			states.add(false);
			states.add(false);
			states.add(true);
			states.add(false);
			states.add(true);
			states.add(false);
			states.add(false);
			break;
			
		case BLINKER:
			// +---+---+---+
			// |   | x |   |
			// +---+---+---+
			// |   | x |   |
			// +---+---+---+
			// |   | x |   |
			// +---+---+---+
			states.add(false);
			states.add(true);
			states.add(false);
			states.add(false);
			states.add(true);
			states.add(false);
			states.add(false);
			states.add(true);
			states.add(false);
			break;
			
		case BLOCK:
			// +---+---+
			// | x | x |
			// +---+---+
			// | x | x |
			// +---+---+
			states.add(true);
			states.add(true);
			states.add(true);
			states.add(true);
			break;
			
		default:
			// +---+---+---+
			// |   | x |   |
			// +---+---+---+
			// |   | x |   |
			// +---+---+---+
			// |   | x |   |
			// +---+---+---+
			states.add(false);
			states.add(true);
			states.add(false);
			states.add(false);
			states.add(true);
			states.add(false);
			states.add(false);
			states.add(true);
			states.add(false);
		}
		
		return states;
	}
}
