package com.falsetrue.titecs.files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import com.falsetrue.titecs.R;
import com.falsetrue.titecs.activities.db.Question;



public class FileWorker{
	
	final String TAG = "States";

	private String fileName;
	private final Context context;
	
	
	
	public FileWorker(Context _context) {
		//super();
		this.context = _context;
	}
	
	
	
	public List<String> getQuestionLinesList(){
		
		String line;
		List<String> lines = new ArrayList<String>();
		Log.d(TAG, " Trying to get resourses");//-------------------------------
		Resources res = context.getResources();
		
		try { 
			BufferedReader buffer =  new BufferedReader(new InputStreamReader(res.openRawResource(R.raw.questions_list)));
		 
		    while((line = buffer.readLine()) != null) {
		    	lines.add(line);
		    	Log.d(TAG, line);
		    } 
		} 
		catch(IOException e) {
		    e.printStackTrace();
		}
		
		return lines;
	}
	
	
	
	public List<Question> questionLineParser(List<String> list){
		
		List<Question> result = new ArrayList<Question>();
		
		for(int i = 0; i < list.size(); i++){
			String[] array = list.get(i).split("/");
			if(array.length < 3){
				Log.d(TAG, "Size of array: " + array.length);
				result.add(new Question(0, array[0], array[1], "", "0"));
				 Log.d(TAG, array[0] + " " + array[1] + " " + "0");
			}
			else{
				Log.d(TAG, "Size of array: " + array.length);
				result.add(new Question(0, array[0], array[1], array[2], "0"));
				Log.d(TAG, array[0] + " " + array[1] + " " + array[2] + "0");
			 }
		}
		return result;
	}
}