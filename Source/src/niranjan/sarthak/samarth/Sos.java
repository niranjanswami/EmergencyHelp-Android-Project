package niranjan.sarthak.samarth;

import java.util.ArrayList;
import niranjan.sarthak.samarth.R;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Sos extends Activity{

	private ArrayList<Button> list = new ArrayList<Button>();
	private static TableLayout table;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sos);

		addRow(list.size(), "Ambulance:", "108");
		addRow(list.size(), "Fire Station:", "101");
		addRow(list.size(), "Police:", "100");

		//adding home button to layout
		Button home = new Button(this);
		home.setText("Home");
		int index = list.size()+ 1;
		home.setId(index);
		home.setOnClickListener(btnclick);
		home.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
		table.addView(home);

	}

	public void addRow(int index, String textview, String number){
		table = (TableLayout)findViewById(R.id.table);
		TableRow row = new TableRow(this);
		row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.WRAP_CONTENT));
		TextView test = new TextView(this);
		test.setText(textview);
		test.setTextSize(18);
		test.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.WRAP_CONTENT));

		Button sample = new Button(this);
		sample.setText(number);
		sample.setId(index);
		sample.setOnClickListener(btnclick);
		sample.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));

		row.addView(test);
		row.addView(sample);

		table.addView(row);
		list.add(sample);
	}
	OnClickListener btnclick = new OnClickListener(){

		@Override
		public void onClick(View v){
			if(v.getId() < list.size()){
				try{
					StringBuilder number = new StringBuilder();
					number.append("tel:");
					number.append(list.get(v.getId()).getText().toString());
					Intent callIntent = new Intent (Intent.ACTION_CALL);
					callIntent.setData(Uri.parse(number.toString()));
					startActivity(callIntent);
				}catch (ActivityNotFoundException activityException) {
					Log.e("First Response", "Call failed");
				} 
			} else{
				finish();
			}
		}
	};
	public void onDestroy(){
		super.onDestroy();
	}
	public void onPause(){
		super.onPause();
		Log.v(null, "Sos's onPause Method !!!");
	}
	public void onResume(){
		super.onResume();
		Log.v(null, "Sos's onResume Method !!!");
	}
	protected void onSaveInstanceState (Bundle outState){
		super.onSaveInstanceState(outState);
	}
	protected void onRestoreInstanceState (Bundle savedInstanceState){
		super.onRestoreInstanceState(savedInstanceState);
	}
}
