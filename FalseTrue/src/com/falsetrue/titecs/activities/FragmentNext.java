package com.falsetrue.titecs.activities;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.falsetrue.titecs.R;

public class FragmentNext extends Fragment{
	
	public static final int NEXT_QUESTION = 2;
	
	public interface onNextButtonEventListener {
	    public void nextButtonEvent(int s);
	  }
	
	onNextButtonEventListener nextButtonEventListener;
	
	@Override
	  public void onAttach(Activity activity) {
	    super.onAttach(activity);
	        try {
	        	nextButtonEventListener = (onNextButtonEventListener) activity;
	        } catch (ClassCastException e) {
	            throw new ClassCastException(activity.toString() + " must implement onSomeEventListener");
	        }
	  }
	
	
	@Override
	  public View onCreateView(LayoutInflater inflater, ViewGroup container,
	      Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_next_button, null);
		 
		 Button btnNext = (Button) v.findViewById(R.id.btnNext);
		 btnNext.setOnClickListener(new OnClickListener() {
		      public void onClick(View v) {
		    	  nextButtonEventListener.nextButtonEvent(NEXT_QUESTION);
		      }
		    });
		 
		 
	    return v;
	  }

}
