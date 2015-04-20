package com.falsetrue.titecs.activities;

import com.falsetrue.titecs.R;
import com.falsetrue.titecs.activities.db.DatabaseHandler;
import com.falsetrue.titecs.activities.db.Question;
import com.falsetrue.titecs.activities.db.Statistics;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class PostGameResultActivity extends Activity implements OnClickListener, OnLoadCompleteListener{
	
	public static final String EXTRA_LEVEL_NUMBER = "level_number";
	public static final String EXTRA_RESULT_PERCENTS = "result_percents";
	
	private static final float LEVEL_ACCEPTANCE_PERCENTAGE = 70.0f;
	
	final int MAX_STREAMS = 5;
	private SoundPool soundPool;

	private int soundIdCongratulations;
	private int soundIdDisappointment;
	
	TextView txtLevelMark;
	TextView txtResultPercents;
	Button btnAvailableLevel;
	Button btnGoToResults;
	Button btnGoToMenu;
	
	private float resultPercentage;
	private int levelNumber;
	private int availableLevel;
	
	private Statistics statistics;
	
	private DatabaseHandler db;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postgameresult);
        
        btnAvailableLevel = (Button) findViewById(R.id.btnAvailableLevel);
        btnGoToResults = (Button) findViewById(R.id.btnGoToResults);
        btnGoToMenu = (Button) findViewById(R.id.btnGoToMenu);
        
        btnAvailableLevel.setOnClickListener(this);
        btnGoToResults.setOnClickListener(this);
        btnGoToMenu.setOnClickListener(this);
        
        txtLevelMark = (TextView) findViewById(R.id.txtlevelMark);
        txtResultPercents = (TextView) findViewById(R.id.txtResultPercents);
        
        soundPool = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, 0);
        soundPool.setOnLoadCompleteListener(this);
		soundIdCongratulations = soundPool.load(this, R.raw.aplause, 1);
		soundIdDisappointment = soundPool.load(this, R.raw.wrong_answer, 1); 
        
        levelNumber = getIntent().getIntExtra(EXTRA_LEVEL_NUMBER, 0);
        availableLevel = levelNumber;
        
        resultPercentage = getIntent().getFloatExtra(EXTRA_RESULT_PERCENTS, (float) 0.0);
        
        //txtResultPercents.setText("Percentage Result: " +  Float.toString(resultPercentage) + " %");
        
        txtResultPercents.setText("Percentage Result: " +  Float.toString((Math.round(10*((resultPercentage))))/10.0f) + " %");
        
        
        if(resultPercentage < LEVEL_ACCEPTANCE_PERCENTAGE){
        	
        	//soundPool.play(soundIdDisappointment, 1, 1, 0, 0, 1);
        	
        	txtLevelMark.setText("Disappointment!");
        	if(levelNumber == 0){
        		btnAvailableLevel.setText("Level 1");
            }
        	else if(levelNumber == 1){
        		btnAvailableLevel.setText("Level 2");
        	}
        	else if(levelNumber == 2){
        		btnAvailableLevel.setText("Level 3");
        	}
        	else if(levelNumber == 3){
        		btnAvailableLevel.setText("Level 4");
        	}
        	else if(levelNumber == 4){
        		btnAvailableLevel.setText("Level 5");
        	}
        }
        else{
        	
        	//soundPool.play(soundIdCongratulations, 1, 1, 0, 0, 1);
        	
        	txtLevelMark.setText("Congratulations!");
        	
        	if(levelNumber == 0){
        		availableLevel = 1;
        		btnAvailableLevel.setText("Level 2");
            }
        	else if(levelNumber == 1){
        		availableLevel = 2;
        		btnAvailableLevel.setText("Level 3");
        	}
        	else if(levelNumber == 2){
        		availableLevel = 3;
        		btnAvailableLevel.setText("Level 4");
        	}
        	else if(levelNumber == 3){
        		availableLevel = 4;
        		btnAvailableLevel.setText("Level 5");
        	}
        	else if(levelNumber == 4){
        		btnAvailableLevel.setText("Level 5");
        	}
        	
        }
        
        db = new DatabaseHandler(this);
        statistics = db.getStatistics(1);

    }
	

	
	@Override
	public void onBackPressed() {
	    //onNavigateUp();
		Intent intent = new Intent(this,MainActivity.class);
    	intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    	finish();
	}
	
	/*
	@SuppressLint("NewApi") @Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if (keyCode == KeyEvent.KEYCODE_BACK) {
	       	onNavigateUp();
	    	//Intent intent;
	    	//intent = new Intent(this, MainActivity.class);
	        //startActivity(intent);
	        
	    }
	    return super.onKeyDown(keyCode, event);
	}*/
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent;
		switch (v.getId()) {
	    case R.id.btnAvailableLevel:
	      // TODO Call second activity
	    	intent  = new Intent(this, GameActivity.class);
	    	intent.putExtra("levelnumber", availableLevel);	    	
	        startActivity(intent);
	        finish();
	        break;
	    case R.id.btnGoToResults:
		      // TODO Call second activity
	    	intent = new Intent(this, ResultsActivity.class);
	    	intent.putExtra(ResultsActivity.EXTRA_STATISTICS, statistics);
	    	startActivity(intent);
		    break;
	    case R.id.btnGoToMenu:
	    	// TODO Call second activity
	    	//intent = new Intent(this, MainActivity.class);
	    	//startActivity(intent);
	    	intent = new Intent(this,MainActivity.class);
	    	intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	        startActivity(intent);
	    	finish();
	    	break;  
	    default:
	      break;
	    }
		
	}

	@Override
	public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
		// TODO Auto-generated method stub
		
	}

}


/*
 *             android:parentActivityName=".MainActivity">
    <!-- Parent activity meta-data to support 4.0 and lower -->
    <meta-data
        android:name="android.support.PARENT_ACTIVITY"
        android:value=".MainActivity" />
        */
